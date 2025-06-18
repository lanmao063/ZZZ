package cn.edu.neu.java_fundamental.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FXMLTools {
    public void loadContentIntoPane(String fxmlName, Pane pane) throws IOException {
        String resourcePath =  "/" +  fxmlName;
        System.out.println("Loading FXML: " + resourcePath);
        FXMLLoader loader =new FXMLLoader(getClass().getResource(resourcePath));
        Parent content=loader.load();
        pane.getChildren().clear();
        pane.getChildren().add(content);
    }
    public static String ChineseRoleName(String roleName) {
        return switch (roleName) {
            case "Administrator" -> ("管理员");
            case "Grider" -> ("网格员");
            case "Supervisor" -> ("公众监督员");
            default -> ("?");
        };
    }
}
