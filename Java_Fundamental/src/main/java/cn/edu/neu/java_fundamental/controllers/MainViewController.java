package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.mynode.AsideMenuButton;
import cn.edu.neu.java_fundamental.mynode.desc.AsideMenuButtonInfo;
import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        for(AsideMenuButtonInfo info: GlobalData.administratorSideButton) {
            AsideMenuButton button = new AsideMenuButton(info);
            AsideMenu.getChildren().add(button);
        }
    }
    }
    @FXML
    public void goPersonal(ActionEvent actionEvent) {
        System.out.println("个人主页");
    }
}
