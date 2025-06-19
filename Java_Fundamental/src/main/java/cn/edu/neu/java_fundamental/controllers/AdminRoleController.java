package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.dao.Admindao;
import cn.edu.neu.java_fundamental.dao.Griderdao;
import cn.edu.neu.java_fundamental.dao.Supervisordao;
import cn.edu.neu.java_fundamental.entity.Administrator;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class AdminRoleController implements Initializable {
    @Setter
    Consumer<Void> onWindowCloseCallback;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Button deleteUserbtn;

    @FXML
    private Button resetpwdbtn;

    @FXML
    private Button turnAdminbtn;

    @FXML
    private Button turnGriderbtn;

    @FXML
    private Label user_idTag;

    @FXML
    private Label user_nameTag;

    @FXML
    private Label user_roleTag;

    @FXML
    void deleteUser(ActionEvent event) {
        String oldRole = GlobalData.EDITING_USER_ROLE;
        Supervisor userToProcess = GlobalData.EDITING_USER;
        try {
            switch (oldRole) {
                case "Administrator":
                    System.out.println("正在删除原管理员用户 (ID: " + userToProcess.getId() + ") ...");
                    if (userToProcess instanceof Administrator) {
                        Admindao admindao = new Admindao();
                        int adminDeleteRes = admindao.deleteAdministrator((Administrator) userToProcess); // 安全转型并调用
                        if (adminDeleteRes != -1) {
                            System.out.println("成功删除原管理员用户 (ID: " + userToProcess.getId() + ")");
                        } else {
                            System.err.println("删除原管理员用户失败 (ID: " + userToProcess.getId() + ")");
                        }
                    } else {
                        System.err.println("错误：角色为 Administrator，但实际对象不是 Administrator 类型。");
                    }
                    break;
                case "Supervisor":
                    System.out.println("正在删除原主管用户 (ID: " + userToProcess.getId() + ") ...");
                    Supervisordao supervisordao = new Supervisordao();
                    int supervisorDeleteRes = supervisordao.deleteSupervisor(userToProcess); // 直接使用 Supervisor 对象
                    if (supervisorDeleteRes != -1) {
                        System.out.println("成功删除原主管用户 (ID: " + userToProcess.getId() + ")");
                    } else {
                        System.err.println("删除原主管用户失败 (ID: " + userToProcess.getId() + ")");
                    }
                    break;
                case "Grider":
                    System.out.println("正在删除原网格员用户 (ID: " + userToProcess.getId() + ") ...");
                    if (userToProcess instanceof Grider) {
                        Griderdao griderdao = new Griderdao();
                        int griderDeleteRes = griderdao.deleteGrider((Grider) userToProcess);
                        if (griderDeleteRes != -1) {
                            System.out.println("成功删除原网格员用户 (ID: " + userToProcess.getId() + ")");
                        } else {
                            System.err.println("删除原网格员用户失败 (ID: " + userToProcess.getId() + ")");
                        }
                    } else {
                        System.err.println("错误：角色为 Grider，但实际对象不是 Grider 类型。");
                    }
                    break;
                default:
                    System.err.println("未知的用户角色：" + oldRole + "，无法删除。");
                    break;
            }
        } catch (IOException e) {
            System.err.println("删除原用户时发生 IO 错误：" + e.getMessage());
            e.printStackTrace();
        }

    }

    @FXML
    void resetPwd(ActionEvent event) throws IOException {
        GlobalData.EDITING_USER.setPassword("000000");
        System.out.println("已将用户 (ID: " + GlobalData.EDITING_USER.getId() + ") 的密码重置为 '000000'。");
        switch(GlobalData.EDITING_USER_ROLE){
            case "Administrator":
                if (GlobalData.EDITING_USER instanceof Administrator administrator) {
                    Admindao admindao = new Admindao();
                    admindao.updateAdmin(administrator);
                } else {
                    System.err.println("类型不匹配：GlobalData.EDITING_USER_ROLE 为 Administrator 但对象不是 Administrator。");
                }
                break;
            case "Supervisor":
                Supervisordao supervisordao = new Supervisordao();
               supervisordao.updateSupervisor(GlobalData.EDITING_USER);
                break;
            case "Grider":
                // 安全转型和更新
                if (GlobalData.EDITING_USER instanceof Grider grider) {
                    Griderdao griderdao = new Griderdao();
                    griderdao.updateGrider(grider);
                } else {
                    System.err.println("类型不匹配：GlobalData.EDITING_USER_ROLE 为 Grider 但对象不是 Grider。");
                }
                break;
            default:
                System.err.println("未知的用户角色：" + GlobalData.EDITING_USER_ROLE + "，无法重置密码。");
        }
    }

    @FXML
    void turnAdmin(ActionEvent event) throws IOException {
        if ("Administrator".equals(GlobalData.EDITING_USER_ROLE)) {
            System.out.println("当前用户已是网格员，无需转换。");
            return;
        } else {
            deleteUser(event);
        }
        Administrator admin = new Administrator();
        admin.setId(GlobalData.EDITING_USER.getId());
        admin.setName(GlobalData.EDITING_USER.getName());
        admin.setPassword(GlobalData.EDITING_USER.getPassword());
        admin.setSex(GlobalData.EDITING_USER.getSex());
        admin.setTerritory("null");
        Admindao admindao = new Admindao();
        int addRes = admindao.addAdministrator(admin);
        if (addRes != -1) {
            GlobalData.EDITING_USER_ROLE = "Administrator";
            System.out.println("成功将用户 (ID: " + admin.getId() + ") 转为管理员。");
        } else {
            System.err.println("添加新管理员失败。");
        }
    }

    @FXML
    void turnGrider(ActionEvent event) throws IOException {
        if ("Grider".equals(GlobalData.EDITING_USER_ROLE)) {
            System.out.println("当前用户已是网格员，无需转换。");
            return;
        } else {
            deleteUser(event);
        }
        Grider grider = new Grider();
        grider.setId(GlobalData.EDITING_USER.getId());
        grider.setName(GlobalData.EDITING_USER.getName());
        grider.setPassword(GlobalData.EDITING_USER.getPassword());
        grider.setSex(GlobalData.EDITING_USER.getSex());
        grider.setScore(0);
        grider.setOnline(true);
        Griderdao griderdao = new Griderdao();
        int addRes = griderdao.addGrider(grider);
        if (addRes != -1) {
            GlobalData.EDITING_USER_ROLE = "Grider";
            System.out.println("成功将用户 (ID: " + grider.getId() + ") 转为网格员。");
        } else {
            System.err.println("添加新网格员失败。");
        }
    }
}


