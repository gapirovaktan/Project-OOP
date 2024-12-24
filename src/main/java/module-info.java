module com.example.projectjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.projectjava to javafx.fxml;
    exports com.example.projectjava;
}