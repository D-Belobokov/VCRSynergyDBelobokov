package com.example.vcrSynergyDbelobokov;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlConnect {
    Connection conn = null;

    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mynavigator_db",
                    "root", "root");
            return conn1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static ObservableList<Device> getDataUser() {
        Connection conn2 = ConnectDB();
        ObservableList<Device> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn2.prepareStatement("SELECT * from device");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Device(Integer.parseInt(rs.getString("device_id")),
                        rs.getString("type_device"),
                        rs.getString("manufacturer"),
                        rs.getString("owner"),
                        rs.getString("status_repair"),
                        rs.getString("note")));

            }
        } catch (Exception e) {

        }
        return list;
    }
}
