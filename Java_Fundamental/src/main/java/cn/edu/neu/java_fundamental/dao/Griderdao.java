package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.entity.Supervisor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Griderdao extends Userdao<Grider>{
    @Override
    protected Class<Grider> getUserClass() {
        return Grider.class;
    }
    private static final String GRIDER_FILE = "Grider.json";
    private ArrayList<Grider> griders=new ArrayList<>();
    public List<Grider> getAllGriders(){
        if (griders.isEmpty()) {
            griders = readSingleData(GRIDER_FILE);
        }
        return griders;
    }
    public int addGrider(Grider grider) throws IOException {
        griders.add(grider);
        return addSingleData(grider, GRIDER_FILE);
    }
    public int deleteGrider(Grider grider) throws IOException {
        griders.remove(grider);
        return deleteSingleData(grider.getId(), GRIDER_FILE);
    }
    public int updateGrider(Grider grider) throws IOException {
        for (int i = 0; i < griders.size(); i++) {
            if (griders.get(i).getId().equals(grider.getId())) {
                griders.set(i, grider);
                break;
            }
        }
        return updateSingleData(grider, GRIDER_FILE);
    }


}
