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
    private Label userNametag;

    @FXML
    private Label timetag;

    @FXML
    private Label roletag;

    @FXML
    private void initialize() {
        LocalDateTime now = LocalDateTime.now();
        if(now.getHour()<12){
            timetag.setText("早上好，");
        }else if(now.getHour()==12){
            timetag.setText("中午好，");
        }else if(now.getHour()<17){
            timetag.setText("下午好，");
        }else{
            timetag.setText("晚上好，");
        }
        switch(ChineseRoleName(GlobalData.USER_ROLE)){
            case "管理员" -> roletag.setText("管理员");
            case "网格员" -> roletag.setText("网格员");
            case "公众监督员" -> roletag.setText("公众监督员");
            default -> roletag.setText("未知角色");
        }
        userNametag.setText(GlobalData.CURRENT_USER.getName());
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
    @FXML
    void dopersonal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cn/edu/neu/java_fundamental/PersonalPage.fxml"));
        loader.setControllerFactory(param -> new PersonalPageController(centerPane));
        Parent personalPage = loader.load();
        centerPane.getChildren().clear();
        centerPane.getChildren().add(personalPage);
    }
}

