package cn.edu.neu.java_fundamental.controllers;
import cn.edu.neu.java_fundamental.dao.Supervisordao;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {

        @FXML
        private Button registbutton;

        @FXML
        private TextField user_name;

        @FXML
        private PasswordField user_password;

        @FXML
        private TextField user_phonenumber;

        @FXML
        void doregist(ActionEvent event) {
                String name = user_name.getText();
                String password = user_password.getText();
                String phoneNumber = user_phonenumber.getText();
                if (name.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
                        System.out.println("请填写完整信息");
                        return;
                }
                if( !phoneNumber.matches("\\d{11}")) {
                        System.out.println("请输入正确的手机号");
                        return;
                }
                if (password.length() < 6) {
                        System.out.println("密码长度不能小于6位");
                        return;
                }
                Supervisor supervisor = new Supervisor();
                supervisor.setName(name);
                supervisor.setPassword(password);
                supervisor.setId(phoneNumber);
                Supervisordao dao =new Supervisordao();
                try {
                        int i = dao.addSupervisor(supervisor);
                        if (i > 0)
                                System.out.println("录入成功");
                        else System.out.println("录入失败 ");
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }

    }


