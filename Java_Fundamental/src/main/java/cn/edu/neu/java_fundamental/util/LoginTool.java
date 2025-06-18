package cn.edu.neu.java_fundamental.util;

import cn.edu.neu.java_fundamental.dao.*;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.entity.Administrator;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class LoginTool {
    private String getFilename(String role) {
        return switch (role) {
            case "Grider" -> "Grider.json";
            case "Administrator" -> "Administrator.json";
            case "Supervisor" -> "Supervisor.json";
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }
    public boolean login(String logid, String logpwd) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        String [] roles = {"Grider", "Administrator", "Supervisor"};
        for(String role : roles) {
            Userdao<? extends Supervisor> dao= DaoFactory.createDao(role);
            String filename = getFilename(role);
            Supervisor user = dao.login(logid, logpwd, filename);
            if(user != null) {
                GlobalData.setLoginData(user,role);
                System.out.println(role + "登录成功");
                return true;
            }
        }
        System.out.println("登录失败，用户名或密码错误");
        return false;
    }
}
