package cn.edu.neu.java_fundamental.controllers;
import cn.edu.neu.java_fundamental.dao.Admindao;
import cn.edu.neu.java_fundamental.dao.Griderdao;
import cn.edu.neu.java_fundamental.dao.Supervisordao;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

import javafx.scene.control.ToggleGroup;



public class RegisterController {
    @FXML
        private ToggleGroup user_sex;
    @FXML
        private Button registbutton;

        @FXML
        private TextField user_name;

        @FXML
        private PasswordField user_password;

        @FXML
        private TextField user_passworda;


        @FXML
        private TextField user_phonenumber;
        public boolean isDuplicate() {
                List<Supervisor> allUsers = new ArrayList();
                Supervisordao s = new Supervisordao();
                Griderdao g = new Griderdao();
                Admindao a = new Admindao();
                allUsers.addAll(s.getAllSupervisors());
                allUsers.addAll(g.getAllGriders());
                allUsers.addAll(a.getAllAdministrators());
                Set<String> phonenumbers = new HashSet<>();
                for (Supervisor user :  allUsers ) {
                        phonenumbers.add(user.getId());
                }
            return phonenumbers.contains(user_phonenumber.getText());
        }
        @FXML
        void doregist(ActionEvent event) {

                if((isValied()) && (!isDuplicate())) {
                    Supervisor supervisor = new Supervisor();
                    supervisor.setName(user_name.getText());
                    supervisor.setPassword(user_password.getText());
                    supervisor.setId(user_phonenumber.getText());
                    RadioButton selectedRadioButton = (RadioButton) user_sex.getSelectedToggle();
                    supervisor.setSex(selectedRadioButton.getText());
                    Supervisordao dao = new Supervisordao();
                    try {
                        int i = dao.addSupervisor(supervisor);
                        if (i > 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("提示");
                            alert.setHeaderText("注册成功");
                            alert.showAndWait();
                            System.out.println("注册成功");
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cn/edu/neu/java_fundamental/login.fxml"));
                            BorderPane borderPane = fxmlLoader.load();
                            Scene scene = new Scene(borderPane, 700, 500);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setTitle("注册");
                            stage.setWidth(700);
                            stage.setHeight(500);
                            stage.setScene(scene);
                            stage.centerOnScreen();
                            stage.show();
                        } else System.out.println("注册失败 ");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        }
        private boolean isValied() {
            String name = user_name.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (name.isEmpty()) {
                alert.setTitle("提示");
                alert.setHeaderText("用户名不能为空");
                alert.showAndWait();
                return false;
            }
            String phoneNumber = user_phonenumber.getText();
            if (phoneNumber.length() != 11 || !phoneNumber.matches("\\d+")) {
                alert.setTitle("提示");
                alert.setHeaderText("请输入正确的手机号");
                user_phonenumber.clear();
                alert.showAndWait();
                return false;
            }
            String password = user_password.getText();
            if ((password.length() < 6)) {
                alert.setTitle("提示");
                alert.setHeaderText("密码长度不能小于6位");
                user_password.clear();
                alert.showAndWait();
                return false;
            }
            String passworda = user_passworda.getText();
            if (!password.equals(passworda)) {
                alert.setTitle("提示");
                alert.setHeaderText("两次输入的密码不一致");
                user_password.clear();
                user_passworda.clear();
                alert.showAndWait();
                return false;
            }
            Toggle selectedToggle = user_sex.getSelectedToggle();
            if (selectedToggle == null) {
                alert.setTitle("提示");
                alert.setHeaderText("请选择性别");
                return false;
            }
            return true;
        }

    }


