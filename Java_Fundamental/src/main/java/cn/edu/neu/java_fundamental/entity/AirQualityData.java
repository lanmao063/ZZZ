package cn.edu.neu.java_fundamental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AirQualityData {
    private int aqi;
    private AirQualityLevel  aql;
    private String province;
    private String city;
    private String district;
    private double so2_concentration;
    private double co_concentration;
    private double spm_concentration;

    Date date;
}
