package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static cn.edu.neu.java_fundamental.util.FXMLTools.ChineseRoleName;

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
    @FXML
    private void initialize() {
        user_nameLabel.setText(GlobalData.CURRENT_USER.getName());
        phone_numberLabel.setText(GlobalData.CURRENT_USER.getId());
        roleLabel.setText(ChineseRoleName(GlobalData.USER_ROLE) );
        scoreLabel.setText(String.valueOf(GlobalData.CURRENT_USER.getScore()));
    }
}
