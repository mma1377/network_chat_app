package chat_TCP_Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private int _port;
    private ServerSocket _serverSocket;
    public List<ConnectionThread> connectionsList;

    TCPServer() {
        this(8439);
    }

    TCPServer(int listening_port) {
        _port = listening_port;
        connectionsList = new ArrayList<ConnectionThread>();
    }

    public void launch() throws IOException {
        _serverSocket = new ServerSocket(_port);
        ConnectionThread connection = new ConnectionThread(this, _serverSocket);
        connectionsList.add(connection);
        connection.start();
    }

    public void Broadcast(String message) throws IOException {
        for (ConnectionThread connection : connectionsList) {
            connection.send(message);
        }
    }

    @Override
    public String toString() {
        if (_serverSocket != null && !_serverSocket.isClosed())
            return "The server is running on port: " + _serverSocket.getLocalPort();
        else
            return "The server is not running";
    }
}
