module com.example.tableusersrest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.tableusersrest to javafx.fxml;
    exports com.example.tableusersrest;
}