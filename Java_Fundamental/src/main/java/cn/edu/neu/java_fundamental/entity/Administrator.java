package cn.edu.neu.java_fundamental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrator extends Supervisor{
    private String territory;
}

