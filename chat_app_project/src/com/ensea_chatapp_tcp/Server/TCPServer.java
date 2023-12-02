package com.ensea_chatapp_tcp.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class to implement a chat app TCP Server
 * <p>The class takes an optional command line arguments: Server port
 * In the case that port is absent, 8439 is the default port.
 *
 * <p>Usage: java com.ensea_chatapp_tcp.Server.TCPServer
 * <p>Usage: java com.ensea_chatapp_tcp.Server.TCPServer <server_port>
 *  */
public class TCPServer {

    public static void main(String[] args) throws IOException {
        TCPServer tcpServer;
        if (args.length < 1)
            tcpServer = new TCPServer();
        else {
            tcpServer = new TCPServer(Integer.parseInt(args[0]));
        }
        tcpServer.launch();
        System.out.println(tcpServer);
    }

    private final int _port;
    private ServerSocket _serverSocket;
    private final List<ConnectionThread> _connectionsList;

    public TCPServer() {
        this(8439);
    }

    public TCPServer(int listening_port) {
        _port = listening_port;
        _connectionsList = new ArrayList<ConnectionThread>();
    }

    public ServerSocket GetServerSocket() {
        return _serverSocket;
    }

    public void launch() throws IOException {
        _serverSocket = new ServerSocket(_port);
        NewConnection();
    }

    /**
     * Send message to all clients connected to threads
     *
     * @param message The message as String
     */
    public void Echo(String message) throws IOException {
        for (ConnectionThread connection : _connectionsList) {
            if(connection._socket != null)
                connection.send_message(message);
        }
    }

    /**
     * Create a new connection thread and add it to the connection threads list
     */
    public void NewConnection() {
        ConnectionThread connection =  new ConnectionThread(this);
        _connectionsList.add(connection);
        connection.start();
    }

    /**
     * Remove a connection thread from connection threads list
     * @param connection The connection which should be removed
     */
    public void RemoveConnection(ConnectionThread connection) {
        _connectionsList.remove(connection);
    }

    /**
     * Returns state of the Server
     * @return State of server in String
     */
    @Override
    public String toString() {
        if (_serverSocket != null && !_serverSocket.isClosed())
            return "The server is running on port: " + _serverSocket.getLocalPort();
        else
            return "The server is not running";
    }
}