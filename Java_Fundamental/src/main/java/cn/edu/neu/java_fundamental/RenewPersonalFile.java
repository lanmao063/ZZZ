package cn.edu.neu.java_fundamental;

import cn.edu.neu.java_fundamental.util.GlobalData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RenewPersonalFile {
    @FXML
    private Button change_btn;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField user_nameField;

    @FXML
    void dochange(ActionEvent event) {



    }
    @FXML
    public void initialize() {
        user_nameField.setPromptText(GlobalData.CURRENT_USER.getName());
        phoneNumberField.setPromptText(GlobalData.CURRENT_USER.getId());
    }
}
