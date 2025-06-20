package cn.edu.neu.java_fundamental;

import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        URL cssUrl = getClass().getResource("/cn/edu/neu/java_fundamental/style.css");

        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.err.println("Error: Stylesheet not found.");
            System.err.println("Classpath root: " + getClass().getResource("/"));
            System.err.println("Current resource URL using getResource: " + getClass().getResource("/cn/edu/neu/java_fundamental/style.css"));
            System.err.println("Current resource URL using ClassLoader: " + LoginApplication.class.getClassLoader().getResource("cn/edu/neu/java_fundamental/style.css"));
        }







        GlobalData.primaryStage=stage; // 设置全局变量
        stage.setTitle("东软环境监督平台-登录");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}