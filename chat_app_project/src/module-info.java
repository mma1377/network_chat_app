module com.ensea_chatapp_gui {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ensea_chatapp_gui to javafx.fxml;
    exports com.ensea_chatapp_gui;
}