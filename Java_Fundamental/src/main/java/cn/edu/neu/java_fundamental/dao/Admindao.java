package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.Administrator;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.entity.Supervisor;

import java.io.File;
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
    public int deleteAdministrator(Administrator administrator) throws IOException {
        administrators.remove(administrator);
        return deleteSingleData(administrator.getId(), ADMINISTRATOR_FILE);
    }
    public int updateAdmin(Administrator administrator) throws IOException {
        for (int i = 0; i < administrators.size(); i++) {
            if (administrators.get(i).getId().equals(administrator.getId())) {
                administrators.set(i, administrator);
                break;
            }
        }
        return updateSingleData(administrator, ADMINISTRATOR_FILE);
    }

}
