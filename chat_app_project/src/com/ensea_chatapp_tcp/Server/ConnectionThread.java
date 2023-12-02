package com.ensea_chatapp_tcp.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionThread extends java.lang.Thread {
    TCPServer _tcpServer;
    Socket _socket;

    public ConnectionThread(TCPServer tcpServer) {
        _tcpServer = tcpServer;
    }

    public void send_message(String message) throws IOException {
        byte[] data = message.getBytes();
        OutputStream output = _socket.getOutputStream();
        output.write(data);
    }

    public void run() {
        try {
            _socket = _tcpServer.GetServerSocket().accept();
            _tcpServer.NewConnection();
            InputStream inputStream = _socket.getInputStream();
            byte[] buf = new byte[4096];
            int bytes_read;
            while ((bytes_read = inputStream.read(buf)) != -1){
                    String respond = _socket.getInetAddress().getHostAddress() + ": " + new String(buf, 0, bytes_read) + "\n";
                _tcpServer.Broadcast(respond);
            }

        } catch (IOException e) {
             System.out.println(e.getMessage());
             _tcpServer.RemoveConnection(this);
        };
    }

}