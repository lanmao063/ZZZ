package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.util.GlobalData;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.edu.neu.java_fundamental.util.FileTools.writeStringToFile;

public class GriderAbsenceController {

    @FXML
    private Button exit;

    @FXML
    private Button submit;

    @FXML
    private TextArea absentReason;

    @FXML
    private void submit() throws IOException {
        submit.setDisable(false);
        String Input = absentReason.getText();
        Grider grider = (Grider) GlobalData.CURRENT_USER;
        if (Input.trim().isEmpty()) {
            grider.setOnline(true);
            submit.setDisable(true);
        } else {
            grider.setOnline(false);
        }
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("data/Grider.json");

        List<Grider> records = new ArrayList<>();

        // 如果文件存在，则读取已有数据
        if (file.exists()) {
            try {
                records = Arrays.asList(mapper.readValue(file, Grider[].class));
            } catch (IOException e) {
                System.out.println("读取旧数据失败，将创建新文件");
            }
        }

        for (Grider record : records) {
            if (record.getId().equals(grider.getId())) {
                record.setOnline(grider.getIsOnline());
                break;
            }
        }

        // 写回文件
        mapper.writeValue(file, records);
            System.out.println("写入状态成功");
            mapper.writeValue(new File("data/GriderAbsenceRecord.json"), Input);
            System.out.println("写入假条成功");
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
