package cn.edu.neu.java_fundamental.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FXMLTools {
    public void loadFxml(String fxmlName, Pane pane)  {
        String resourcePath =  "/" +  fxmlName;
        FXMLLoader loader =new FXMLLoader(getClass().getResource(resourcePath));
        pane.getChildren().clear();
        try{
            Pane childrenPane = loader.load();
            pane.getChildren().add(childrenPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
