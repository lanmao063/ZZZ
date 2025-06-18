package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.FileTools;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Userdao<T extends Supervisor> {
    protected ObjectMapper mapper = new ObjectMapper();
    protected abstract Class<T> getUserClass();
    public int addSingleData(T user,String filename) throws IOException {
        ArrayList<T> users=readSingleData(filename);
        users.add(user);
        String json = mapper.writeValueAsString(users);
        return FileTools.writeStringToFile(filename, json);
    }


    public ArrayList<T> readSingleData(String filename) {
        try{
            String json=FileTools.readStringFromFile(filename);
            CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, getUserClass());
            return mapper.readValue(json, type);
        }
        catch(IOException e){
            System.out.println(filename+" readSingleData failed "+e.getMessage());
            return new ArrayList<>();
        }
    }
    public T login(String logid, String logpwd,String filename) throws IOException {
        ArrayList<T> users= readSingleData(filename);
            for (T user : users) {
                if (user.getId().equals(logid) && user.getPassword().equals(logpwd)) {
                    return user;
            }
        }
        return null;
    }


}
