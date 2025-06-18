package cn.edu.neu.java_fundamental.entity;

import lombok.*;

@Data
@ToString
public class Grider extends Supervisor {
    private String area;
    private boolean isOnline;

    public Grider(String id, String password, String name, String sex, int score, String area, boolean isOnline)
    {
        super(id, password, name, sex, score);
        this.area = area;
        this.isOnline = isOnline;
    }

    public boolean getIsOnline()
    {
        return isOnline;
    }


}
