package cn.edu.neu.java_fundamental.util;

import cn.edu.neu.java_fundamental.dao.Admindao;
import cn.edu.neu.java_fundamental.dao.Supervisordao;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.dao.Griderdao;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.entity.Administrator;


import java.io.IOException;

public class LoginTool {
    public boolean login(String logid, String logpwd) {
        Griderdao griderDao = new Griderdao();
        try {
            Grider grider = griderDao.login(logid, logpwd);
            GlobalData.CURRENT_USER =grider;
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
            if (supervisor != null) {
                System.out.println("监督员");
                return true;
            }
        } catch (IOException e) {
            System.out.println("访问监督员数据时出错: " + e.getMessage());
            return false;
        }
        return false;
    }
}
