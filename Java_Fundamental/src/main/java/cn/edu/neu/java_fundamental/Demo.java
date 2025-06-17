package cn.edu.neu.java_fundamental;


import cn.edu.neu.java_fundamental.dao.GriderSubmit;
import cn.edu.neu.java_fundamental.dao.SupervisorSubmit;
import cn.edu.neu.java_fundamental.dao.Supervisordao;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.AirQualityLevel;
import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Demo extends  Application{

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("UsersTable.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 1100, 600);
        stage.setTitle("用户信息");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }



}
