package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.dao.Dispatchdao;
import cn.edu.neu.java_fundamental.dao.SupervisorSubmit;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.mynode.ClickableTextCell;
import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
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
        System.out.println("得到的数据量: "+allData.size());
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
            AQDataSupervisorGroup group=(AQDataSupervisorGroup) event.getSource();
            GlobalData.CURRENT_CHOOSE_AQ_DATA=group.data;
            try{
                URL fxmlLocation = getClass().getResource("/cn/edu/neu/java_fundamental/ChooseGrider.fxml");
                if (fxmlLocation == null) {
                    System.err.println("找不到 FXML 文件，请检查路径是否正确！");
                    new Alert(Alert.AlertType.ERROR, "无法找到 ChooseGrider.fxml 文件。").showAndWait();
                    return;
                }
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                AnchorPane anchorPane=loader.load();
                Scene scene=new Scene(anchorPane);
                GlobalData.secStage=new Stage();
                GlobalData.secStage.initModality(Modality.APPLICATION_MODAL);
                GlobalData.secStage.initStyle(StageStyle.UNDECORATED);
                GlobalData.secStage.setTitle("选择指派的网格员");
                GlobalData.secStage.setScene(scene);
                GlobalData.secStage.showAndWait();
                GlobalData.secStage.setResizable(false); // 禁止调整大小（可选）


                if(GlobalData.ERROR_CODE!=1){
                    System.out.println("添加成功");
                    submit.deleteData(group.ID,group.data);
                    dataList.remove(group);
                    TV_AQDSupervisor.setItems(dataList);
                    TV_AQDSupervisor.refresh();
                    System.out.println("删除已指派的记录成功");
                    GlobalData.ERROR_CODE=-1;

                }else{
                    System.out.println("添加失败");
                }

            } catch (IOException e) {
                System.out.println("指派失败"+e.getMessage());
            }

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

    }
}
