package com.example.vcrSynergyDbelobokov;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_auth;

    @FXML
    private Button buttonSingUp;


    @FXML
    private TextField txt_login;

    @FXML
    private TextField txt_password;

    Connection conn = null;
    ResultSet rst = null;
    PreparedStatement prst = null;


    @FXML
    void initialize() {
        button_auth.setOnAction(actionEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("authorization-view.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    private void SingUpUser() {
        conn = MySqlConnect.ConnectDB();
        String sql = "SELECT * from users where login = ? and password = ?";
        try {
            prst = conn.prepareStatement(sql);
            prst.setString(1, txt_login.getText());
            prst.setString(2, txt_password.getText());
            rst = prst.executeQuery();
            if (rst.next()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tableDevice-view.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else JOptionPane.showMessageDialog(null, "!неправильный логин или пароль!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        buttonSingUp.getScene().getWindow().hide();
    }
}
