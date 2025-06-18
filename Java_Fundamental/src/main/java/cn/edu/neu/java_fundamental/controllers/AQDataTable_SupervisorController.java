package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.dao.SupervisorSubmit;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.mynode.ClickableTextCell;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.*;

public class AQDataTable_SupervisorController  implements Initializable {
    @FXML
    private TableColumn<AQDataSupervisorGroup, String> AreaColumn;

    @FXML
    private TableColumn<AQDataSupervisorGroup, String> SupervisorColumn;

    @FXML
    private TableView<AQDataSupervisorGroup> TV_AQDSupervisor;

    @FXML
    private Label Title;

    @FXML
    private TableColumn<AQDataSupervisorGroup, String> deleteColumn;

    @FXML
    private TableColumn<AQDataSupervisorGroup, String> dispatchColumn;

    @FXML
    private TableColumn<AQDataSupervisorGroup, String> pollutionClassColumn;

    @FXML
    private TableColumn<AQDataSupervisorGroup, String> timeColumn;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AQDataSupervisorGroup{
        private String ID;
        private AirQualityDataWrittenBySupervisor data;
    }

    private ObservableList<AQDataSupervisorGroup> dataList= FXCollections.observableArrayList();
    private SupervisorSubmit submit= new SupervisorSubmit();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        List<AQDataSupervisorGroup> list=new ArrayList<>();
        Map<String , List<AirQualityDataWrittenBySupervisor>> allData=submit.getAllData();
        Set< String> IDs=allData.keySet();
        for(String ID:IDs){
            for(AirQualityDataWrittenBySupervisor data:allData.get(ID)){
                AQDataSupervisorGroup group=new AQDataSupervisorGroup();
                group.ID=ID;
                group.data=data;
                list.add(group);

            }
        }
        SupervisorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().ID));
        pollutionClassColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().data.getAQL().getChinese_explain()));
        AreaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().data.getProvince()+cellData.getValue().data.getCity()+cellData.getValue().data.getDistrict()));
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().data.getDate().toString()));
        dispatchColumn.setCellValueFactory(cellData -> new SimpleStringProperty("指派网格员"));
        dispatchColumn.setCellFactory(param->new ClickableTextCell(event->{

            System.out.println("管理员点击了指派网格员");
        }));

        deleteColumn.setCellValueFactory(cellData -> new SimpleStringProperty("删除这条记录"));
        deleteColumn.setCellFactory(param->new ClickableTextCell(event->{
            System.out.println("管理员点击了信息删除字段");
            AQDataSupervisorGroup group=(AQDataSupervisorGroup) event.getSource();
            try{
                submit.deleteData(group.ID,group.data);
                dataList.remove(group);
                TV_AQDSupervisor.setItems(dataList);
                TV_AQDSupervisor.refresh();
                System.out.println("删除成功");

            }
            catch (Exception e){
                System.out.println("删除失败");
                e.printStackTrace();
            }
        }));

        dataList.addAll(list);
        TV_AQDSupervisor.setItems(dataList);
        TV_AQDSupervisor.setEditable(true);
    }
}
