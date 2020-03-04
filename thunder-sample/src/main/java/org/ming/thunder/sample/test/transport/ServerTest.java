package org.ming.thunder.sample.test.transport;


import org.ming.thunder.rpc.DefaultResponse;
import org.ming.thunder.rpc.Request;
import org.ming.thunder.rpc.URL;
import org.ming.thunder.transport.MessageHandler;
import org.ming.thunder.transport.Server;
import org.ming.thunder.transport.netty4.Netty4Server;

import java.io.IOException;

public class ServerTest {



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
