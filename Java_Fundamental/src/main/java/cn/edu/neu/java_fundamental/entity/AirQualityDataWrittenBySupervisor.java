package cn.edu.neu.java_fundamental.entity;

import cn.edu.neu.java_fundamental.util.AirQualityLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AirQualityDataWrittenBySupervisor {

    private AirQualityLevel AQL;
    private String province;
    private String city;
    private String district;
    private String date;
    private String text;

    public AirQualityDataWrittenBySupervisor()

    {

        this.AQL = AirQualityLevel.EXCELLENT;
        this.province = "none";
        this.city = "none";
        this.district = "none";
        this.date = "none";
        this.text = "none";
    }


    /**
     *监督员只能估计空气质量等级AQL，不能给出具体数值
     */
    public AirQualityDataWrittenBySupervisor(AirQualityLevel AQL, String province, String city, String district, String date, String text)
    {
        this.AQL = AQL;
        this.province = province;
        this.city = city;
        this.district = district;
        this.date = date;
        this.text = text;
    }

    public AirQualityDataWrittenBySupervisor(String province, String city, String district, String date) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.date = date;
    }
}
