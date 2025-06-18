package cn.edu.neu.java_fundamental.entity;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Grider extends Supervisor {
    private String area;
    private boolean isOnline;
    private GridInfo gridInfo;

    public Grider(String id, String password, String name, String sex, int score, String area, boolean b) {
        super(id, password, name, sex, score);
        this.area = area;
        this.isOnline = b;
    }

    public boolean getIsOnline()
    {
        return isOnline;
    }
}
