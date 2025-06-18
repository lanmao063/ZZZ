package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.entity.Supervisor;

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
    public int addGrider(Supervisor supervisor) throws IOException {
        Grider grider = (Grider) supervisor;
        grider.setScore(0);
        grider.setOnline(true);
        grider.setArea("null");
        griders.add(grider);
        return addSingleData(grider, GRIDER_FILE);
    }

    @Override
    protected Class<Grider> getUserClass() {
        return Grider.class;
    }
}
