package com.ensea_chatapp_gui;

import com.ensea_chatapp_tcp.Client.TCPClient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


/**
 * This the graphical interface which is a subclass of javafx application class
 * This will run a graphical interface which receives ip and port from user in order to connect to
 * the chat app server
 *  */
public class ChatApplication extends Application {
    private Label _chatTexts;
    private TextField _chatTextEntry;
    private TCPClient _TCPClient;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start primary scene
     * @param stage stage graphical scene will run on. This class overrides start method from
     *              javafx Application abstract class
     */
    @Override
    public void start(Stage stage) {

        //Initialization
        Label label = new Label("Enter Server IP and Port");
        TextField IpEntry = new TextField("localhost");
        TextField PortEntry = new TextField("8439");
        VBox entries = new VBox(IpEntry, PortEntry);
        VBox entriesLabelBox = new VBox(label, entries);
        Button connectButton = new Button("Connect");
        VBox root = new VBox(entriesLabelBox, connectButton);

        //Styling the graphics
        label.setStyle("-fx-font-size: 16px;");
        IpEntry.setPromptText("Enter server IP...");
        PortEntry.setPromptText("Enter server Port...");
        root.setAlignment(Pos.BOTTOM_CENTER);
        connectButton.setPrefWidth(300);
        connectButton.setPrefHeight(50);
        IpEntry.setPrefWidth(300);
        IpEntry.setPrefHeight(50);
        PortEntry.setPrefWidth(300);
        PortEntry.setPrefHeight(50);
        Scene scene = new Scene(root, 300, 200);

        //Adding handlers
        connectButton.setOnAction(event -> connect(IpEntry.getText(), PortEntry.getText(), stage));

        //Launch and Showing
        stage.setTitle("ChatApp");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * start chat scene
     * @param stage stage which previous graphical scene was running on
     */
    private void chatScene(Stage stage, String ip, String port) throws IOException, InterruptedException {

        //Initialization
        _TCPClient = new TCPClient(ip, Integer.parseInt(port));
        _chatTexts = new Label();
        _chatTextEntry = new TextField("");
        Button sendButton = new Button("Send");
        ScrollPane scrollPane = new ScrollPane();
        HBox inputBox = new HBox(_chatTextEntry, sendButton);
        VBox root = new VBox(scrollPane, inputBox);
        Scene scene = new Scene(root, 400, 600);

        //Styling the graphics
        _chatTexts.setWrapText(true);
        _chatTexts.setStyle("-fx-font-size: 16px;");
        scrollPane.setContent(_chatTexts);
        _chatTexts.setText("");
        scrollPane.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
        _chatTextEntry.setPromptText("Type here...");
        root.setAlignment(Pos.BOTTOM_LEFT);
        inputBox.setPrefWidth(400);
        inputBox.setPrefHeight(50);
        inputBox.setMinHeight(50);
        sendButton.setPrefWidth(100);
        sendButton.setPrefHeight(50);
        _chatTextEntry.setPrefWidth(300);
        _chatTextEntry.setPrefHeight(50);

        //Adding handlers
        _chatTexts.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0);
        });
        scene.setOnKeyPressed(event -> {
            if (Objects.requireNonNull(event.getCode()) == KeyCode.ENTER) {
                submitData();
            }
        });
        sendButton.setOnAction(event -> submitData());

        //Launch and Showing
        _TCPClient.launch();
        _TCPClient.asynchronous_fetch(this::changeLabel);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * run chat scene which will make a connection to server and display the chat page
     * @param ip server ip address
     * @param port server port address
     * @param stage stage which current graphical scene is running on
     */
    private void connect(String ip, String port, Stage stage)  {
        try {
            chatScene(stage, ip, port);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * append the new data fetched from server to chat texts in order to display
     * @param newText text fetched from the server
     */
    private void changeLabel(String newText) {
        Platform.runLater(() -> {
            _chatTexts.setText(_chatTexts.getText() + newText);
        });
    }

    /**
     * send data read from chat entry to server
     */
    private void submitData(){
        try {
            _TCPClient.send_data(_chatTextEntry.getText());
            _chatTextEntry.clear();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
