package com.example.vcrSynergyDbelobokov;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;


public class TableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_add;

    @FXML
    private Button button_delete;

    @FXML
    private Button button_reboot;

    @FXML
    private Button button_update;

    @FXML
    private TableColumn<Device, String> col_Manufacturer;

    @FXML
    private TableColumn<Device, Integer> col_id;

    @FXML
    private TableColumn<Device, String> col_note;

    @FXML
    private TableColumn<Device, String> col_owner;

    @FXML
    private TableColumn<Device, String> col_statusRepair;

    @FXML
    private TableColumn<Device, String> col_typeDevice;

    @FXML
    private TableView<Device> table_device;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_manufacturer;

    @FXML
    private TextField txt_note;

    @FXML
    private TextField txt_owner;

    @FXML
    private TextField txt_statusRepair;

    @FXML
    private TextField txt_typeDevice;

    @FXML
    void Add_users(ActionEvent event) {
    }

    ObservableList<Device> listM;
    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void Add_users() {
        conn = MySqlConnect.ConnectDB();
        String sql = "INSERT INTO device (type_device,manufacturer,owner,status_repair,note)values(?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_typeDevice.getText());
            pst.setString(2, txt_manufacturer.getText());
            pst.setString(3, txt_owner.getText());
            pst.setString(4, txt_statusRepair.getText());
            pst.setString(5, txt_note.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Устройсво добавлено");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Edit() {
        try {
            conn = MySqlConnect.ConnectDB();
            String value_1 = txt_id.getText();
            String value_2 = txt_typeDevice.getText();
            String value_3 = txt_manufacturer.getText();
            String value_4 = txt_owner.getText();
            String value_5 = txt_statusRepair.getText();
            String value_6 = txt_note.getText();

            String sql = "UPDATE device set device_id= '" + value_1 + "',type_device= '" + value_2 + "',manufacturer= '" +
                    value_3 + "',owner= '" + value_4 + "',status_repair= '" + value_5 + "',note= '" + value_6 + "' where device_id='" + value_1 + "' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Редактирование выполнено");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void DeleteDevice() {
        conn = MySqlConnect.ConnectDB();
        String sql = "DELETE from device where device_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Устройство удалено");
            UpdateTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            throw new RuntimeException(e);
        }
    }

    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Device, Integer>("id"));
        col_typeDevice.setCellValueFactory(new PropertyValueFactory<Device, String>("typeDevice"));
        col_Manufacturer.setCellValueFactory(new PropertyValueFactory<Device, String>("manufacturer"));
        col_owner.setCellValueFactory(new PropertyValueFactory<Device, String>("owner"));
        col_statusRepair.setCellValueFactory(new PropertyValueFactory<Device, String>("statusRepair"));
        col_note.setCellValueFactory(new PropertyValueFactory<Device, String>("note"));

        listM = MySqlConnect.getDataUser();
        table_device.setItems(listM);
    }

    @FXML
    void initialize() {
        UpdateTable();
    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = table_device.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_typeDevice.setText(col_typeDevice.getCellData(index).toString());
        txt_manufacturer.setText(col_Manufacturer.getCellData(index).toString());
        txt_owner.setText(col_owner.getCellData(index).toString());
        txt_statusRepair.setText(col_statusRepair.getCellData(index).toString());
        txt_note.setText(col_note.getCellData(index).toString());
    }
}
