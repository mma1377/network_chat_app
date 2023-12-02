package com.ensea_chatapp_tcp.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Thread in order to handle clients' connections
 * The thread listen and accept connection. If a connection is established, another ConnectionThread is instantiated
 * in order to handle new connections
 *  */
public class ConnectionThread extends java.lang.Thread {
    TCPServer _tcpServer;
    Socket _socket;

    /**
     * Constructor
     *
     * @param tcpServer the TCPServer which wraps the ServerSocket
     */
    public ConnectionThread(TCPServer tcpServer) {
        _tcpServer = tcpServer;
    }

    /**
     * Send a message to the client connected to this thread
     *
     * @param message The message as String
     */
    public void send_message(String message) throws IOException {
        byte[] data = message.getBytes();
        OutputStream output = _socket.getOutputStream();
        output.write(data);
    }

    /**
     * The method run in order to start the FetchThread. This is going to accept a connection
     * In case a connection is establsihed, the tcpServer will make new Connection.
     * Then this connection will fetch data from client. In case the data is available, it will
     * echo the data and sender's ip
     */
    public void run() {
        try {
            _socket = _tcpServer.GetServerSocket().accept();
            System.out.println("A new connection is established with ip address " + _socket.getInetAddress().getHostAddress());

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        _tcpServer.NewConnection();

        // Fetch client data and echo "ip: data"
        try {
            InputStream inputStream = _socket.getInputStream();
            byte[] buf = new byte[1024];
            int bytes_read;
            while ((bytes_read = inputStream.read(buf)) != -1){
                    String respond = _socket.getInetAddress().getHostAddress() + ": " + new String(buf, 0, bytes_read) + "\n";
                _tcpServer.Echo(respond);
            }

        } catch (IOException e) {
             System.out.println(e.getMessage());
             _tcpServer.RemoveConnection(this);
        };
    }

}