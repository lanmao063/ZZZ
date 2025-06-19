package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.LoginApplication;
import cn.edu.neu.java_fundamental.dao.Admindao;
import cn.edu.neu.java_fundamental.dao.Griderdao;
import cn.edu.neu.java_fundamental.dao.Supervisordao;
import cn.edu.neu.java_fundamental.entity.Administrator;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.mynode.ClickableTextCell;
import cn.edu.neu.java_fundamental.util.GlobalData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static cn.edu.neu.java_fundamental.util.GlobalData.Load;

public class UsersTableController implements Initializable {

    @FXML
    private TableView<Supervisor> TV_users;

    @FXML
    private TableColumn<Supervisor, String> ClassColumn;

    @FXML
    private TableColumn<Supervisor, String> ConditionColumn;

    @FXML
    private TableColumn<Supervisor, String> EditColumn;

    @FXML
    private TableColumn<Supervisor, String> IDColumn;

    @FXML
    private TableColumn<Supervisor, String> NameColumn;

    @FXML
    private TableColumn<Supervisor, Integer> ScoreColumn;

    @FXML
    private TableColumn<Supervisor, String> SexColumn;


    private ObservableList<Supervisor> users= FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NameColumn.setCellValueFactory(cellData->new
                SimpleStringProperty(cellData.getValue().getName()));
        ConditionColumn.setCellValueFactory(cellData->
        {
            Supervisor supervisor = cellData.getValue();
            String condition;
            if(supervisor instanceof Administrator)
            condition=null;
            else if(supervisor instanceof Grider){
                if(((Grider) supervisor).getIsOnline())
                    condition="在线";
                    else
                        condition="离线";
            }else
                condition=null;
            return new SimpleStringProperty(condition);
        });
        IDColumn.setCellValueFactory(cellData->new
                SimpleStringProperty(cellData.getValue().getId()));
        SexColumn.setCellValueFactory(cellData->new
                SimpleStringProperty(cellData.getValue().getSex()));
        ScoreColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getScore()).asObject());
        System.out.println("加载用户表格");
        ClassColumn.setCellValueFactory(cellData->{
            Supervisor supervisor = cellData.getValue();
            String clazz;
            if(supervisor instanceof Administrator) {
                clazz = "管理员";
            }
            else if(supervisor instanceof Grider) {
                clazz = "网格员";
            }
            else {
                clazz = "公众监督员";
            }
                return new SimpleStringProperty(clazz);
        });
        EditColumn.setCellValueFactory(cellData->new SimpleStringProperty("编辑"));
        EditColumn.setCellFactory(param->new ClickableTextCell<Supervisor>(event-> {
            System.out.println("管理员点击了信息编辑字段");
            Object source = event.getSource();
            if (!(source instanceof Supervisor supervisor)) {
                System.err.println("点击单元格的 source 不是 Supervisor 类型！");
                return;
            }
            GlobalData.EDITING_USER = supervisor;
            if(supervisor instanceof Administrator) {
                GlobalData.EDITING_USER_ROLE = "Administrator";
            }
            else if(supervisor instanceof Grider) {
                GlobalData.EDITING_USER_ROLE = "Grider";
            }
            else {
                GlobalData.EDITING_USER_ROLE = "Supervisor";
            }
            try{
                URL fxmlLocation = getClass().getResource("/cn/edu/neu/java_fundamental/AdminRole.fxml");
                if (fxmlLocation == null) {
                    System.err.println("找不到 FXML 文件，请检查路径是否正确！");
                    new Alert(Alert.AlertType.ERROR, "无法找到 AdminRole.fxml 文件。").showAndWait();
                    return;
                }
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                AnchorPane anchorPane=loader.load();
                Scene scene=new Scene(anchorPane);
                GlobalData.secStage=new Stage();
                GlobalData.secStage.initModality(Modality.APPLICATION_MODAL);
                GlobalData.secStage.setTitle("用户管理");
                GlobalData.secStage.setScene(scene);
                AdminRoleController adminRoleController = loader.getController();
                adminRoleController.setOnWindowCloseCallback((v) -> refreshUserTable());
                GlobalData.secStage.setOnHidden(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        // 在窗口关闭时执行回调
                        if (adminRoleController.onWindowCloseCallback != null) {
                            adminRoleController.onWindowCloseCallback.accept(null);
                        }
                    }
                });
                GlobalData.secStage.showAndWait();
            } catch (IOException e) {
                System.out.println("指派失败"+e.getMessage());
            }
        }
        ));
        refreshUserTable();
    }
    public void refreshUserTable() {
        System.out.println("正在刷新用户表格...");
        users.clear(); // 清空当前列表
        users.addAll(new Supervisordao().getAllSupervisors());
        users.addAll(new Griderdao().getAllGriders());
        users.addAll(new Admindao().getAllAdministrators());
        TV_users.setItems(users); // 重新设置表格的数据源
        TV_users.refresh(); // 强制 TableView 重新绘制
        System.out.println("用户表格刷新完成。");
    }

}
