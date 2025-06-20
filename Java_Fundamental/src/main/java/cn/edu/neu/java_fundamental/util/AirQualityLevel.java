package cn.edu.neu.java_fundamental.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum AirQualityLevel {

    EXCELLENT(1, "优","Green","空气质量令人满意，基本无空气污染","各类人群可正常活动",0,50),
    GOOD(2, "良","Yellow","空气质量可接受，但某些污染物可能对极少数异常敏感人群健康有较弱影响","极少数异常敏感人群应减少户外活动",51,100),
    LIGHT_POLLUTED(3, "轻度污染","Orange","易感人群症状有轻度加剧，健康人群出现刺激症状","儿童、老年人及心脏病、呼吸疾病患者应减少长时间、高强度的户外活动",101,150),
    MODERATE_POLLUTED(4, "中度污染","Red","进一步加剧易感人群症状，可能对健康人群心脏、呼吸系统有影响","儿童、老年人及心脏病、呼吸系统疾病患者避免长时间、高强度的户外锻练，一般人群适量减少户外运动",151,200),
    HEAVY_POLLUTED(5, "重度污染","Purple","心脏病、肺病患者症状显著加剧，运动耐受力降低，健康人群普遍出现症状","儿童、老年人及心脏病、肺病患者应停留在室内，停止户外运动，一般人群减少户外运动",201,300),
    SEVERE_POLLUTED(6, "严重污染","Brown","健康人群运动耐受力降低，有明显强烈症状，提前出现某些疾病","儿童、老年人和病人应当留在室内，避免体力消耗，一般人群应避免户外活动",301,500);

    int aql_id;
    String chinese_explain;
    String color;
    String health_impact;
    String takes_steps;
    int max_aqi;
    int min_aqi;

    @Override
    public String toString() {
        return "AirQualityLevel{" +
                "aql_id=" + aql_id +
                ", chinese_explain='" + chinese_explain + '\'' +
                ", color='" + color + '\'' +
                ", health_impact='" + health_impact + '\'' +
                ", takes_steps='" + takes_steps + '\'' +
                ", max_aqi=" + max_aqi +
                ", min_aqi=" + min_aqi +
                '}';
    }

    /**
     * 根据aql数字等级返回对应的AQI等级
     * @param aql_id aql数字等级
     * @return AQL等级
     */

    public static AirQualityLevel getAQL_by_aql(int aql_id){
        for(AirQualityLevel level:AirQualityLevel.values()){
            if(aql_id==level.aql_id)
                return level;
        }
        throw new IllegalArgumentException("该AQI不在当前等级分类之中");

    }


    /**
     * 根据AQI返回对应的AQI等级
     * @param aqi AQI
     * @return AQL等级
     */

    public static AirQualityLevel getAQL_by_aqi(int aqi){
        if(aqi<=50)
            return AirQualityLevel.EXCELLENT;
        else if(aqi<=100)
            return AirQualityLevel.GOOD;
        else if(aqi<=150)
            return AirQualityLevel.LIGHT_POLLUTED;
        else if(aqi<=200)
            return AirQualityLevel.MODERATE_POLLUTED;
        else if(aqi<=300)
            return AirQualityLevel.HEAVY_POLLUTED;
        else
            return AirQualityLevel.SEVERE_POLLUTED;
    }

    /**
     * 根据污染物返回对应的AQI等级
     * @param so2_concentration so2浓度
     * @param co_concentration co浓度
     * @param spm_concentration spm浓度
     * @return AQL等级
     */

    public static AirQualityLevel getAQL_by_pollutant(double so2_concentration,double co_concentration,double spm_concentration){
        return getAQL_by_aqi(AQI_Calculator.calculateAQI_forAllPollutants(so2_concentration,co_concentration,spm_concentration));
    }

    public int getLevel() {
        return aql_id;
    }
}
