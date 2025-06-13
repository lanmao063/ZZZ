module cn.edu.neu.java_fundamental {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.fasterxml.jackson.databind;


    opens cn.edu.neu.java_fundamental to javafx.fxml;
    exports cn.edu.neu.java_fundamental;

    opens cn.edu.neu.java_fundamental.controllers to javafx.fxml;
    exports cn.edu.neu.java_fundamental.controllers;

    opens cn.edu.neu.java_fundamental.entity to javafx.fxml;
    exports cn.edu.neu.java_fundamental.entity;
    opens cn.edu.neu.java_fundamental.util to javafx.fxml;
    exports cn.edu.neu.java_fundamental.util;
    opens cn.edu.neu.java_fundamental.mynode to javafx.fxml;
    exports cn.edu.neu.java_fundamental.mynode;




}