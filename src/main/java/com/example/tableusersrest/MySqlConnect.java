package com.example.tableusersrest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlConnect {
    Connection conn = null;
    public static Connection ConnectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mynavigator_db",
                    "root","root");
            JOptionPane.showMessageDialog(null, "Connection successfully");
            return conn1;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public static ObservableList<Users> getDataUser(){
        Connection conn2 = ConnectDB();
        ObservableList<Users>list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn2.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Users(Integer.parseInt(rs.getString("user_id")),
                        rs.getString("username"),
                        rs.getString("user_password"),
                        rs.getString("user_email")));
            }
        }catch (Exception e){

        }
        return list;
    }
}
