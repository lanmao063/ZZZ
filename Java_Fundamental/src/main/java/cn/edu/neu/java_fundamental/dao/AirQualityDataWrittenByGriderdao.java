package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import cn.edu.neu.java_fundamental.util.FileTools;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirQualityDataWrittenByGriderdao {
    private static final String AIRQUALITYDATA_FILE = "AirQualityDataWrittenByGrider";
    private static List<AirQualityDataWrittenByGrider> AirQualityDatum = null;

    public void readAirQualityDatum(){
        try{
            String json=FileTools.readStringFromFile(AIRQUALITYDATA_FILE);
            ObjectMapper mapper = new ObjectMapper();
            AirQualityDatum = mapper.readValue(json,mapper.getTypeFactory().constructCollectionType(List.class,AirQualityDataWrittenByGrider.class));
        }
        catch(IOException e){
            AirQualityDatum = new ArrayList<>();
            System.out.println("readAirQualityDatum"+e.getMessage());
        }

    }

    public int addAirQualityDataWrittenByGrider(AirQualityDataWrittenByGrider data) throws IOException{
        if (AirQualityDatum==null)
            readAirQualityDatum();
        AirQualityDatum.add(data);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(AirQualityDatum);
        return FileTools.writeStringToFile(AIRQUALITYDATA_FILE,json);
    }


    public void writeAirQualityDatum() {
        if (AirQualityDatum==null)
            readAirQualityDatum();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(AirQualityDatum);
            FileTools.writeStringToFile(AIRQUALITYDATA_FILE, json);
        }  catch (IOException e) {
            System.out.println("writeAirQualityDatum failed"+e.getMessage());
        }
    }


    public List<AirQualityDataWrittenByGrider> getAllData(){
        if (AirQualityDatum==null)
            readAirQualityDatum();
        return AirQualityDatum;
    }


}
