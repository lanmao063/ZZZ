package cn.edu.neu.java_fundamental;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

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
