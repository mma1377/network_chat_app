package com.ensea_chatapp_tcp.Client;

import java.io.IOException;

public class FetchThread extends java.lang.Thread {

    TCPClient _tcpClient;
    UpdateDisplayChat _function;

    public FetchThread(TCPClient tcpClient, UpdateDisplayChat function) {
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