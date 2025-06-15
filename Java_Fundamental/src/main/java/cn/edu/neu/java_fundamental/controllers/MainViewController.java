package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.mynode.AsideMenuButton;
import cn.edu.neu.java_fundamental.mynode.desc.AsideMenuButtonInfo;
import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class MainViewController {
    @FXML
    private VBox AsideMenu;

    @FXML
    private Pane centerPane;

    @FXML
    private void initialize() {
        if (Objects.equals(GlobalData.USER_ROLE, "administrator")) {
            System.out.println("Loading Administrator View");
            AsideMenu.getChildren().clear();
            for (AsideMenuButtonInfo info : GlobalData.administratorSideButton) {
                AsideMenuButton button = new AsideMenuButton(info);
                AsideMenu.getChildren().add(button);
            }
        } else if (Objects.equals(GlobalData.USER_ROLE, "grider")) {
            System.out.println("Loading Grider View");
            AsideMenu.getChildren().clear();
            for (AsideMenuButtonInfo info : GlobalData.griderAsideMenuButtons) {
                AsideMenuButton btn = new AsideMenuButton(info);
                AsideMenu.getChildren().add(btn);
            }
            GlobalData.mainViewController = this;

        }


    }

    public void navigateTo(String fxmlPath) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource(fxmlPath));
            centerPane.getChildren().clear();
            centerPane.getChildren().add(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

