package cn.edu.neu.java_fundamental.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor



public class GridInfo {
    private String id;
    private String province;
    private String city;
    private String district;
    private String date;
    private String AQL;


    public GridInfo(String province, String city, String district, String date, String string) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.date = date;
        this.AQL = string;
    }
}