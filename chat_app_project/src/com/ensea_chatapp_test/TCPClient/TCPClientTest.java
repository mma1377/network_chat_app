package com.ensea_chatapp_test.TCPClient;

import java.io.IOException;
import org.junit.Test;
import org.junit.BeforeClass;

import com.ensea_chatapp_tcp.Client.TCPClient;
import com.ensea_chatapp_tcp.Server.TCPServer;

import static org.junit.Assert.*;


public class TCPClientTest {

    @BeforeClass
    public static void setUp() throws Exception {
        TCPServer tcpServer = new TCPServer();
        tcpServer.launch();
    }

    @Test
    public void testSendData() throws IOException {
        TCPClient tcpClient = new TCPClient();
        tcpClient.launch();
        try {
            tcpClient.send_data("test");
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void testFetchData() throws IOException {
        TCPClient tcpClient = new TCPClient();
        tcpClient.launch();
        TCPClient tcpClient2 = new TCPClient();
        tcpClient.launch();
        tcpClient2.launch();
        try {
            tcpClient.send_data("test");
            String result = tcpClient2.fetch_data();
            while (result == null) {
                result = tcpClient2.fetch_data();
            }
            assertEquals("127.0.0.1: test\n", result);
        }
        catch (Exception e){
            fail();
        }
    }


}