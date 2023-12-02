# Chat Application - UDP and TCP Communication

This project implements a chat application that enables communication between computers using both UDP and TCP protocols. It consists of server and client components for UDP and TCP, as well as a graphical user interface for the client.

## Project Structure

### UDP Communication

#### UDP Server (UDPServer.java)
- Listens for UDP messages on a specified port (default: 8439).

#### UDP Client (UDPClient.java)
- Sends UDP messages to the server on a specified host and port (default: localhost:8439).

### TCP Communication

#### TCP Server (TCPServer.java)
- Listens for TCP connections on a specified port (default: 8439).
- Accepts multiple client connections and broadcasts messages.

#### TCP Client (TCPClient.java)
- Connects to the TCP server on a specified host and port (default: localhost:8439).
- Allows user input through the console.

### Graphical Interface

#### GUI (HelloApplication.java)
- Provides a graphical user interface for the TCP client.
- Displays chat messages in a scrollable window.
- Allows users to input messages and send them to the server.

## How to Compile

To compile the project, ensure you have Java installed and execute the following commands:

```bash
### Compile UDP classes
javac com/ensea_chatapp_udp/Server/UDPServer.java
javac com/ensea_chatapp_udp/Client/UDPClient.java

### Compile TCP classes
javac com/ensea_chatapp_tcp/Server/TCPServer.java
javac com/ensea_chatapp_tcp/Server/ConnectionThread.java
javac com/ensea_chatapp_tcp/Client/TCPClient.java
javac com/ensea_chatapp_tcp/Client/FetchThread.java

### Compile GUI class
javac com/ensea_chatapp_gui/HelloApplication.java

## How to Run

### Run UDP Server
```bash
java com.ensea_chatapp_udp.Server.UDPServer [port]

### Run UDP Client
```bash
java com.ensea_chatapp_udp.Client.UDPClient [host] [port]

### Run TCP Server
```bash
java com.ensea_chatapp_tcp.Server.TCPServer [port]

### Run TCP Client (Console)
```bash
java com.ensea_chatapp_tcp.Client.TCPClient [host] [port]

### Run GUI Client
```bash
java com.ensea_chatapp_gui.HelloApplication

#### GUI Interface:

The GUI window will appear with a label to display chat messages.
Type your message in the text field at the bottom.
Press the "Send" button or hit the Enter key to send your message.

#### Viewing Messages:

Sent and received messages will be displayed in the label area.
The scroll pane allows you to view past messages.

#### Closing the Application:

Close the GUI window to exit the application.

This README provides a high-level overview of the project, explains the components, and gives instructions on how to compile and run each part of the application.
