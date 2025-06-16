package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class GriderAbsenceController {

    @FXML
    private Button exit;

    @FXML
    private TextArea textArea;

    @FXML
    private void onSaveButtonClicked() {
        String Input = textArea.getText();
        if (Input == null || Input.trim().isEmpty()) {
            GlobalData.CURRENT_USER.setOnline(true);
            return;
        } else {
            GlobalData.CURRENT_USER.setOnline(false);
        }
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cn/edu/neu/java_fundamental/MainView.fxml"));
        Parent root = loader.load();
        Scene CurrentScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) CurrentScene.getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
}
