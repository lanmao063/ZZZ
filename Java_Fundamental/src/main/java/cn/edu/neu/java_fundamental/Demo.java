package cn.edu.neu.java_fundamental;


import cn.edu.neu.java_fundamental.dao.*;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.AirQualityLevel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.*;

public class Demo extends  Application{

    @Override
    public void start(Stage stage) throws IOException {
     
        FXMLLoader fxmlLoader = new FXMLLoader(Demo.class.getResource("AQDataTable_Supervisor.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane, 1100, 600);
        stage.setTitle("AQDATA");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }



}
