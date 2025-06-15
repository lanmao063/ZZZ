package cn.edu.neu.java_fundamental.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class Grider extends Supervisor {
    private String area;
    private boolean isOnline;
}
