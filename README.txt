# UDP Client-Server Communication

This repository contains the implementation of a simple UDP client-server communication in Java. The communication is based on datagrams, where the server displays received strings on the standard output, and the client sends user-entered text lines to the server.

## Contents

- [UDPServer](#udpserver)
- [UDPClient](#udpclient)
- [How to Use](#how-to-use)
- [Testing](#testing)

## UDPServer

The `UDPServer` class represents a UDP server that receives datagrams containing strings encoded in UTF-8. The server displays the received string on the standard output, prefixed with the client's address. The server is capable of accepting strings up to 1024 bytes, truncating any data beyond this size.

### Constructors

- `UDPServer()`: Default constructor with a default listening port of 8439.
- `UDPServer(int listening_port)`: Constructor with a specified listening port.

### Methods

- `launch()`: Starts the server to listen for incoming datagrams.
- `toString()`: Returns a string describing the state of the server.

### Usage

To start the server, you can use the following command:

```bash
$ java UDPServer [port_number]


## UDPClient

The `UDPClient` class reads text lines entered by the user from the standard input and sends them as UTF-8 encoded UDP datagrams to the specified server.

### Constructors

- `UDPClient()`: Default constructor with default server address and port.
- `UDPClient(String host)`: Constructor with a specified server address and default port.
- `UDPClient(String host, int port)`: Constructor with both server address and port specified.

### Methods
- `launch()`: Starts the client to read user input and send datagrams.

Usage
To start the client, use the following command:

```bash
$ java UDPClient [server_address] [server_port]

## How to Use

Clone the repository:

```bash
$ git clone https://github.com/your-username/udp-client-server.git
$ cd udp-client-server

Compile the Java files:

```bash
$ javac UDPServer.java UDPClient.java

Run the server in one terminal:

```bash
$ java UDPServer [port_number]

In another terminal, run the client:

```bash
$ java UDPClient [server_address] [server_port]

Enter text lines in the client terminal to send them to the server.

## Testing

You can test the communication using tools like netcat (nc). For example:

```bash
$ nc -u localhost [port_number]

Enter text in the netcat terminal to send it to the server.


This `README.md` file provides a brief overview of the structure of your project and explains how to run the server and client and how to test their interaction.