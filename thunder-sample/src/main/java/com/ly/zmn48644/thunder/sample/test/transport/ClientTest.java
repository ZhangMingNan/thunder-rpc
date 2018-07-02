package com.ly.zmn48644.thunder.sample.test.transport;


import com.ly.zmn48644.thunder.rpc.DefaultRequest;
import com.ly.zmn48644.thunder.rpc.Response;
import com.ly.zmn48644.thunder.rpc.URL;
import com.ly.zmn48644.thunder.transport.Client;
import com.ly.zmn48644.thunder.transport.netty4.Netty4Client;

import java.io.IOException;


/**
 * 作者：张明楠
 * 时间：2018/6/23
 */
public class ClientTest {

    public void client() throws IOException {
        URL url = new URL("netty", "192.168.199.170", 8081, "", null);
        Client client = new Netty4Client(url);
        client.open();
        DefaultRequest request = new DefaultRequest();
        request.setRequestId(1);
        request.setInterfaceName("com.ly.zmn48644.rpc.test.UserService");
        request.setMethodName("findUsersByName");
        request.setArguments(new Object[]{"zmn"});
        Response response = client.request(request);
        System.out.println(response.getValue());
        System.in.read();
    }
}
