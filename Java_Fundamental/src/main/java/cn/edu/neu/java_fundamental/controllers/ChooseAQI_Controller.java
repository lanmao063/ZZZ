package cn.edu.neu.java_fundamental.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Arrays;
import java.util.List;


public class ChooseAQI_Controller {
    private List<Button> buttons;

    @FXML
    private Button bt1;

    @FXML
    private Button bt2;

    @FXML
    private Button bt3;

    @FXML
    private Button bt4;

    @FXML
    private Button bt5;

    @FXML
    private Button bt6;

    @FXML
    private Button submit;

    @FXML
    void Submit(ActionEvent event) {

    }
    @FXML
    public void initialize() {
        // 初始化按钮列表
        buttons = Arrays.asList(bt1, bt2, bt3, bt4, bt5, bt6);
    }

    @FXML
    void HandleButtonClick(ActionEvent event) {
        Button ClickedButton = (Button) event.getSource();
        for(Button bt : buttons){
            bt.setStyle("-fx-min-width:15px;-fx-min-height:15px;-fx-pref-width:15px;-fx-pref-height:15px;-fx-max-width:15px;-fx-max-height:15px;-fx-background-radius:50%");
        }
        ClickedButton.setStyle("-fx-background-color: #28c8f5;-fx-min-width:15px;-fx-min-height:15px;-fx-pref-width:15px;-fx-pref-height:15px;-fx-max-width:15px;-fx-max-height:15px;-fx-background-radius:50%");
    }

}
