package org.example.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String nickname;
    private String password;
    private int age;
    private String phonenumber;
    private String address;
}
