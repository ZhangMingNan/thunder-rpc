package com.ly.zmn48644.rpc.framework;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zmn on 17-12-22.
 */
public class ProviderReflect {
    private static final ExecutorService executorServicec = Executors.newCachedThreadPool();

    public static void provider(final Object service,Integer port)throws Exception{
        ServerSocket serverSocket = new ServerSocket(port);
        while (true){
            final Socket socket = serverSocket.accept();
            executorServicec.execute(new Runnable() {
                public void run() {

                    try {
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                        try {
                            String methodName = inputStream.readUTF();
                            Object[] objects = (Object[])inputStream.readObject();
                            final Object result = MethodUtils.invokeExactMethod(service, methodName, objects);
                            outputStream.writeObject(result);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            inputStream.close();
                            outputStream.close();
                            socket.close();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }
    }
}
