module com.example.tableusersrest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires lombok;
    requires org.testng;


    opens com.example.vcrSynergyDbelobokov to javafx.fxml;
    exports com.example.vcrSynergyDbelobokov;
}