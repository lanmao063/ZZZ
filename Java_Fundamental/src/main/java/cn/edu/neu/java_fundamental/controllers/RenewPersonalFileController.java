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
import java.lang.reflect.InvocationTargetException;

public class RenewPersonalFileController {
    @FXML
    private Button change_btn;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField user_nameField;

    @FXML
    void dochange(ActionEvent event) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        String newName = user_nameField.getText();
        String newPhoneNumber = phoneNumberField.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (newName.isEmpty() || newPhoneNumber.isEmpty()) {
            alert.setTitle("提示");
            alert.setHeaderText("所有字段都必须填写");
            alert.showAndWait();
            System.out.println("Fields cannot be empty.");
        }
        else if (newPhoneNumber.length() != 11 || !newPhoneNumber.matches("\\d+")) {
            alert.setTitle("提示");
            alert.setHeaderText("请填写正确的手机号码");
            alert.showAndWait();
            System.out.println("Phone number must be 11 digits long and contain only numbers.");
        }
        else {
            Supervisor currentUser = GlobalData.CURRENT_USER;
            String filename="data/"+GlobalData.USER_ROLE+".json";

            File targetFile = new File(filename);
            JsonNode root = new ObjectMapper().readTree(targetFile);
            if (root.isArray()) {
                ArrayNode users = (ArrayNode) root;
                for (int i = 0; i < users.size(); i++) {
                    JsonNode userNode = users.get(i);
                    if (userNode.get("id").asText().equals(currentUser.getId())) {
                        currentUser.setName(newName);
                        currentUser.setId(newPhoneNumber);
                        ((ObjectNode) userNode).put("name", newName);
                        ((ObjectNode) userNode).put("id", newPhoneNumber);
                        System.out.println("User information updated successfully.");
                        break;
                    }
                }
                ObjectMapper mapper = new ObjectMapper();
                mapper.writerWithDefaultPrettyPrinter().writeValue(targetFile,root);
                System.out.println("User information file updated successfully.");
                alert.setTitle("提示");
                alert.setHeaderText("个人信息修改成功");
                alert.showAndWait();
            }
        }


    }
    @FXML
    public void initialize() {
        user_nameField.setPromptText(GlobalData.CURRENT_USER.getName());
        phoneNumberField.setPromptText(GlobalData.CURRENT_USER.getId());
    }
}
