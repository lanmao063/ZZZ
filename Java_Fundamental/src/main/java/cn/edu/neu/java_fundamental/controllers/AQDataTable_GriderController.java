package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.dao.AirQualityDataWrittenByGriderdao;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AQDataTable_GriderController implements Initializable {

    @FXML
    private TableView<AirQualityDataWrittenByGrider> TV_AQData_Grider;

    @FXML
    private TableColumn<AirQualityDataWrittenByGrider, String> GriderColumn;


    @FXML
    private TableColumn<AirQualityDataWrittenByGrider,String> AQLColumn;

    @FXML
    private TableColumn<AirQualityDataWrittenByGrider, String> AreaColumn;


    @FXML
    private TableColumn<AirQualityDataWrittenByGrider, String> TimeColumn;

    @FXML
    private TableColumn<?, ?> space;

    @FXML
    private TableColumn<AirQualityDataWrittenByGrider, ?> OperatorColumn;

    private ObservableList<AirQualityDataWrittenByGrider> dataList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<AirQualityDataWrittenByGrider> list = new AirQualityDataWrittenByGriderdao().getAllData();
        GriderColumn.setCellValueFactory(cellData->new
                SimpleStringProperty(cellData.getValue().getSubmitter().getName()));
        AQLColumn.setCellValueFactory(cellData->new
                SimpleStringProperty(cellData.getValue().getAQL().getChinese_explain()));
        AreaColumn.setCellValueFactory(cellData->new
                SimpleStringProperty(cellData.getValue().getProvince()+cellData.getValue().getCity()+cellData.getValue().getDistrict()));
        TimeColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getDate().toString()));

        dataList.addAll(list);
        TV_AQData_Grider.setItems(dataList);


    }


}


