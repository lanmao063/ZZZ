package cn.edu.neu.java_fundamental.util;

import cn.edu.neu.java_fundamental.controllers.MainViewController;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.mynode.desc.AsideMenuButtonInfo;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static String USER_ROLE = null;
    public static Stage primaryStage=null ;
    public static final int WIDTH=1360;
    public static final int HEIGHT=768;
    public static List<AsideMenuButtonInfo> administratorSideButton=new ArrayList<>();
    public static MainViewController mainViewController;

    public static Grider CURRENT_USER;
    public static List<AsideMenuButtonInfo> griderAsideMenuButtons = new ArrayList<>();
    static {
        //网格员功能按钮
        AsideMenuButtonInfo absence = new AsideMenuButtonInfo("缺勤申请", "moon.png",
                actionEvent -> {
                    MainViewController controller = GlobalData.mainViewController;
                    if (controller != null) {
                        controller.navigateTo("/cn/edu/neu/java_fundamental/griderAbsence.fxml");
                    }
                });
        AsideMenuButtonInfo work = new AsideMenuButtonInfo("查看任务", "spread-left.png",
                actionEvent -> {
                    MainViewController controller = GlobalData.mainViewController;
                    if (controller != null) {
                        controller.navigateTo("/cn/edu/neu/java_fundamental/griderWork.fxml");
                    }});
        griderAsideMenuButtons.add(absence);
        griderAsideMenuButtons.add(work);
    }

    static{
        AsideMenuButtonInfo info1 = new AsideMenuButtonInfo("空气质量数据管理", "leaf.png", actionEvent -> {
            System.out.println("空气质量数据管理");
        });
        AsideMenuButtonInfo info2 = new AsideMenuButtonInfo("任务指派", "light.png", actionEvent -> {
            System.out.println("任务指派");
        });
        AsideMenuButtonInfo info3 = new AsideMenuButtonInfo("用户管理", "link.png", actionEvent -> {
            System.out.println("用户管理");
        });
        AsideMenuButtonInfo info4 = new AsideMenuButtonInfo("数据可视化", "list.png", actionEvent -> {
            System.out.println("数据可视化");
        });
        administratorSideButton.add(info1);
        administratorSideButton.add(info2);
        administratorSideButton.add(info3);
        administratorSideButton.add(info4);
    }
}
