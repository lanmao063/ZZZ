package cn.edu.neu.java_fundamental;


import cn.edu.neu.java_fundamental.dao.GriderSubmit;
import cn.edu.neu.java_fundamental.dao.SupervisorSubmit;
import cn.edu.neu.java_fundamental.dao.Supervisordao;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.AirQualityLevel;


import java.util.Date;
import java.util.List;
import java.util.Map;

public class Demo  {

    public static void main(String[] args){
        Supervisordao supervisordao=new Supervisordao();
        List<Supervisor> list=supervisordao.getAllSupervisors();
        SupervisorSubmit submit=new SupervisorSubmit();
        try {
            submit.addAirQualityData(list.get(0),new AirQualityDataWrittenBySupervisor(AirQualityLevel.EXCELLENT,"北京","北京","东城区",new Date()));
            Map<String, List<AirQualityDataWrittenBySupervisor >> map=submit.getAllData();
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
