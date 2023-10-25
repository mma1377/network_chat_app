import java.io.IOException;
import java.net.Socket;
import java.net.DatagramSocket;

public class UDPServer {

    public static void main(String[] args) throws IOException {
        UDPServer udpServer;
        if(args.length < 1)
            udpServer = new UDPServer();
        else {
            udpServer = new UDPServer(Integer.parseInt(args[0]));
        }
        udpServer.launch();
    }
    private int _port;
    private final String _host = "127.0.0.1";

    UDPServer() {
        _port = 8439;
    }

    UDPServer(int listening_port) {
        _port = listening_port;
    }

    public void launch() throws IOException {
        DatagramSocket socket = new DatagramSocket(_port);


    }

    @Override
    public String toString() {
        return super.toString();
    }
}
