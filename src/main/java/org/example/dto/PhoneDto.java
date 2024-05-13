package org.example.dto;

import lombok.Data;

@Data
public class PhoneDto {
    private Integer id;
    private String brand;
    private String model;
    private Integer storage;
    private Integer memory;
    private String processor;
    private Integer number;
    private Integer price;

}
