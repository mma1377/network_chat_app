import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

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

    TCPServer() {
        _port = 8439;
    }

    TCPServer(int listening_port) {
        _port = listening_port;
    }

    public void launch() throws IOException {
        _serverSocket = new ServerSocket(_port);
        Socket socket = _serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[4096];
        int bytes_read;
        while ((bytes_read = inputStream.read(buf)) != -1){
            System.out.println(new String(buf, 0, bytes_read));
            buf = new byte[4096];
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
