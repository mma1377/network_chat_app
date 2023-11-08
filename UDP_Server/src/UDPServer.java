import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Arrays;

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
    private int _port;
    private DatagramSocket _socket;

    UDPServer() {
        _port = 8439;
    }

    UDPServer(int listening_port) {
        _port = listening_port;
    }

    public void launch() throws IOException {
        _socket = new DatagramSocket(_port);
        Console console = System.console();

        while (true) {
            byte[] byteBuffer = new byte[8];
            DatagramPacket datagramPacket = new DatagramPacket(byteBuffer, 8);
            _socket.receive(datagramPacket);
            String receivedStr = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            System.out.println(receivedStr);
            String inputLine = console.readLine();
            if (Objects.equals(inputLine, "exit"))
                break;
            else if (Objects.equals(inputLine, "state"))
                System.out.println(this);

        }

    }

    @Override
    public String toString() {
        if (_socket != null && !_socket.isClosed())
            return "The server is running on port: " + _socket.getLocalPort();
        else
            return "The server is not running";
    }
}
