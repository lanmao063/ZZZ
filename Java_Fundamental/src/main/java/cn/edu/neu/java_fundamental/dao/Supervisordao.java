package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.Supervisor;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.edu.neu.java_fundamental.util.FileTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Supervisordao {
    private static final String SUPERVISOR_FILE="Supervisor.json";
    private List<Supervisor> supervisors=null;
    public int addSupervisor(Supervisor supervisor) throws IOException {
        if (supervisors==null)
            readSupervisors();
        supervisors.add(supervisor);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(supervisors);
        return FileTools.writeStringToFile(SUPERVISOR_FILE, json);
        }


    private void readSupervisors() {
        try{
            String json=FileTools.readStringFromFile(SUPERVISOR_FILE);
            ObjectMapper mapper = new ObjectMapper();
            supervisors = mapper.readValue(json,mapper.getTypeFactory().constructCollectionType(List.class,Supervisor.class));
        }
       catch(IOException e){
            supervisors = new ArrayList<>();
            System.out.println("readSupervisors failed "+e.getMessage());

        }

    }
    public Supervisor login(String logid, String logpwd) throws IOException {
        if (supervisors==null)
            readSupervisors();
        for (Supervisor supervisor : supervisors) {
            if (supervisor.getId().equals(logid) && supervisor.getPassword().equals(logpwd)) {
                return supervisor;
            }
        }
        return null;
    }

    public List<Supervisor> getAllSupervisors() throws IOException {
        if (supervisors==null)
            readSupervisors();
        return supervisors;
    }
}



