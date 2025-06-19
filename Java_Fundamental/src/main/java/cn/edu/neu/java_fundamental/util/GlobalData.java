package cn.edu.neu.java_fundamental.util;

import cn.edu.neu.java_fundamental.controllers.MainViewController;
import cn.edu.neu.java_fundamental.dao.DaoFactory;
import cn.edu.neu.java_fundamental.dao.Userdao;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.mynode.desc.AsideMenuButtonInfo;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static cn.edu.neu.java_fundamental.util.FXMLTools.loadContentIntoPane;

public class GlobalData {
    public static String USER_ROLE = null;
    public static Stage primaryStage=null ;
    public static final int WIDTH=1360;
    public static final int HEIGHT=768;
    public static List<AsideMenuButtonInfo> administratorSideButton=new ArrayList<>();
    public static List<AsideMenuButtonInfo> griderAsideMenuButtons = new ArrayList<>();
    public static MainViewController mainViewController;
    public static Userdao<? extends Supervisor> CURRENTDAO = null;
    public static Supervisor CURRENT_USER = null;/***此为当前登录用户实体类的引用*/
    public static AirQualityDataWrittenBySupervisor CURRENT_CHOOSE_AQ_DATA = null;
    public static int ERROR_CODE = 0;
    public static Stage secStage=null;
    public static void setLoginData(Supervisor userEntity,String role) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        USER_ROLE=role;
        CURRENTDAO= DaoFactory.createDao(role);
        CURRENT_USER = userEntity;
    }

    static {
        //网格员功能按钮
        AsideMenuButtonInfo absence = new AsideMenuButtonInfo("缺勤申请", "moon.png",
                actionEvent -> {
                    Load("/cn/edu/neu/java_fundamental/griderAbsence.fxml");
                });
        AsideMenuButtonInfo work = new AsideMenuButtonInfo("查看任务", "spread-left.png",
                actionEvent -> {
                    Load("/cn/edu/neu/java_fundamental/griderWork.fxml");});
        griderAsideMenuButtons.add(absence);
        griderAsideMenuButtons.add(work);
    }

    public static void Load(String fxmlPath) {
        MainViewController controller = GlobalData.mainViewController;
        if (controller != null) {
            controller.navigateTo(fxmlPath);
        }
    }

    static{
        AsideMenuButtonInfo info1 = new AsideMenuButtonInfo("空气质量数据管理", "leaf.png", actionEvent -> {
            Load("/cn/edu/neu/java_fundamental/AQDataTable_Supervisor.fxml");
        });
        AsideMenuButtonInfo info2 = new AsideMenuButtonInfo("空气质量检测", "light.png", actionEvent -> {
            Load("/cn/edu/neu/java_fundamental/AQDataTable_Grider.fxml");
        });
        AsideMenuButtonInfo info3 = new AsideMenuButtonInfo("用户管理", "link.png", actionEvent -> {
            Load("/cn/edu/neu/java_fundamental/UsersTable.fxml");
        });
        AsideMenuButtonInfo info4 = new AsideMenuButtonInfo("数据可视化", "list.png", actionEvent -> {
            System.out.println("数据可视化");
        });
        AsideMenuButtonInfo info5 = new AsideMenuButtonInfo("请假管理","moon.png",actionEvent -> {
            System.out.println("请假管理");
        });
        administratorSideButton.add(info1);
        administratorSideButton.add(info2);
        administratorSideButton.add(info3);
        administratorSideButton.add(info4);
        administratorSideButton.add(info5);
    }
}
