package cn.edu.neu.java_fundamental;

import cn.edu.neu.java_fundamental.dao.AirQualityDataWrittenByGriderdao;
import cn.edu.neu.java_fundamental.dao.Supervisordao;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.AQI_Calculator;
import cn.edu.neu.java_fundamental.util.AirQualityLevel;
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
        AirQualityDataWrittenByGrider a=new AirQualityDataWrittenByGrider(list.get(0),"liaoning","shenyang","hunnan",new Date(),100,10,10);
        AirQualityDataWrittenByGriderdao tool=new AirQualityDataWrittenByGriderdao();
        tool.addAirQualityDataWrittenByGrider(a);
        System.out.println(a.getAQI());
        System.out.println(a.getAQL());
        System.out.println(AQI_Calculator.calculateAQI_forAllPollutants(100,10,10));
        System.out.println(AirQualityLevel.getAQL_by_aqi(300));
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AQDataTable_Grider.fxml"));
        AnchorPane root=loader.load();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
}
