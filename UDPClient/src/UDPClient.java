import java.io.IOException;
import java.net.DatagramSocket;

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
}
