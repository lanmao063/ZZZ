package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.entity.Grid;
import cn.edu.neu.java_fundamental.entity.GridInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static cn.edu.neu.java_fundamental.util.GlobalData.Load;

public class GriderWorkController {


    @FXML
    private VBox gridContainer;

    @FXML
    public void initialize() throws IOException {
        List<GridInfo> gridInfoList = new Grid().readGridInfo();

        for (GridInfo grid : gridInfoList) {
            HBox hbox = new HBox(10);
            hbox.setAlignment(Pos.CENTER_LEFT);

            TextField textField = new TextField(formatGridInfo(grid));
            textField.setPrefWidth(700);
            textField.setPrefHeight(50);
            textField.setEditable(false);
            Button myButton = new Button("去检测");
            myButton.setPrefSize(80, 50);
            myButton.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/cn/edu/neu/java_fundamental/GriderFeedback.fxml"));
                    Parent root = loader.load();

                    GriderFeedbackController controller = loader.getController();
                    controller.setGridInfo(grid);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Label label = new Label(grid.getAQL());
            label.setPrefSize(200, 50);

            hbox.getChildren().addAll(label, textField, myButton);
            gridContainer.getChildren().add(hbox);
        }

    }

    private String formatGridInfo(GridInfo grid) {
        return grid.getProvince() + grid.getCity() + grid.getDistrict()
                 + grid.getDate();
    }

    @FXML
    public void exit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cn/edu/neu/java_fundamental/MainView.fxml"));
        Parent root = loader.load();
        Scene CurrentScene = ((Node)actionEvent.getSource()).getScene();
        Stage stage = (Stage)CurrentScene.getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
