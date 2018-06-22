package com.ly.zmn48644.rpc.transport;

import com.ly.zmn48644.rpc.transport.netty.Netty4Server;
import org.junit.Test;

public class ServerTest {


    @Test
    public void test() {
        Server server = new Netty4Server();
        server.open();
    }

}
