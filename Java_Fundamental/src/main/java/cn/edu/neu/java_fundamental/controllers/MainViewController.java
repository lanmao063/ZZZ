package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.LoginApplication;
import cn.edu.neu.java_fundamental.mynode.AsideMenuButton;
import cn.edu.neu.java_fundamental.mynode.desc.AsideMenuButtonInfo;
import cn.edu.neu.java_fundamental.util.FXMLTools;
import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static cn.edu.neu.java_fundamental.util.FXMLTools.ChineseRoleName;

public class MainViewController {
    @FXML
    private VBox AsideMenu;

    @FXML
    private Pane centerPane;

    @FXML
    private Button personal_btn;

    @FXML
    private Label personalfiletag;

    @FXML
    private void initialize() {
        initTag();
        GlobalData.mainViewController = this;
        if (Objects.equals(GlobalData.USER_ROLE, "Administrator")) {
            System.out.println("Loading Administrator View");
            AsideMenu.getChildren().clear();
            for (AsideMenuButtonInfo info : GlobalData.administratorSideButton) {
                AsideMenuButton button = new AsideMenuButton(info);
                AsideMenu.getChildren().add(button);
            }
        } else if (Objects.equals(GlobalData.USER_ROLE, "Grider")) {
            System.out.println("Loading Grider View");
            AsideMenu.getChildren().clear();
            for (AsideMenuButtonInfo info : GlobalData.griderAsideMenuButtons) {
                AsideMenuButton btn = new AsideMenuButton(info);
                AsideMenu.getChildren().add(btn);
            }
        }
    }

    private void initTag() {
        personalfiletag.setText(getTimeHello()+"，"+ChineseRoleName(GlobalData.USER_ROLE)+" "+GlobalData.CURRENT_USER.getName());

    }
    private String getTimeHello() {
        LocalDateTime now = LocalDateTime.now();
        if(now.getHour() < 12) {
            return "早上好";
        } else if (now.getHour() == 12) {
            return "中午好";
        } else if (now.getHour() < 18) {
            return "下午好";
        } else {
            return "晚上好";
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
    @FXML
    void dopersonal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cn/edu/neu/java_fundamental/PersonalPage.fxml"));
        loader.setControllerFactory(param -> new PersonalPageController(centerPane));
        Parent personalPage = loader.load();
        centerPane.getChildren().clear();
        centerPane.getChildren().add(personalPage);
    }
}

