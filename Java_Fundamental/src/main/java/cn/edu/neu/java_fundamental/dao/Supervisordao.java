package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.Supervisor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Supervisordao extends Userdao<Supervisor> {
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

    @Override
    protected Class<Supervisor> getUserClass() {
        return Supervisor.class;
    }
}



