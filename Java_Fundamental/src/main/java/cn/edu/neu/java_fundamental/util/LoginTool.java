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
       /* Griderdao griderDao = new Griderdao();
        try {
            Grider grider = griderDao.login(logid, logpwd);
            *//*GlobalData.CURRENT_GRIDER =grider;*//*
            if (grider != null) {
                System.out.println("网格员");
                return true;
            }
        } catch (IOException e) {
            System.out.println("访问网格员数据时出错: " + e.getMessage());
            return false;
        }
        Admindao adminDao = new Admindao();
        try {
            Administrator admin = adminDao.login(logid, logpwd);
//            GlobalData.CURRENT_ADMINISTRATOR = admin;
            if (admin != null) {
                System.out.println("管理员");
                return true;
            }
        } catch (IOException e) {
            System.out.println("访问管理员数据时出错: " + e.getMessage());
            return false;
        }
        Supervisordao supervisorDao=new Supervisordao();
        try {
            Supervisor supervisor = supervisorDao.login(logid, logpwd);
//            GlobalData.CURRENT_SUPERVISOR = supervisor;
            if (supervisor != null) {
                System.out.println("监督员");
                return true;
            }
        } catch (IOException e) {
            System.out.println("访问监督员数据时出错: " + e.getMessage());
            return false;
        }
        return false;*/
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
