package com.ensea_chatapp_tcp.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.scene.control.Label;

public class FetchThread extends java.lang.Thread {

    TCPClient _tcpClient;
    MyFunction _function;

    public FetchThread(TCPClient tcpClient, MyFunction function) {
        _tcpClient = tcpClient;
        _function = function;
    }

    public void run() {
        while (true) {
            try {
                 String fetchedData = _tcpClient.fetch_data();
                 if (fetchedData != null){
                     _function.applyFunction(fetchedData);
                 }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}