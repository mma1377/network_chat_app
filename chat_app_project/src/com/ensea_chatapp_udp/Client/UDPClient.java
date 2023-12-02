package com.ensea_chatapp_udp.Client;

import java.io.Console;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * This is a class to implement a UDP Client which send data to the UDP server
 * <p>The class takes two optional command line arguments: the first argument is the server ip address,
 * and the second argument is server ip address.
 * In the case that the address or ports are absent, the localhost is the default address and 8439 is the default port.
 *
 * <p>Usage: java com.ensea_chatapp_udp.Client.UDPClient
 * <p>Usage: java com.ensea_chatapp_udp.Client.UDPClient server_ip
 * <p>Usage: java com.ensea_chatapp_udp.Client.UDPClient server_ip server_port
 *  */
public class UDPClient {
    public static void main(String[] args) throws IOException {
        UDPClient udpClient;
        if (args.length < 1)
            udpClient = new UDPClient();
        else if (args.length < 2)
            udpClient = new UDPClient(args[0]);
        else {
            udpClient = new UDPClient(args[0], Integer.parseInt(args[1]));
        }
        udpClient.launch();
        System.out.println(udpClient);
    }

    private final int _port;
    private final String _host;

    UDPClient() {
        this("localhost", 8439);
    }
    UDPClient(String host) {
        this(host, 8439);
    }
    UDPClient(String host, int port) {
        _port = port;
        _host = host;
    }

    /**
     * launch the client in order to read data from command line and send it to server
     */
    public void launch() throws SocketException {
        DatagramSocket _socket = new DatagramSocket();
        Console console = System.console();
        while (true) {
            try {
                // read data from console and send it to the server
                String inputLine = console.readLine();
                byte[] bytes = inputLine.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(_host), _port);
                _socket.send(datagramPacket);
            }
            catch (IOException | SecurityException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        _socket.close();
    }
}
