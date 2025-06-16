package cn.edu.neu.java_fundamental.entity;

import cn.edu.neu.java_fundamental.util.FileTools;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    private static final String GRIDER_FILE = "AirQualityDataWrittenBySupervisor.json";
    private List<GridInfo> griders = new ArrayList<>();
    public List<GridInfo> readGridInfo() throws IOException {
        try {
            String gridInfo = FileTools.readStringFromFile(GRIDER_FILE);
            ObjectMapper mapper = new ObjectMapper();
            griders = mapper.readValue(gridInfo, mapper.getTypeFactory().constructCollectionType(List.class, GridInfo.class));
        }
        catch(IOException e){
            griders = new ArrayList<>();
            System.out.println("readSupervisors failed "+e.getMessage());

        }
        return griders;
    }

}
