package cn.edu.neu.java_fundamental.entity;


import lombok.*;

@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Supervisor{
    @Getter @Setter
    private String name;
    private String sex;
    private String id;
    private String password;
    private int score;

}
