package cn.edu.neu.java_fundamental;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

    public class test extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(cn.edu.neu.java_fundamental.test.class.getResource("ChooseAQI.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
            stage.setTitle("登录");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
    }

