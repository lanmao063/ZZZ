package cn.edu.neu.java_fundamental;


import cn.edu.neu.java_fundamental.dao.GriderSubmit;
import cn.edu.neu.java_fundamental.dao.Supervisordao;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.entity.Supervisor;



import java.util.Date;
import java.util.List;
import java.util.Map;

public class Demo  {

    public static void main(String[] args){
        Supervisordao supervisordao=new Supervisordao();
        List<Supervisor> list=supervisordao.getAllSupervisors();
        GriderSubmit griderSubmit=new GriderSubmit();
        try {
            griderSubmit.addAirQualityData(list.get(0),new AirQualityDataWrittenByGrider("北京","shenyang","北京",new Date(),10,10,10));
            Map<String, List<AirQualityDataWrittenByGrider >> map=griderSubmit.getAllData();
            for(String supervisor:map.keySet()){
                for(AirQualityDataWrittenBySupervisor data:map.get(supervisor)){
                    System.out.println(data);
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
