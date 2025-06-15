package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.GlobalData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Griderdao extends Userdao<Grider>{
    private static final String GRIDER_FILE = "Grider.json";
    private ArrayList<Grider> griders=new ArrayList<>();
    public List<Grider> getAllGriders(){
        if (griders.isEmpty()) {
            readSingleData(GRIDER_FILE);
        }
        return griders;
    }
    public int addGrider(Grider grider) throws IOException {
        griders.add(grider);
        return addSingleData(grider, GRIDER_FILE);
    }

    public Grider login(String logid, String logpwd) throws IOException {
        if(!((login(logid, logpwd, GRIDER_FILE)) == null)) {
            GlobalData.USER_ROLE = "grider";
        }
        return login(logid, logpwd, GRIDER_FILE);
    }

    @Override
    protected Class<Grider> getUserClass() {
        return Grider.class;
    }
}
