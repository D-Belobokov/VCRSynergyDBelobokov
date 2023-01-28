package com.example.tableusersrest;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;


public class TableViewController {
    @FXML
    private TableView<Users> table_users;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_add;

    @FXML
    private Button button_delete;

    @FXML
    private Button button_update;

    @FXML
    private TableColumn<Users, String> col_email;

    @FXML
    private TableColumn<Users, Integer> col_id;

    @FXML
    private TableColumn<Users, String> col_password;

    @FXML
    private TableColumn<Users, String> col_username;
    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_id;

    @FXML
    void Add_users(ActionEvent event) {
    }

    ObservableList<Users>listM;
    int index = -1;

    Connection conn3 = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void Add_users() {
        conn3 = MySqlConnect.ConnectDB();
        String sql = "INSERT INTO users (username,user_password,user_email)values(?,?,?)";
        try {
            pst = conn3.prepareStatement(sql);
            pst.setString(1,txt_username.getText());
            pst.setString(2,txt_password.getText());
            pst.setString(3,txt_email.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, " Пользователь добавлен ");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void Edit(){
        try {
            conn3 = MySqlConnect.ConnectDB();
            String value_1 = txt_id.getText();
            String value_2 = txt_username.getText();
            String value_3 = txt_password.getText();
            String value_4 = txt_email.getText();

            String sql = "update users set user_id= '"+value_1+"',username= '"+value_2+"',user_password= '"+
                    value_3+"',user_email= '"+value_4+"' where user_id='"+value_1+"' ";
            pst = conn3.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Редактирование выполнено");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }


    @FXML
    void initialize() {
        col_id.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<Users,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
        col_email.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));

        listM = MySqlConnect.getDataUser();
        table_users.setItems(listM);
        button_update.setOnAction(actionEvent -> {
            Edit();
        });

    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = table_users.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_username.setText(col_username.getCellData(index).toString());
        txt_password.setText(col_password.getCellData(index).toString());
        txt_email.setText(col_email.getCellData(index).toString());
    }

}
