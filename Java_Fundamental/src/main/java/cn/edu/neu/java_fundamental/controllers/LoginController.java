package cn.edu.neu.java_fundamental.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Node;


import java.io.IOException;

public class LoginController {

    @FXML
    private TextField id;

    @FXML
    private Button login_button;

    @FXML
    private PasswordField password;

    @FXML
    private Button resignButton;

    public LoginController() throws IOException {
    }

    @FXML
    void doLogin(ActionEvent event) {

    }

    @FXML
    void doReg(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cn/edu/neu/java_fundamental/register.fxml"));
        GridPane gridPane = fxmlLoader.load();
        Scene scene = new Scene(gridPane, 400, 300);
        Stage stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("注册");
        stage.setWidth(400);
        stage.setScene(scene);
        stage.show();
    }


}
