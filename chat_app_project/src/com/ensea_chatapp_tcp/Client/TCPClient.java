package com.ensea_chatapp_tcp.Client;

import java.io.*;
import java.net.*;
import java.util.Objects;

import static java.lang.Thread.sleep;

/**
 *
 * @author Mohammadmahdi ALIJANI
 * @author Maryna SHAPOVAL
 */

public class TCPClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        TCPClient tcpClient;
        if (args.length < 1)
            tcpClient = new TCPClient();
        else if (args.length < 2)
            tcpClient = new TCPClient(args[0]);
        else {
            tcpClient = new TCPClient(args[0], Integer.parseInt(args[1]));
        }
        tcpClient.launch();
        System.out.println(tcpClient);
    }

    private final int _port;
    private final String _host;

    TCPClient() {
        _port = 8439;
        _host = "localhost";
    }
    TCPClient(String host) {
        _port = 8439;
        _host = host;
    }
    TCPClient(String host, int port) {
        _port = port;
        _host = host;
    }


    public void launch() throws IOException, InterruptedException {
        Socket _socket = new Socket(_host, _port);
        Console console = System.console();
        while (true) {
            String inputLine = console.readLine();
            OutputStream output = _socket.getOutputStream();
            byte[] data = inputLine.getBytes();
            output.write(data);
            byte[] buf = new byte[4096];
            int bytes_read;
            InputStream inputStream = _socket.getInputStream();
            if(inputStream.available() > 0) {
                if ((bytes_read = inputStream.read(buf)) != -1) {
                    String respond = new String(buf, 0, bytes_read);
                    System.out.print(respond);
                }
            }
        }
    }
}
