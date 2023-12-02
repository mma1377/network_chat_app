package com.ensea_chatapp_tcp.Client;

import java.io.IOException;


/**
 * This is a class to implement a Thread in order to fetch data from the TCP server consistently.
 * This class is a subclass of java.lang.Thread.
 */
public class FetchThread extends java.lang.Thread {

    TCPClient _tcpClient;
    DataFetchHandler _dataFetchHandler;

    /**
     * The Constructor of FetchThread class
     *
     * @param tcpClient This is the reference to TCPClient class which we want to fetch data from its connection
     * @param dataFetchHandler This handler function is called when a new data is fetched. It can be used to
     *                         display the data or other purposes that needs to be done by data
     */
    public FetchThread(TCPClient tcpClient, DataFetchHandler dataFetchHandler) {
        _tcpClient = tcpClient;
        _dataFetchHandler = dataFetchHandler;
    }

    /**
     * The method run in order to start the FetchThread. This is going to fetch data from server in an infinite loop
     * The dataFetchHandler which is argument of FetchThread will be called when a new data is fetched
     */
    public void run() {
        while (true) {
            try {
                 String fetchedData = _tcpClient.fetch_data();
                 if (fetchedData != null){
                     _dataFetchHandler.applyFunction(fetchedData);
                 }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

}