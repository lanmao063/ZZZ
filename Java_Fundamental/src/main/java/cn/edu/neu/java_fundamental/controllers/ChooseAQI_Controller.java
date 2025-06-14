package cn.edu.neu.java_fundamental.controllers;
import cn.edu.neu.java_fundamental.dao.AirQualityDataWrittenBySupervisordao;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.util.AirQualityLevel;

import cn.edu.neu.java_fundamental.util.AirQualityLevel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.Data;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class ChooseAQI_Controller {
    private List<Button> buttons;
    private Button ClickedButton;

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
    private TextField sp_city;

    @FXML
    private TextField sp_district;

    @FXML
    private TextField sp_province;

    @FXML
    public void initialize() {
        // 初始化按钮列表
        buttons = Arrays.asList(bt1, bt2, bt3, bt4, bt5, bt6);
    }

    @FXML
    void HandleButtonClick(ActionEvent event) {
        ClickedButton = (Button) event.getSource();
        for(Button bt : buttons){
            bt.setStyle("-fx-min-width:15px;-fx-min-height:15px;-fx-pref-width:15px;-fx-pref-height:15px;-fx-max-width:15px;-fx-max-height:15px;-fx-background-radius:50%");
        }
        ClickedButton.setStyle("-fx-background-color: #28c8f5;-fx-min-width:15px;-fx-min-height:15px;-fx-pref-width:15px;-fx-pref-height:15px;-fx-max-width:15px;-fx-max-height:15px;-fx-background-radius:50%");
    }

    @FXML
    void Submit(ActionEvent event) throws IOException {
        String province = sp_province.getText();
        String city = sp_city.getText();
        String district = sp_district.getText();
        Date date = new Date();
        String text = ClickedButton.getText();
        AirQualityLevel AQL = switch (text){
            case "1" -> AirQualityLevel.EXCELLENT;
            case "2" -> AirQualityLevel.GOOD;
            case "3" -> AirQualityLevel.LIGHT_POLLUTED;
            case "4" -> AirQualityLevel.MODERATE_POLLUTED;
            case "5" -> AirQualityLevel.HEAVY_POLLUTED;
            case "6" -> AirQualityLevel.SEVERE_POLLUTED;
            default -> null;
        };
        AirQualityDataWrittenBySupervisor airQualityDataWrittenBySupervisor = new AirQualityDataWrittenBySupervisor();
        airQualityDataWrittenBySupervisor.setProvince(province);
        airQualityDataWrittenBySupervisor.setCity(city);
        airQualityDataWrittenBySupervisor.setDistrict(district);
        airQualityDataWrittenBySupervisor.setDate(date);
        airQualityDataWrittenBySupervisor.setAQL(AQL);
        AirQualityDataWrittenBySupervisordao AQdao = new AirQualityDataWrittenBySupervisordao();
        try {
            int i = AQdao.addAirQualityDataWrittenBySupervisor(airQualityDataWrittenBySupervisor);
            if(i > 0){
                System.out.println("提交成功");
            }else {
                System.out.println("提交失败");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
