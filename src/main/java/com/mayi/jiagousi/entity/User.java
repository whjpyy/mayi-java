package com.mayi.jiagousi.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String pwd;
}
