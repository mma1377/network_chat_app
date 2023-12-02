package com.ensea_chatapp_udp.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * This is a class to implement a UDP Server which receives data and print it in console
 * <p>The class takes an optional command line arguments: Server port
 * In the case that port is absent, 8439 is the default port.
 *
 * <p>Usage: java com.ensea_chatapp_udp.Server.UDPServer
 * <p>Usage: java com.ensea_chatapp_udp.Server.UDPServer server_port
 *  */
public class UDPServer {

    public static void main(String[] args) throws IOException {
        UDPServer udpServer;
        if(args.length < 1)
            udpServer = new UDPServer();
        else {
            udpServer = new UDPServer(Integer.parseInt(args[0]));
        }
        udpServer.launch();
        System.out.println(udpServer);
    }
    private final int _port;
    private DatagramSocket _socket;
    byte[] _byteBuffer;

    UDPServer() {
        this(8439);
    }

    UDPServer(int listening_port) {
        _port = listening_port;
        _byteBuffer = new byte[512];
    }

    /**
     * launch the server in order to receive data from clients and print it in console
     */
    public void launch() throws IOException {
        _socket = new DatagramSocket(_port);
        while (true) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(_byteBuffer, 8);
                _socket.receive(datagramPacket);
                String receivedStr = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println(receivedStr);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    /**
     * Returns state of the Server
     * @return State of server in String
     */
    @Override
    public String toString() {
        if (_socket != null && !_socket.isClosed())
            return "The server is running on port: " + _socket.getLocalPort();
        else
            return "The server is not running";
    }
}
