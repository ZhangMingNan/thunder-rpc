package com.ly.zmn48644.rpc.framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Created by zmn on 17-12-22.
 */
public class ConsumerProxy {


    public<T> T consume(final Class<T> interfaceClass,final String host,final Integer port){

        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class<?>[]{interfaceClass}, new InvocationHandler() {
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                Socket socket  = new Socket(host,port);
              try {

                  ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                  try{

                      outputStream.writeUTF(method.getName());

                      outputStream.writeObject(objects);

                      ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                      try{
                          Object result = inputStream.readObject();

                          if (result instanceof  Throwable){
                              throw (Throwable)result;
                          }
                          return result;
                      }finally {
                          inputStream.close();
                      }
                  }finally {
                      outputStream.close();
                  }


              }finally {
                  socket.close();
              }
            }
        });
    }
}
