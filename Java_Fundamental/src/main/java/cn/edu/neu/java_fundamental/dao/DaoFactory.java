package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.Supervisor;

import java.lang.reflect.InvocationTargetException;

public class DaoFactory {
    public static <T extends Supervisor> Userdao<T> createDao(String role) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String daoClassName=null;
        switch (role) {
            case "Grider":
                daoClassName = "cn.edu.neu.java_fundamental.dao.Griderdao";
                break;
            case "Administrator":
                daoClassName = "cn.edu.neu.java_fundamental.dao.Admindao";
                break;
            case "Supervisor":
                daoClassName = "cn.edu.neu.java_fundamental.dao.Supervisordao";
                break;
                default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
            Class<?> daoClass=Class.forName(daoClassName);
            return (Userdao<T>) daoClass.getDeclaredConstructor().newInstance();
    }
}
