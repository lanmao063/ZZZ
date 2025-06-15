package cn.edu.neu.java_fundamental;

import cn.edu.neu.java_fundamental.dao.AirQualityDataWrittenByGriderdao;
import cn.edu.neu.java_fundamental.dao.Supervisordao;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.util.Date;
import java.util.List;

public class Demo extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Supervisordao supervisordao=new Supervisordao();
        List<Supervisor> list=supervisordao.getAllSupervisors();
        AirQualityDataWrittenByGrider a=new AirQualityDataWrittenByGrider(list.get(0),"liaoning","shenyang","hunnan",new Date(),1,1,1);
        AirQualityDataWrittenByGriderdao tool=new AirQualityDataWrittenByGriderdao();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AQDataTable_Grider.fxml"));
        AnchorPane root=loader.load();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
}
