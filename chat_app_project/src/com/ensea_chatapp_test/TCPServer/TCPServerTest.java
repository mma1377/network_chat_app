package com.ensea_chatapp_test.TCPServer;

import java.io.IOException;
import org.junit.Test;

import com.ensea_chatapp_tcp.Server.TCPServer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TCPServerTest {
    @Test
    public void testServerInitializationWithDefaultPort() throws IOException {
        TCPServer tcpServer = new TCPServer();
        tcpServer.launch();
        assertEquals(8439, tcpServer.GetServerSocket().getLocalPort());
    }

    @Test
    public void testServerInitializationWithSpecifiedPort() throws IOException {
        TCPServer tcpServer = new TCPServer(8441);
        tcpServer.launch();
        assertEquals(8441, tcpServer.GetServerSocket().getLocalPort());
    }

    @Test
    public void testServerLaunch() throws IOException {
        TCPServer tcpServer = new TCPServer(8442);
        tcpServer.launch();
        assertNotNull(tcpServer.GetServerSocket());
    }

    @Test
    public void testServerStateWhenRunning() throws IOException {
        TCPServer tcpServerWithPort = new TCPServer(8443);
        tcpServerWithPort.launch();
        assertEquals("The server is running on port: 8443", tcpServerWithPort.toString());
    }

    @Test
    public void testServerStateWhenNotRunning() throws IOException {
        TCPServer tcpServer = new TCPServer(8444);
        assertEquals("The server is not running", tcpServer.toString());
    }

}