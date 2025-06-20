package cn.edu.neu.java_fundamental.controllers;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.GlobalData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;

public class RenewPasswordController {

    @FXML
    private Button changebtn;

    @FXML
    private TextField confirmpwd;

    @FXML
    private TextField newpwd;

    @FXML
    private TextField originpwd;

    @FXML
    void dochange(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String origin = originpwd.getText();
        String newPassword = newpwd.getText();
        String confirmPassword = confirmpwd.getText();

        if (origin.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            alert.setTitle("提示");
            alert.setHeaderText("所有字段都必须填写");
            alert.showAndWait();
            System.out.println("All fields must be filled out.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            alert.setTitle("提示");
            alert.setHeaderText("确认密码与新密码不匹配");
            alert.showAndWait();
            System.out.println("New password and confirmation do not match.");
            return;
        }
        if (newPassword.length() < 6) {
            alert.setTitle("提示");
            alert.setHeaderText("新密码必须至少6个字符长");
            System.out.println("New password must be at least 6 characters long.");
            return;
        }
        if(origin.equals(GlobalData.CURRENT_USER.getPassword())){
            Supervisor currentUser = GlobalData.CURRENT_USER;
            String filename="data/"+GlobalData.USER_ROLE+".json";
            File targetFile = new File(filename);
            JsonNode root=null;
            try {
                root = new ObjectMapper().readTree(targetFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (root.isArray()) {
                ArrayNode users = (ArrayNode) root;
                for (int i = 0; i < users.size(); i++) {
                    JsonNode userNode = users.get(i);
                    if (userNode.get("id").asText().equals(currentUser.getId())) {
                        currentUser.setPassword(newPassword);
                        ((ObjectNode) userNode).put("password", newpwd.getText());
                        System.out.println("User information updated successfully.");
                        break;
                    }
                }
                ObjectMapper mapper = new ObjectMapper();
                try {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(targetFile,root);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("User information file updated successfully.");
            }
            alert.setTitle("提示");
            alert.setHeaderText("密码已修改");
            alert.showAndWait();
            System.out.println("Password changed successfully!");

        }
        }


}

