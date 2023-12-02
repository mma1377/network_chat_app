package com.ensea_chatapp_tcp.Client;

import java.io.IOException;
import java.io.Console;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * This is a class to implement a TCP Client needed to connect to the chat TCP server
 * <p>The class takes two optional command line arguments: the first argument is the server ip address,
 * and the second argument is server ip address.
 * In the case that the address or ports are absent, the localhost is the default address and 8439 is the default port.
 *
 * <p>Usage: java com.ensea_chatapp_tcp.Client.TCPClient
 * <p>Usage: java com.ensea_chatapp_tcp.Client.TCPClient <server_ip>
 * <p>Usage: java com.ensea_chatapp_tcp.Client.TCPClient <server_ip> <server_port>
 *  */
public class TCPClient {

    public static void main(String[] args) throws IOException {
        TCPClient tcpClient;
        if (args.length < 1)
            tcpClient = new TCPClient();
        else if (args.length < 2)
            tcpClient = new TCPClient(args[0]);
        else {
            tcpClient = new TCPClient(args[0], Integer.parseInt(args[1]));
        }
        tcpClient.launch();
        tcpClient.synchronous_send_and_fetch();
    }

    private final int _port;
    private final String _host;
    private Socket _socket;
    private final byte[] _readBuf;

    public TCPClient() {
        this("localhost", 8439);
    }

    public TCPClient(String host) {
        this(host, 8439);

    }
    public TCPClient(String host, int port) {
        _port = port;
        _host = host;
        _readBuf = new byte[1024];
    }


    public void launch() throws IOException {
        _socket = new Socket(_host, _port);
    }


    /**
     * send data read from console to server and fetch the data available from server synchronously
     * The function will continue fetch sending/fetching cycle infinitely
     */
    public void synchronous_send_and_fetch() throws IOException {
        Console console = System.console();
        while (true) {
            String inputLine = console.readLine();
            send_data(inputLine);
            System.out.print(fetch_data());
        }
    }

    /**
     * Start a thread in order to fetch the data from server asynchronously. The thread will continue fetch infinitely
     *
     * @param handler The handler invoked when a data is available. The handler must take 1 argument as fetched data
     */
    public void asynchronous_fetch(DataFetchHandler handler) {
        FetchThread fetchThread = new FetchThread(this, handler);
        fetchThread.start();
    }

    /**
     * Try to fetch data
     *
     * @return if there is data available, it will return it as String, otherwise it will return null
     */
    public String fetch_data() throws IOException {
        int bytes_read;
        InputStream inputStream = _socket.getInputStream();
        if(inputStream.available() > 0) {
            if ((bytes_read = inputStream.read(_readBuf)) != -1) {
                return new String(_readBuf, 0, bytes_read);
            }
        }
        return null;
    }

    /**
     * send String data to Server
     *
     * @param dataStr The data which will be sent to Server
     */
    public void send_data(String dataStr) throws IOException {
        OutputStream output = _socket.getOutputStream();
        byte[] data = dataStr.getBytes();
        output.write(data);
    }
}
