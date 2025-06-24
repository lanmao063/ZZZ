package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.LoginApplication;
import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class eggController {
    @FXML
    void doexit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认退出");
        alert.setHeaderText("您确定要退出吗？");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(LoginApplication.class.getResource("/cn/edu/neu/java_fundamental/login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = null;
                stage = GlobalData.primaryStage; // 设置全局变量
                stage.setTitle("登录");
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
                GlobalData.CURRENT_USER = null; // 清除当前用户信息
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
