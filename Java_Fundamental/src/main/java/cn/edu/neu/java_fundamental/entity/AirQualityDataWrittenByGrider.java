package cn.edu.neu.java_fundamental.entity;

import cn.edu.neu.java_fundamental.util.AirQualityLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
public class AirQualityDataWrittenByGrider extends AirQualityDataWrittenBySupervisor{
    private int AQI;
    private double so2_concentration;
    private double co_concentration;
    private double spm_concentration;

    public AirQualityDataWrittenByGrider() {
        super();
    }

    /**
     * AQI和AQL会自动计算
     */
    public AirQualityDataWrittenByGrider(String province, String city, String district, Date date, double so2_concentration, double co_concentration, double spm_concentration) {
        super(AirQualityLevel.EXCELLENT, province, city, district, date);
        this.so2_concentration = so2_concentration;
        this.co_concentration = co_concentration;
        this.spm_concentration = spm_concentration;
        this.AQI = AirQualityLevel.getAQL_by_pollutant(so2_concentration,co_concentration,spm_concentration).getAql_id();
        super.setAQL(AirQualityLevel.getAQL_by_aqi(this.AQI));

    }



}
