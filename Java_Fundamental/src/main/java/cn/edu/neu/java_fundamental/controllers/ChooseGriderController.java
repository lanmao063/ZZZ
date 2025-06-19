package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.dao.Dispatchdao;
import cn.edu.neu.java_fundamental.dao.Griderdao;
import cn.edu.neu.java_fundamental.entity.Administrator;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.mynode.ClickableTextCell;
import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChooseGriderController implements Initializable {
    @FXML
    private TableColumn<Grider, String> AreaColumn;

    @FXML
    private TableColumn<Grider, String> ChooseColumn;

    @FXML
    private TableColumn<Grider,String> ConditionColumn;

    @FXML
    private TableColumn<Grider, String> IDColumn;

    @FXML
    private TableColumn<Grider, Integer> ScoreColumn;

    @FXML
    private TableView<Grider> TV_ChooseGrider;

    @FXML
    private TableColumn<Grider,String> nameColumn;


    private ObservableList<Grider> griders= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        Griderdao griderdao=new Griderdao();
        List<Grider> list=griderdao.getAllGriders();


        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getId()));
        AreaColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getArea()));
        ChooseColumn.setCellValueFactory(cellData->new SimpleStringProperty("选择这名网格员"));
        ChooseColumn.setCellFactory(param->new ClickableTextCell<>(event->{
            System.out.println("管理员点击了选择字段");
            Grider grider = (Grider) event.getSource();
            Dispatchdao dispatchdao = new Dispatchdao();
            GlobalData.ERROR_CODE = dispatchdao.addDispatchLog(grider, GlobalData.CURRENT_CHOOSE_AQ_DATA);
                if(GlobalData.ERROR_CODE!=-1){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("指派成功");
                    alert.setHeaderText("指派成功");
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("添加失败失败");
                    alert.showAndWait();

                }
                GlobalData.secStage.close();


        }));
        ScoreColumn.setCellValueFactory(cellData->new SimpleIntegerProperty(cellData.getValue().getScore()).asObject());
        ConditionColumn.setCellValueFactory(cellData->
        {
            Grider grider=cellData.getValue();
            String condition;
           if(grider.getIsOnline())
               condition="在线";
               else
                   condition="休假";
            return new SimpleStringProperty(condition);
        });

  griders.addAll(list.stream()
        .sorted(Comparator.comparingInt(Grider::getScore).reversed())
        .toList());


        TV_ChooseGrider.setItems(griders);


    }
}
