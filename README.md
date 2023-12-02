# Chat Application - UDP and TCP Communication

This project implements a chat application that enables communication between computers using both UDP and TCP protocols. It consists of server and client components for UDP and TCP, as well as a graphical user interface for the client.
For the UDP part, we have a single-directional communication.
The TCP protocol is used to make a fully functional chat app. You can also use a graphical interface for the client side.

You can find the documentions [here](https://mma1377.github.io/network_chat_app/).

## Project Structure

### UDP Communication

#### UDP Server (UDPServer.java)
- Listens for UDP messages on a specified port (default: 8439).

#### UDP Client (UDPClient.java)
- Sends UDP messages to the server on a specified host and port (default: localhost:8439).

### TCP Communication

#### TCP Server (TCPServer.java)
- Listens for TCP connections on a specified port (default: 8439).
- Accepts multiple client connections and echoes messages to all clients.

#### TCP Client (TCPClient.java)
- Connects to the TCP server on a specified host and port (default: localhost:8439).
- Allows user input through the console and sends it to the server. It can be used as a single thread-blocking mode (used in the terminal by default) or multi-thread to send and receive data asynchronously (used in graphical mode). 

## How to Compile

To compile the project, ensure you have Java installed and execute the following commands:

### Compile UDP classes
```bash
javac com/ensea_chatapp_udp/Server/UDPServer.java
javac com/ensea_chatapp_udp/Client/UDPClient.java
```

### Compile TCP classes
```bash
javac com/ensea_chatapp_tcp/Server/TCPServer.java
javac com/ensea_chatapp_tcp/Client/TCPClient.java
```

### Compile GUI class
```bash
javac --module-path javafx-dir\javafx-sdk-21.0.1\lib --add-modules javafx.controls com/ensea_chatapp_gui/ChatApplication.java
```

## How to Run

### Run UDP Server
```bash
java com.ensea_chatapp_udp.Server.UDPServer [port]
```

### Run UDP Client
```bash
java com.ensea_chatapp_udp.Client.UDPClient [host] [port]
```

### Run TCP Server
```bash
java com.ensea_chatapp_tcp.Server.TCPServer [port]
```

### Run TCP Client
```bash
java com.ensea_chatapp_tcp.Client.TCPClient [host] [port]
```

### Run GUI Client
```bash
java --module-path javafx-dirs\javafx-sdk-21.0.1\lib --add-modules javafx.controls com.ensea_chatapp_gui.ChatApplication 
```

## Run pre-compiled jar files
You can also use pre-compiled jar files to use functionalities.
### Run TCP Server
```bash
java -jar Client_GUI.jar  
```
### Run TCP Client 
```bash
java -jar Server_GUI.jar  
```

### Run TCP Graphical Interface for Client
```bash
java -jar Client_GUI.jar  
```

### Graphical Interface

#### GUI (HelloApplication.java)
- Provides a graphical user interface for the TCP client.
- Allows users to connect to the server by specifying the address.
- Displays chat messages
- Allows users to input messages and send them to the server.
