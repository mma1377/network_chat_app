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
import com.ensea_chatapp_tcp.Client.TCPClient;
import com.ensea_chatapp_tcp.Client.MyFunction;

import java.io.IOException;
import java.util.Objects;


public class HelloApplication extends Application {
    Label _label;
    TextField _textField;
    TCPClient _TCPClient;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        _TCPClient = new TCPClient();
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
        stage.setTitle("ChatApp");
        stage.setScene(scene);
        stage.show();
        _label.setText("");
        _TCPClient.graphical_fetch(this::changeLabel);
    }

    public static void main(String[] args) {
        launch(args);
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
