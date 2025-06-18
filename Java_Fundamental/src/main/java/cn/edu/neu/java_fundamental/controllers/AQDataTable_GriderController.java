package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.dao.GriderSubmit;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import cn.edu.neu.java_fundamental.mynode.ClickableTextCell;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.Data;


import java.net.URL;
import java.util.*;


public class AQDataTable_GriderController implements Initializable {

    @FXML
    private TableView<AQDataGriderGroup> TV_AQData_Grider;

    @FXML
    private TableColumn<AQDataGriderGroup, String> GriderColumn;


    @FXML
    private TableColumn<AQDataGriderGroup,String> AQLColumn;

    @FXML
    private TableColumn<AQDataGriderGroup, String> AreaColumn;


    @FXML
    private TableColumn<AQDataGriderGroup, String> TimeColumn;

    @FXML
    private TableColumn<?, ?> space;

    @FXML
    private TableColumn<AQDataGriderGroup, String> OperatorColumn;

    @Data
    public  static class AQDataGriderGroup{
        private String griderID;
        private AirQualityDataWrittenByGrider data;

    }


    private ObservableList<AQDataGriderGroup> dataList= FXCollections.observableArrayList();
    private GriderSubmit griderSubmit=new GriderSubmit();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<AQDataGriderGroup> list=new ArrayList<>();
        Map<String , List<AirQualityDataWrittenByGrider>> allData=griderSubmit.getAllData();
       Set< String> griderIDs=allData.keySet();
       for(String griderID:griderIDs){
           for(AirQualityDataWrittenByGrider data:allData.get(griderID)){
               AQDataGriderGroup group=new AQDataGriderGroup();
               group.griderID=griderID;
               group.data=data;
               list.add(group);

           }
       }
       GriderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().griderID));
       AQLColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().data.getAQL().getChinese_explain()));
       AreaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().data.getProvince()+cellData.getValue().data.getCity()+cellData.getValue().data.getDistrict()));
       TimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().data.getDate().toString()));
       OperatorColumn.setCellValueFactory(cellData -> new SimpleStringProperty("删除这条数据"));
       OperatorColumn.setCellFactory(param-> new ClickableTextCell(event -> {
           AQDataGriderGroup group=(AQDataGriderGroup) event.getSource();
           System.out.println("管理员点击了信息删除字段");
           try{
           griderSubmit.deleteData(group.griderID,group.data);
           dataList.remove(group);
           TV_AQData_Grider.setItems(dataList);
           TV_AQData_Grider.refresh();
           System.out.println("删除成功");
           }
           catch (Exception e){
               System.out.println("删除失败");
               e.printStackTrace();
           }
       }));

       dataList.addAll(list);
       TV_AQData_Grider.setItems(dataList);
       TV_AQData_Grider.setEditable(true);


    }


}


