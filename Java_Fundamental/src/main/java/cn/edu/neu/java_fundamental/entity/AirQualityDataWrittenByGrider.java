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

    public int getAQI() {
        return AQI;
    }

    public void setAQI(int AQI) {
        this.AQI = AQI;
    }

    public double getSo2_concentration() {
        return so2_concentration;
    }

    public void setSo2_concentration(double so2_concentration) {
        this.so2_concentration = so2_concentration;
    }

    public double getCo_concentration() {
        return co_concentration;
    }

    public void setCo_concentration(double co_concentration) {
        this.co_concentration = co_concentration;
    }

    public double getSpm_concentration() {
        return spm_concentration;
    }

    public void setSpm_concentration(double spm_concentration) {
        this.spm_concentration = spm_concentration;
    }



    /**
     * AQI和AQL会自动计算
     */
    public AirQualityDataWrittenByGrider(Supervisor submitter, String province, String city, String district, Date date, double so2_concentration, double co_concentration, double spm_concentration) {
        super(submitter,AirQualityLevel.EXCELLENT, province, city, district, date);
        this.so2_concentration = so2_concentration;
        this.co_concentration = co_concentration;
        this.spm_concentration = spm_concentration;
        this.AQI = AirQualityLevel.getAQL_by_pollutant(so2_concentration,co_concentration,spm_concentration).getAql_id();
        super.setAQL(AirQualityLevel.getAQL_by_aqi(this.AQI));

    }



}
