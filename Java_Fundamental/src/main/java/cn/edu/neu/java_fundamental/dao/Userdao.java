package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.FileTools;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Userdao<T extends Supervisor> {
    protected ObjectMapper mapper = new ObjectMapper();
    protected abstract Class<T> getUserClass();
    public int addSingleData(T user, String filename) throws IOException {
        ArrayList<T> users=readSingleData(filename);
        users.add(user);
        String json = mapper.writeValueAsString(users);
        return FileTools.writeStringToFile(filename, json);
    }
    public int deleteSingleData(String id, String filename) throws IOException {
        ArrayList<T> users = readSingleData(filename);
        Iterator<T> iterator = users.iterator();
        while (iterator.hasNext()) {
            T user = iterator.next();
            if (user.getId().equals(id)) {
                iterator.remove();  // 用迭代器删除
                String json = mapper.writeValueAsString(users);
                return FileTools.writeStringToFile(filename, json);
            }
        }
        return -1; // 没找到
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
    public int updateSingleData(T updatedUser, String filename) throws IOException {
        ArrayList<T> users = readSingleData(filename);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser); // 找到并替换旧的用户对象
                String json = mapper.writeValueAsString(users);
                return FileTools.writeStringToFile(filename, json);
            }
        }
        return -1;
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
