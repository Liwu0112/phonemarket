package org.example.dto;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class userLoginDto {
    Integer id;
    String password;
}
