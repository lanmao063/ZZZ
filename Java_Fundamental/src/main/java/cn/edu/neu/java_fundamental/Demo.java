package cn.edu.neu.java_fundamental;


import cn.edu.neu.java_fundamental.dao.Dispatchdao;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.util.AirQualityLevel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Demo extends  Application{

    @Override
    public void start(Stage stage) throws IOException {
        Dispatchdao dispatchdao=new Dispatchdao();
        dispatchdao.addDispatchLog(new Grider("1","1","1","1",1,"1",true),new AirQualityDataWrittenBySupervisor( AirQualityLevel.EXCELLENT,"1","1","1","1","1"));
        Map<String, List<AirQualityDataWrittenBySupervisor>> dispatchLog=dispatchdao.getDispatchLog();
        System.out.println(dispatchLog);
    }
    public static void main(String[] args) {
        launch();
    }



}
