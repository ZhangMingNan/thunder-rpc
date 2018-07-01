package com.ly.zmn48644.thunder.transport;


import com.ly.zmn48644.thunder.rpc.DefaultResponse;
import com.ly.zmn48644.thunder.rpc.Request;
import com.ly.zmn48644.thunder.rpc.URL;

import com.ly.zmn48644.thunder.transport.netty.Netty4Server;
import org.junit.Test;

import java.io.IOException;

public class ServerTest {


    @Test
    public void test() throws IOException {
        String interfaceName = "";
        URL url = new URL("netty", "localhost", 18080, interfaceName,null);

        Server server = new Netty4Server(url, new MessageHandler() {
            @Override
            public Object handle(Object message) {
                Request request = Request.class.cast(message);
                DefaultResponse response = new DefaultResponse("hello world, from " + request.getArguments()[0] + "!");
                response.setRequestId(request.getRequestId());
                return response;
            }
        });
        server.open();
        System.out.println("server started on " + url.getPort() + "!");
        System.in.read();
    }

}
