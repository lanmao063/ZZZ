package cn.edu.neu.java_fundamental.util;

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
