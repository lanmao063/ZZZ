package cn.edu.neu.java_fundamental.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistController {
    public Stage stage;
    @FXML
    private Button regist;

    @FXML
    private TextField user_name;

    @FXML
    private PasswordField user_password;

    @FXML
    private TextField user_phonenumber;

    @FXML
    void handleButtonClick() throws IOException {

    }
}
