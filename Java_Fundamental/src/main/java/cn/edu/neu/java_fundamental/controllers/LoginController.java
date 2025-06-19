package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.LoginApplication;
import cn.edu.neu.java_fundamental.dao.Supervisordao;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.GlobalData;
import cn.edu.neu.java_fundamental.dao.Supervisordao;

import cn.edu.neu.java_fundamental.util.LoginTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Node;



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
        String phone_number = id.getText();
        String pwd = password.getText();
        Supervisordao dao= new Supervisordao();
        try {
            LoginTool loginTool = new LoginTool();
            if (loginTool.login(phone_number,pwd)) {
                System.out.println("登录成功");
                FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("MainView.fxml"));
                BorderPane borderPane = (BorderPane) fxmlLoader.load();
                Scene scene = new Scene(borderPane, GlobalData.WIDTH, GlobalData.HEIGHT);
                GlobalData.primaryStage.setScene(scene);
                GlobalData.primaryStage.setTitle("东软环保公众监督系统");
                GlobalData.primaryStage.centerOnScreen();
                GlobalData.primaryStage.show();
            } else
                System.out.println("登录失败，请检查用户名或密码");

        } catch (IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void doReg(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cn/edu/neu/java_fundamental/register.fxml"));
        GridPane gridPane = fxmlLoader.load();
        Scene scene = new Scene(gridPane, 700, 500);
        Stage stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("注册");
        stage.setWidth(700);
        stage.setHeight(500);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


}
