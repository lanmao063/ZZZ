package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.dao.GriderAbsentApplication;
import cn.edu.neu.java_fundamental.dao.Griderdao;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.mynode.ClickableTextCell;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LeaveRequestTableController implements Initializable {

    @FXML
    private TableView<Grider> TV_LeaveRequest;

    @FXML
    private TableColumn<Grider, String> AreaColumn;

    @FXML
    private TableColumn<Grider, String> IDColumn;

    @FXML
    private TableColumn<Grider, String> applyColumn;

    @FXML
    private TableColumn<Grider,  String> nameColumn;

    @FXML
    private TableColumn<Grider,  String> reasonColumn;

    @FXML
    private TableColumn<Grider,  String> rejectColumn;

    @FXML
    private TableColumn<Grider, Integer> scoreColumn;


    private ObservableList<Grider> adsentGriders= FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GriderAbsentApplication griderAbsentApplication=new GriderAbsentApplication();
        List<Grider> list = null;

        try {
            list = griderAbsentApplication.getAllAbsentGriders();
        } catch (IOException e) {
            System.err.println("加载请假申请数据失败: " + e.getMessage());
        }

        AreaColumn.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getArea()));
        IDColumn.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getId()));
        nameColumn.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getName()));
        reasonColumn.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getAbsentReason()));
        scoreColumn.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getScore()).asObject());
        applyColumn.setCellValueFactory(cellData ->new SimpleStringProperty("同意"));
        applyColumn.setCellFactory(param ->new ClickableTextCell<>(event->{
            System.out.println("管理员点击了同意");
            Grider grider = TV_LeaveRequest.getSelectionModel().getSelectedItem();
            Griderdao griderdao=new Griderdao();
            List<Grider> griders = griderdao.getAllGriders();
            for (Grider g:griders) {
                if(g.getId().equals(grider.getId())){
                    g.setIsOnline(false);
                    try {
                        griderdao.updateGrider(g);
                    } catch (IOException e) {
                        System.err.println("更新网格员数据失败: " + e.getMessage());
                    }
                    break;
                }
            }
            try {
                griderAbsentApplication.deleteAbsentGrider(grider);
            } catch (IOException e) {
                System.err.println("删除请假记录失败: " + e.getMessage());
            }
            adsentGriders.remove(grider);
            TV_LeaveRequest.setItems(adsentGriders);
            TV_LeaveRequest.refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("提示");
            alert.setContentText("已同意");
            System.out.println("已同意");



        }));
        rejectColumn.setCellValueFactory(cellData ->new SimpleStringProperty("驳回"));
        rejectColumn.setCellFactory(param ->new ClickableTextCell<>(event->{
            System.out.println("管理员点击了驳回");
            Grider grider = TV_LeaveRequest.getSelectionModel().getSelectedItem();
            Griderdao griderdao=new Griderdao();
            List<Grider> griders = griderdao.getAllGriders();
            for (Grider g:griders) {
                if(g.getId().equals(grider.getId())){
                    g.setAbsentReason("!");
                    g.setIsOnline(true);
                    try {
                        griderdao.updateGrider(g);
                    } catch (IOException e) {
                        System.err.println("更新网格员数据失败: " + e.getMessage());
                    }
                    break;
                }
            }
            try {
                griderAbsentApplication.deleteAbsentGrider(grider);
            } catch (IOException e) {
                System.err.println("删除请假记录失败: " + e.getMessage());
            }
            adsentGriders.remove(grider);
            TV_LeaveRequest.setItems(adsentGriders);
            TV_LeaveRequest.refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("提示");
            alert.setContentText("已驳回");
            System.out.println("已驳回");
        }));



        if (list != null && !list.isEmpty()) {
            adsentGriders.addAll(list);
        } else {
            System.out.println("暂无请假申请数据");
        }

        TV_LeaveRequest.setItems(adsentGriders);

    }
}
