package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.dao.GriderSubmit;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import cn.edu.neu.java_fundamental.entity.GridInfo;
import cn.edu.neu.java_fundamental.entity.Grider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static cn.edu.neu.java_fundamental.util.GlobalData.CURRENT_USER;

public class GriderFeedbackController {

    @FXML
    private TextField co;

    @FXML
    private Button feedback;

    @FXML
    private TextField so2;

    @FXML
    private TextField spm;

    private GridInfo currentGrid;
    public void setGridInfo(GridInfo gridInfo) {
        this.currentGrid = gridInfo;
    }
    Grider CURRENT_GRIDER = (Grider) CURRENT_USER;

    @FXML
    void feedback(ActionEvent event) {


            try {
                double so2Value = Double.parseDouble(so2.getText());
                double coValue = Double.parseDouble(co.getText());
                double spmValue = Double.parseDouble(spm.getText());

                // 构造数据实体
                AirQualityDataWrittenByGrider data = new AirQualityDataWrittenByGrider(
                        CURRENT_GRIDER.getIsOnline(), currentGrid.getProvince(), currentGrid.getCity(), currentGrid.getDistrict(), currentGrid.getDate(), so2Value, coValue, spmValue
                );

                Grider grider = new Grider();
                grider.setId(CURRENT_GRIDER.getId());
                grider.setOnline(CURRENT_GRIDER.getIsOnline());

                GriderSubmit submitter = new GriderSubmit();
                submitter.addAirQualityData(grider, data);

                // 显示成功提示
                showAlert("提交成功", "数据已成功写入 GriderSubmitLog.json 文件");

            } catch (NumberFormatException e) {
                showAlert("输入错误", "请输入有效的数字");
            } catch (IOException e) {
                showAlert("写入失败", "无法写入文件: " + e.getMessage());
            }

    }
    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


