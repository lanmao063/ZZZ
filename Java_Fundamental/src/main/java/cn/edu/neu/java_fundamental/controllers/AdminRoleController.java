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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static cn.edu.neu.java_fundamental.util.FXMLTools.ChineseRoleName;

public class AdminRoleController implements Initializable {
    @Setter
    Consumer<Void> onWindowCloseCallback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_idTag.setText(GlobalData.EDITING_USER.getId());
        user_nameTag.setText(GlobalData.EDITING_USER.getName());
        user_roleTag.setText(ChineseRoleName(GlobalData.EDITING_USER_ROLE));

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText("您确定要删除该用户吗？");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            deleteUserMethod();
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("提示");
            alert1.setHeaderText("已删除用户");
            alert1.showAndWait();
        }
    }

    @FXML
    void resetPwd(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText("您确定要重置该用户的密码吗？");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            GlobalData.EDITING_USER.setPassword("000000");
            System.out.println("已将用户 (ID: " + GlobalData.EDITING_USER.getId() + ") 的密码重置为 '000000'。");
            switch (GlobalData.EDITING_USER_ROLE) {
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
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("提示");
            alert1.setHeaderText("已重置密码为000000");
            alert1.showAndWait();
        }
    }

    @FXML
    void turnAdmin(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText("您确定要将该用户改为管理员吗？");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if ("Administrator".equals(GlobalData.EDITING_USER_ROLE)) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("提示");
                alert1.setHeaderText("当前用户已是网格员，无需转换。");
                alert1.showAndWait();
                return;
            } else {
                deleteUserMethod();
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
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("提示");
            alert2.setHeaderText("已转换为管理员");
            alert2.showAndWait();
        }
    }

    @FXML
    void turnGrider(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText("您确定要将该用户改为网格员吗？");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
        if ("Grider".equals(GlobalData.EDITING_USER_ROLE)) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("提示");
            alert1.setHeaderText("当前用户已是网格员，无需转换。");
            alert1.showAndWait();
            return;
        } else {
            deleteUserMethod();
        }
        Grider grider = new Grider();
        grider.setId(GlobalData.EDITING_USER.getId());
        grider.setName(GlobalData.EDITING_USER.getName());
        grider.setPassword(GlobalData.EDITING_USER.getPassword());
        grider.setSex(GlobalData.EDITING_USER.getSex());
        grider.setScore(0);
        grider.setIsOnline(true);
        Griderdao griderdao = new Griderdao();
        int addRes = griderdao.addGrider(grider);
        if (addRes != -1) {
            GlobalData.EDITING_USER_ROLE = "Grider";
            System.out.println("成功将用户 (ID: " + grider.getId() + ") 转为网格员。");
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("提示");
            alert2.setHeaderText("已转换为网格员");
            alert2.showAndWait();
        } else {
            System.err.println("添加新网格员失败。");
        }
        }
    }
    public void deleteUserMethod(){
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
        }

    }
}


