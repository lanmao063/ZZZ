package cn.edu.neu.java_fundamental.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AirQualityDataWrittenBySupervisor {
    private AirQualityLevel  AQL;
    private String province;
    private String city;
    private String district;
    private Date date;

    public AirQualityDataWrittenBySupervisor()
    {
        this.AQL = AirQualityLevel.EXCELLENT;
        this.province = "none";
        this.city = "none";
        this.district = "none";
        this.date = new Date();
    }

    public AirQualityDataWrittenBySupervisor(AirQualityLevel AQL, String province, String city, String district, Date date)
    {
        this.AQL = AQL;
        this.province = province;
        this.city = city;
        this.district = district;
        this.date = date;
    }

}
