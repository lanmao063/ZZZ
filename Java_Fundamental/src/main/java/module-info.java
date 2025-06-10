module cn.edu.neu.java_fundamental {
    requires javafx.controls;
    requires javafx.fxml;


    opens cn.edu.neu.java_fundamental to javafx.fxml;
    exports cn.edu.neu.java_fundamental;
    opens cn.edu.neu.java_fundamental.controllers to javafx.fxml;
    exports cn.edu.neu.java_fundamental.controllers;
}