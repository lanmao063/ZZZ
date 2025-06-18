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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;



public class RegisterController {

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
                for (String id : phonenumbers) {
                        if (user_phonenumber.getText().equals(id)) {
                                return true;
                        }
                }
                return false;
        }
        @FXML
        void doregist(ActionEvent event) {
                if(user_password.getText().equals(user_passworda.getText()) && (!isDuplicate())) {
                        String name = user_name.getText();
                        String password = user_password.getText();
                        String phoneNumber = user_phonenumber.getText();
                        Supervisor supervisor = new Supervisor();
                        supervisor.setName(name);
                        supervisor.setPassword(password);
                        supervisor.setId(phoneNumber);
                        Supervisordao dao = new Supervisordao();
                        try {
                                int i = dao.addSupervisor(supervisor);
                                if (i > 0) {
                                        System.out.println("注册成功");
                                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cn/edu/neu/java_fundamental/login.fxml"));
                                        BorderPane borderPane = fxmlLoader.load();
                                        Scene scene = new Scene(borderPane, 700, 500);
                                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        stage.setTitle("注册");
                                        stage.setWidth(700);
                                        stage.setScene(scene);
                                        stage.show();
                                } else System.out.println("注册失败 ");
                        } catch (IOException e) {
                                throw new RuntimeException(e);
                        }
                }
        }

    }


