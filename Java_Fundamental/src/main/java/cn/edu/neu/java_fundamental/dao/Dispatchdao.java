package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.util.FileTools;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dispatchdao {
    private static final String  filename = "dispatchLog.json";
    private static Map<String , List<AirQualityDataWrittenBySupervisor>> dispatchLog = null;


    public void readDispatchLog()
    {
        try {
            String json = FileTools.readStringFromFile(filename);
            ObjectMapper mapper = new ObjectMapper();
            dispatchLog = mapper.readValue(json, new com.fasterxml.jackson.core.type.TypeReference<Map<String, List<AirQualityDataWrittenBySupervisor>>>() {});

        } catch (IOException e) {
            System.out.println("readDispatchLog failed: " + e.getMessage());
        }
    }

    public int  writeDispatchLog() {
        if(dispatchLog == null) {
            readDispatchLog();
            if(dispatchLog == null)
                dispatchLog=new HashMap<>();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(dispatchLog);
            int i= FileTools.writeStringToFile(filename, json);
            System.out.println("writeDispatchLog success");
            return i;
        }catch (IOException e){
            System.out.println("writeDispatchLog failed:"+e.getMessage());
            return -1;
        }


    }

    public int addDispatchLog(Grider grider, AirQualityDataWrittenBySupervisor  data) {
        if(dispatchLog == null) {
            readDispatchLog();
            if(dispatchLog == null)
                dispatchLog=new HashMap<>();
        }
        if(dispatchLog.containsKey(grider.getId()))
            dispatchLog.get(grider.getId()).add(data);
            else
                dispatchLog.put(grider.getId(), java.util.List.of(data));
            try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(dispatchLog);
            return FileTools.writeStringToFile(filename,json);
            } catch (Exception e) {
                System.out.println("writeDispatchLog failed:"+e.getMessage());
            return -1;
        }

    }

    public  Map<String, List<AirQualityDataWrittenBySupervisor>> getDispatchLog() {
        if(dispatchLog == null) {
            readDispatchLog();
            if(dispatchLog == null)
                dispatchLog=new HashMap<>();
        }
        return dispatchLog;
    }
}
