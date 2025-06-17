package cn.edu.neu.java_fundamental.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PersonalPageController {
    @FXML
    private Label phone_numberLabel;

    @FXML
    private Button renewPersonalfile_btn;

    @FXML
    private Button renewPwd_btn;

    @FXML
    private Label roleLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label user_nameLabel;

    @FXML
    void doRenewPersonalFile(ActionEvent event) {

    }

    @FXML
    void doRenewPwd(ActionEvent event) {

    }
    private void initialize() {
        // 初始化个人信息
        user_nameLabel.setText("张三");
        phone_numberLabel.setText("1234567890");
        roleLabel.setText("管理员");
        scoreLabel.setText("100");
    }
}
