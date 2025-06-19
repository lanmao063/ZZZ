package cn.edu.neu.java_fundamental.entity;

import cn.edu.neu.java_fundamental.util.AQI_Calculator;
import cn.edu.neu.java_fundamental.util.AirQualityLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class AirQualityDataWrittenByGrider extends AirQualityDataWrittenBySupervisor{
    private int AQI;
    private boolean isOnline;
    private double so2_concentration;
    private double co_concentration;
    private double spm_concentration;

    public AirQualityDataWrittenByGrider(boolean isOnline, String province, String city, String district, String date, double so2Value, double coValue, double spmValue) {
        super();
    }



    /**
     * AQI和AQL会自动计算
     */
    public AirQualityDataWrittenByGrider(boolean isOnline, String province, String city, String district, String date, String text, double so2_concentration, double co_concentration, double spm_concentration) {
        super(AirQualityLevel.getAQL_by_aqi(AQI_Calculator.calculateAQI_forAllPollutants(so2_concentration, co_concentration, spm_concentration)), province, city, district, date,text);
        this.isOnline = isOnline;
        this.so2_concentration = so2_concentration;
        this.co_concentration = co_concentration;
        this.spm_concentration = spm_concentration;
        this.AQI = AQI_Calculator.calculateAQI_forAllPollutants(so2_concentration, co_concentration, spm_concentration);
        super.setAQL(AirQualityLevel.getAQL_by_aqi(this.AQI));
    }







}
