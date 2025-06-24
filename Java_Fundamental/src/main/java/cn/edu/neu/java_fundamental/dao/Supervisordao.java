package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.entity.Supervisor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Supervisordao extends Userdao<Supervisor> {
    @Override
    protected Class<Supervisor> getUserClass() {
        return Supervisor.class;
    }
    private static final String SUPERVISOR_FILE="Supervisor.json";
    private ArrayList<Supervisor> supervisors=new ArrayList<>();
    public List<Supervisor> getAllSupervisors(){
        if (supervisors.isEmpty()) {
            supervisors=readSingleData(SUPERVISOR_FILE);
        }
        return supervisors;
    }
    public int addSupervisor(Supervisor supervisor) throws IOException {
        supervisors.add(supervisor);
        return addSingleData(supervisor, SUPERVISOR_FILE);
    }
    public int deleteSupervisor(Supervisor supervisor) throws IOException {
        int res = deleteSingleData(supervisor.getId(), SUPERVISOR_FILE);
        if (res != -1) {
            supervisors.clear(); // 清空缓存，确保下次重新加载
        }
        return res;
    }
    public int updateSupervisor(Supervisor supervisor) throws IOException {
        for (int i = 0; i < supervisors.size(); i++) {
            if (supervisors.get(i).getId().equals(supervisor.getId())) {
                supervisors.set(i, supervisor);
                break;
            }
        }
        return updateSingleData(supervisor,SUPERVISOR_FILE);
    }


}



