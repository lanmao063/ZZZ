package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.FileTools;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupervisorSubmit {
    private static final String  filename = "supervisorSubmitLog.json";
    private static Map<String , List<AirQualityDataWrittenBySupervisor>> supervisorSubmitLog = null;




    /**
     * 读取提交数据
     */
    public void readAirQualityDatum() {
        try {
            String json = FileTools.readStringFromFile(filename);
            ObjectMapper mapper = new ObjectMapper();
            supervisorSubmitLog = mapper.readValue(json, new com.fasterxml.jackson.core.type.TypeReference<Map<String, List<AirQualityDataWrittenBySupervisor>>>() {});

        } catch (IOException e) {
            System.out.println("readAirQualityDatum failed: " + e.getMessage());
        }
    }


    /**
     * 添加数据
     * @param supervisor 提交人的对象
     * @param data 提交的数据
     * @return int数字表示写入成功与否
     */
    public int addAirQualityData(Supervisor supervisor, AirQualityDataWrittenBySupervisor data) throws IOException {
        if(supervisorSubmitLog==null){
            readAirQualityDatum();
            if(supervisorSubmitLog==null)
                supervisorSubmitLog = new HashMap<>();
        }
            if(supervisorSubmitLog.containsKey(supervisor.getId())){
                supervisorSubmitLog.get(supervisor.getId()).add(data);
            }
            else{
                supervisorSubmitLog.put(supervisor.getId(), java.util.List.of(data));
            }
        ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(supervisorSubmitLog);
            return FileTools.writeStringToFile(filename,json);
    }

    /**
     * 写入数据
     */
    public void writeAirQualityDatum() {
        if(supervisorSubmitLog==null){
            readAirQualityDatum();
            if(supervisorSubmitLog==null)
                supervisorSubmitLog = new HashMap<>();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(supervisorSubmitLog);
            FileTools.writeStringToFile(filename, json);
            System.out.println("writeAirQualityDatum success");
        }
        catch (IOException e) {
            System.out.println("writeAirQualityDatum failed:"+e.getMessage());
        }
    }

    /**
     * 获取所有数据
     * @return supervisorSubmitLog的map
     */
    public Map<String , List<AirQualityDataWrittenBySupervisor>> getAllData() {
        if(supervisorSubmitLog==null){
            readAirQualityDatum();
            if(supervisorSubmitLog==null)
                supervisorSubmitLog = new HashMap<>();
        }
        return supervisorSubmitLog;
    }


    public int deleteData(String supervisorID, AirQualityDataWrittenBySupervisor data)throws IOException{
        if (supervisorSubmitLog==null){
            readAirQualityDatum();
            if (supervisorSubmitLog==null)
                supervisorSubmitLog = new java.util.HashMap<>();
        }
        if(!supervisorSubmitLog.containsKey(supervisorID)){
            System.out.println("No such supervisorID");
            return 0;

        }
        else if(!supervisorSubmitLog.get(supervisorID).contains(data)) {
            System.out.println("No such data");
            return 0;
        }
        supervisorSubmitLog.get(supervisorID).remove(data);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(supervisorSubmitLog);
        return FileTools.writeStringToFile(filename, json);
    }


}
