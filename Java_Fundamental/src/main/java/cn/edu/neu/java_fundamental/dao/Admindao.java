package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.Administrator;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.GlobalData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Admindao extends Userdao<Administrator> {
    @Override
    protected Class<Administrator> getUserClass() {
        return Administrator.class;
    }
    private static final String ADMINISTRATOR_FILE="Administrator.json";
    private ArrayList<Administrator> administrators=new ArrayList<>();
    public List<Administrator> getAllAdministrators(){
        if (administrators.isEmpty()) {
            administrators=readSingleData(ADMINISTRATOR_FILE);
        }
        return administrators;
    }
    public int addAdministrator(Administrator administrator) throws IOException {
        administrators.add(administrator);
        return addSingleData(administrator, ADMINISTRATOR_FILE);
    }
    public Administrator login(String logid, String logpwd) throws IOException {
        if (!((login(logid, logpwd, ADMINISTRATOR_FILE)) == null))
        {
            GlobalData.USER_ROLE = "administrator";
        }
        return login(logid, logpwd, ADMINISTRATOR_FILE);
    }

}
