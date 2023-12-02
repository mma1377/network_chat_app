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


public class ChatApplication extends Application {
    Label _label;
    TextField _textField;
    TCPClient _TCPClient;


    public void chatScene(Stage stage, String ip, String port) throws IOException, InterruptedException {
        _TCPClient = new TCPClient(ip, Integer.parseInt(port));
        _TCPClient.graphical_launch();
        _label = new Label("ChatApp");
        _label.setWrapText(true);
        _label.setStyle("-fx-font-size: 16px;");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(_label);
        scrollPane.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
        _label.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0);
        });
        _textField = new TextField("");
        _textField.setPromptText("Type here...");
        Button button = new Button("Send");
        button.setOnAction(event -> submitData());
        HBox inputBox = new HBox(_textField, button);
        VBox root = new VBox(scrollPane, inputBox);
        root.setAlignment(Pos.BOTTOM_LEFT);
        inputBox.setPrefWidth(400);
        inputBox.setPrefHeight(50);
        inputBox.setMinHeight(50);
        button.setPrefWidth(100);
        button.setPrefHeight(50);
        _textField.setPrefWidth(300);
        _textField.setPrefHeight(50);
        Scene scene = new Scene(root, 400, 600);
        scene.setOnKeyPressed(event -> {
            if (Objects.requireNonNull(event.getCode()) == KeyCode.ENTER) {
                submitData();
            }
        });
        _label.setText("");
        _TCPClient.graphical_fetch(this::changeLabel);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        Label label = new Label("ChatApp");
        label.setText("Enter Server IP and Port");
        label.setStyle("-fx-font-size: 16px;");
        TextField textFieldIp = new TextField("localhost");
        textFieldIp.setText("localhost");
        textFieldIp.setPromptText("Enter server IP...");
        TextField textFieldPort = new TextField();
        textFieldPort.setText("8439");
        textFieldPort.setPromptText("Enter server Port...");
        Button button = new Button("Connect");
        button.setOnAction(event -> connect(textFieldIp.getText(), textFieldPort.getText(), stage));
        VBox textsFields = new VBox(textFieldIp, textFieldPort);
        VBox labelTextBox = new VBox(label, textsFields);
        VBox root = new VBox(labelTextBox, button);
        root.setAlignment(Pos.BOTTOM_CENTER);
        button.setPrefWidth(300);
        button.setPrefHeight(50);
        textFieldIp.setPrefWidth(300);
        textFieldIp.setPrefHeight(50);
        textFieldPort.setPrefWidth(300);
        textFieldPort.setPrefHeight(50);
        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("ChatApp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void connect(String ip, String port, Stage stage)  {
        try {
            chatScene(stage, ip, port);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeLabel(String str) {
        Platform.runLater(() -> {
            _label.setText(_label.getText() + str);
        });
    }

    public void submitData(){
        try {
            _TCPClient.send_data(_textField.getText());
            _textField.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
