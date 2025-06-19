package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.dao.Admindao;
import cn.edu.neu.java_fundamental.dao.Griderdao;
import cn.edu.neu.java_fundamental.dao.Supervisordao;
import cn.edu.neu.java_fundamental.entity.Administrator;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.mynode.ClickableTextCell;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
        ClassColumn.setCellValueFactory(cellData->{
            Supervisor supervisor = cellData.getValue();
            String clazz;
            if(supervisor instanceof Administrator)
                clazz="管理员";
            else if(supervisor instanceof Grider)
                clazz="网格员";
            else
                clazz="公众监督员";
                return new SimpleStringProperty(clazz);
        });
        EditColumn.setCellValueFactory(cellData->new SimpleStringProperty("编辑"));
        EditColumn.setCellFactory(param->new ClickableTextCell<Supervisor>(event->{
            System.out.println("管理员点击了信息编辑字段");
            Supervisor supervisor = (Supervisor) event.getSource();
            File file=new File("data/Supervisor.json");
            JsonNode root = null;
            if (root.isArray()) {
                ArrayNode users = (ArrayNode) root;
                for (int i = 0; i < users.size(); i++) {
                    JsonNode userNode = users.get(i);
                    if (userNode.get("id").asText().equals(supervisor.getId())) {
                        ((ObjectNode) userNode).put("name","默认用户");
                        ((ObjectNode) userNode).put("password","000000" );
                        System.out.println("User information init successfully.");
                        break;
                    }
                }
                ObjectMapper mapper = new ObjectMapper();
                try {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file,root);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("User information file updated successfully.");
            }
            System.out.println(supervisor);

        }));

        users.addAll(new Supervisordao().getAllSupervisors());
        users.addAll(new Griderdao().getAllGriders());
        users.addAll(new Admindao().getAllAdministrators());
        TV_users.setItems(users);




    }

}
