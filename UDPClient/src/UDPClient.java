import java.io.Console;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Objects;

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

    private int _port;
    private String _host;
    private DatagramSocket _socket;
    UDPClient() {
        _port = 8439;
        _host = "localhost";
    }
    UDPClient(String host) {
        _port = 8439;
        _host = host;
    }
    UDPClient(String host, int port) {
        _port = port;
        _host = host;
    }


    public void launch() throws IOException {
        _socket = new DatagramSocket();
        Console console = System.console();
        while (true) {
            String inputLine = console.readLine();
            byte[] bytes = inputLine.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(_host), _port);
            _socket.send(datagramPacket);
        }

    }
}
