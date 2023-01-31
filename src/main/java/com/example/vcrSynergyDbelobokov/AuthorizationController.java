package com.example.vcrSynergyDbelobokov;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_reg;

    @FXML
    private TextField txt_firstName;

    @FXML
    private TextField txt_lastName;

    @FXML
    private TextField txt_loginAuth;

    @FXML
    private TextField txt_passwordAuth;

    @FXML
    private TextField txt_emailAuth;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void AddUser() {
        conn = MySqlConnect.ConnectDB();
        String sql = "INSERT INTO users (firstname,lastname,login,password,email)values(?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_firstName.getText());
            pst.setString(2, txt_lastName.getText());
            pst.setString(3, txt_loginAuth.getText());
            pst.setString(4, txt_passwordAuth.getText());
            pst.setString(5, txt_emailAuth.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, " Пользователь добавлен! ");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        button_reg.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {

    }

}
