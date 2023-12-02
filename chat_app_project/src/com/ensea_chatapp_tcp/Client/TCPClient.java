package com.ensea_chatapp_tcp.Client;

import javafx.scene.control.Label;

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
    private Socket _socket;

    public TCPClient() {
        _port = 8439;
        _host = "localhost";
    }
    TCPClient(String host) {
        _port = 8439;
        _host = host;
    }
    public TCPClient(String host, int port) {
        _port = port;
        _host = host;
    }


    public void launch() throws IOException, InterruptedException {
        Socket _socket = new Socket(_host, _port);
        Console console = System.console();
        while (true) {
            String inputLine = console.readLine();
            send_data(inputLine);
            System.out.print(fetch_data());
        }
    }

    public void graphical_launch() throws IOException, InterruptedException {
        _socket = new Socket(_host, _port);
    }

    public void graphical_fetch(UpdateDisplayChat function) throws IOException, InterruptedException {
        FetchThread fetchThread = new FetchThread(this, function);
        fetchThread.start();
    }

    public String fetch_data() throws IOException {
        byte[] buf = new byte[4096];
        int bytes_read;
        InputStream inputStream = _socket.getInputStream();
        if(inputStream.available() > 0) {
            if ((bytes_read = inputStream.read(buf)) != -1) {
                return new String(buf, 0, bytes_read);
            }
        }
        return null;
    }

    public void send_data(String input) throws IOException {
        OutputStream output = _socket.getOutputStream();
        byte[] data = input.getBytes();
        output.write(data);
    }
}
