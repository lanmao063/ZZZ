package cn.edu.neu.java_fundamental.controllers;
import cn.edu.neu.java_fundamental.dao.SupervisorSubmit;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.AirQualityLevel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static cn.edu.neu.java_fundamental.util.GlobalData.CURRENT_USER;


public class ChooseAQI_Controller {
    private List<Button> buttons;
    private Button ClickedButton;
    private Map<String, List<String>> provinceCityMap = new HashMap<>();

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
    private TextField sp_district;

    @FXML
    private ComboBox<String> sp_city;

    @FXML
    private ComboBox<String> sp_province;


    @FXML
    private TextField text1;

    @FXML
    public void initialize() throws IOException {
        // 初始化按钮列表
        buttons = Arrays.asList(bt1, bt2, bt3, bt4, bt5, bt6);
        ObjectMapper objectMapper = new ObjectMapper();
        provinceCityMap = objectMapper.readValue(new File("data/provinceandcity.json"),
                new TypeReference<Map<String, List<String>>>(){});
        // 添加省份到下拉框
        sp_province.getItems().addAll(provinceCityMap.keySet());
    }
    @FXML
    void onProvinceSelected(ActionEvent event) {
        String selectedProvince = sp_province.getValue();
        List<String> cities = provinceCityMap.getOrDefault(selectedProvince, List.of());

        sp_city.getItems().clear(); // 清除旧城市
        sp_city.getItems().addAll(cities);
        sp_city.setValue(null); // 重置选中项
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
        String province = sp_province.getValue();
        String city = sp_city.getValue();
        String district = sp_district.getText();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = date.format(formatter);
        String text2 = text1.getText();
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
        airQualityDataWrittenBySupervisor.setDate(formattedDate);
        airQualityDataWrittenBySupervisor.setAQL(AQL);
        airQualityDataWrittenBySupervisor.setText(text2);
        SupervisorSubmit AQdao = new SupervisorSubmit();
        try {
            int i = AQdao.addAirQualityData(CURRENT_USER, airQualityDataWrittenBySupervisor);
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
