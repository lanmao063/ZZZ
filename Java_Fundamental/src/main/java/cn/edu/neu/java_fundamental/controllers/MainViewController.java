package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.util.GlobalData;

import java.util.Objects;
public class MainViewController {
    private void initialize() {
    if (Objects.equals(GlobalData.USER_ROLE, "administrator")) {
        System.out.println("Loading Administrator View");

    }
    }
}
