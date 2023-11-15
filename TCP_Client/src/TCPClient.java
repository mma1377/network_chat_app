import java.io.*;
import java.net.*;
import java.util.Objects;

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
        System.out.println(tcpClient);
    }

    private int _port;
    private String _host;
    private Socket _socket;
    TCPClient() {
        _port = 8439;
        _host = "localhost";
    }
    TCPClient(String host) {
        _port = 8439;
        _host = host;
    }
    TCPClient(String host, int port) {
        _port = port;
        _host = host;
    }


    public void launch() throws IOException {
        _socket = new Socket(_host, _port);
        Console console = System.console();
        while (true) {
            String inputLine = console.readLine();
            OutputStream output = _socket.getOutputStream();
            byte[] data = inputLine.getBytes();
            output.write(data);
            PrintWriter writer = new PrintWriter(output, true);
        }
    }
}
