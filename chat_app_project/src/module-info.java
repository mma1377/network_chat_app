module com {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    opens com.ensea_chatapp_gui to javafx.fxml;
    exports com.ensea_chatapp_tcp.Server;
    exports com.ensea_chatapp_tcp.Client;
    exports com.ensea_chatapp_udp.Server;
    exports com.ensea_chatapp_udp.Client;
    exports com.ensea_chatapp_gui;
    exports com.ensea_chatapp_test.TCPServer;
    exports com.ensea_chatapp_test.TCPClient;
}