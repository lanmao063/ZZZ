package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.dao.SupervisorSubmit;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.util.AirQualityLevel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.util.Map;

import static cn.edu.neu.java_fundamental.util.GlobalData.CURRENT_USER;

public class HistoryController {
    SupervisorSubmit supervisorSubmit = new SupervisorSubmit();
    Map<String, List<AirQualityDataWrittenBySupervisor>>airQualityDataWrittenBySupervisor = supervisorSubmit.getAllData();


    @FXML
    private TableColumn<AirQualityDataWrittenBySupervisor, AirQualityLevel> h_aqi;

    @FXML
    private TableColumn<AirQualityDataWrittenBySupervisor, String> h_area;

    @FXML
    private TableColumn<AirQualityDataWrittenBySupervisor, String> h_city;

    @FXML
    private TableColumn<AirQualityDataWrittenBySupervisor, String> h_date;

    @FXML
    private TableColumn<AirQualityDataWrittenBySupervisor, String> h_province;

    @FXML
    private TableColumn<AirQualityDataWrittenBySupervisor, String> h_text;

    @FXML
    private TableView<AirQualityDataWrittenBySupervisor> tabelview;
    public void initialize() {
        h_aqi.setCellValueFactory(new PropertyValueFactory<>("AQL"));
        h_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        h_province.setCellValueFactory(new PropertyValueFactory<>("province"));
        h_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        h_area.setCellValueFactory(new PropertyValueFactory<>("district"));
        h_text.setCellValueFactory(new PropertyValueFactory<>("text"));

        List<AirQualityDataWrittenBySupervisor> allData = airQualityDataWrittenBySupervisor.get(CURRENT_USER.getId());
        tabelview.setItems(FXCollections.observableArrayList(allData));
    }
}
