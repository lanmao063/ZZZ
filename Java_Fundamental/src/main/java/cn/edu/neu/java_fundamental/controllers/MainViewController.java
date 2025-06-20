package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.LoginApplication;
import cn.edu.neu.java_fundamental.dao.Griderdao;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.mynode.AsideMenuButton;
import cn.edu.neu.java_fundamental.mynode.desc.AsideMenuButtonInfo;
import cn.edu.neu.java_fundamental.util.FXMLTools;
import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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
    private void initialize() throws IOException {
        initTag();
        if (Objects.equals(GlobalData.USER_ROLE, "Administrator")) {
            System.out.println("Loading Administrator View");
            AsideMenu.getChildren().clear();
            for (AsideMenuButtonInfo info : GlobalData.administratorSideButton) {
                AsideMenuButton button = new AsideMenuButton(info);
                AsideMenu.getChildren().add(button);
            }
            GlobalData.mainViewController = this;
        } else if (Objects.equals(GlobalData.USER_ROLE, "Grider")) {
            System.out.println("Loading Grider View");
            AsideMenu.getChildren().clear();
            for (AsideMenuButtonInfo info : GlobalData.griderAsideMenuButtons) {
                AsideMenuButton btn = new AsideMenuButton(info);
                AsideMenu.getChildren().add(btn);
            }
            GlobalData.mainViewController = this;
            if("!".equals(((Grider)GlobalData.CURRENT_USER).getAbsentReason())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("警告");
                alert.setHeaderText("您有请假申请被驳回！请联系管理员");
                alert.showAndWait();
                ((Grider) GlobalData.CURRENT_USER).setAbsentReason("。。。");
                Griderdao griderdao = new Griderdao();
                griderdao.updateGrider((Grider) GlobalData.CURRENT_USER);
            }
            if (!((Grider) GlobalData.CURRENT_USER).getIsOnline()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("提示");
                alert.setHeaderText("您的请假申请被通过了，恭喜您。");
                alert.showAndWait();
                ((Grider) GlobalData.CURRENT_USER).setAbsentReason("，，，");
                Griderdao griderdao = new Griderdao();
                griderdao.updateGrider((Grider) GlobalData.CURRENT_USER);
            }
        } else if (Objects.equals(GlobalData.USER_ROLE, "Supervisor")) {
            System.out.println("Loading Supervisor View");
            AsideMenu.getChildren().clear();
            for (AsideMenuButtonInfo info : GlobalData.supervisorSideButton) {
                AsideMenuButton btn = new AsideMenuButton(info);
                AsideMenu.getChildren().add(btn);
            }
            GlobalData.mainViewController = this;

        }


    }

    public void initTag() {
        personalfiletag.setText(initTime() + "，" + ChineseRoleName(GlobalData.USER_ROLE) + GlobalData.CURRENT_USER.getName());
    }

    public String initTime() {
        LocalDateTime now = LocalDateTime.now();
        if (now.getHour() < 12) {
            return ("早上好");
        } else if (now.getHour() == 12) {
            return ("中午好");
        } else if (now.getHour() < 17) {
            return ("下午好");
        } else {
            return ("晚上好");
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

    @FXML
    void doexit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认退出");
        alert.setHeaderText("您确定要退出登录吗？");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(LoginApplication.class.getResource("/cn/edu/neu/java_fundamental/login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = null;
                stage = GlobalData.primaryStage; // 设置全局变量
                stage.setTitle("登录");
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
                GlobalData.CURRENT_USER = null; // 清除当前用户信息
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

