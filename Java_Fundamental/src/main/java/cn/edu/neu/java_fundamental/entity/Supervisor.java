package cn.edu.neu.java_fundamental.entity;


import lombok.*;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Data
public class Supervisor{
    private String id;
    private String password;
    private String name;
    private String sex;
    private int score;

}
