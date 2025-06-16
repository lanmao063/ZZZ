package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.util.FileTools;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GriderSubmit  {
    private static final String  filename = "GriderSubmitLog.json";
    private static Map<String , List<AirQualityDataWrittenByGrider>> griderSubmitLog = null;

/**
 * 读取提交数据
 */
    public void readAirQualityDatum() {
        try {
            String json = FileTools.readStringFromFile(filename);
            ObjectMapper mapper = new ObjectMapper();
            griderSubmitLog = mapper.readValue(json, new com.fasterxml.jackson.core.type.TypeReference<Map<String, List<AirQualityDataWrittenByGrider>>>() {});

        } catch (IOException e) {
            System.out.println("readAirQualityDatum failed: " + e.getMessage());
        }
    }


/**
 * 添加提交数据并写入
 */
    public int addAirQualityData(Grider grider, AirQualityDataWrittenByGrider data) throws IOException {
        if (griderSubmitLog==null){
            readAirQualityDatum();
            if (griderSubmitLog==null)
                griderSubmitLog = new java.util.HashMap<>();
        }
       if(griderSubmitLog.containsKey(grider.getId()))
           griderSubmitLog.get(grider.getId()).add(data);
       else {
           ArrayList<AirQualityDataWrittenByGrider> list = new ArrayList<>();
           list.add(data);
           griderSubmitLog.put(grider.getId(),list);
       }
       ObjectMapper mapper = new ObjectMapper();
       String json = mapper.writeValueAsString(griderSubmitLog);
       return FileTools.writeStringToFile(filename, json);

    }


/**
 * 写入提交数据
 */
    public void writeAirQualityDatum() {
        if (griderSubmitLog==null){
            readAirQualityDatum();
            if (griderSubmitLog==null)
                griderSubmitLog = new java.util.HashMap<>();
        }
            try {
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(griderSubmitLog);
                FileTools.writeStringToFile(filename, json);
                System.out.println("writeAirQualityDatum success");
            }
            catch (IOException e) {
                System.out.println("writeAirQualityDatum failed"+e.getMessage());
            }

    }

/**
 * 获取所有提交数据
 */
    public Map<String , List<AirQualityDataWrittenByGrider>> getAllData() {
        if (griderSubmitLog==null){
            readAirQualityDatum();
            if (griderSubmitLog==null)
                griderSubmitLog = new java.util.HashMap<>();
        }
        return griderSubmitLog;
    }
}
