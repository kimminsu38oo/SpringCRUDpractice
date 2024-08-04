package com.example.demo.dto;


import com.example.demo.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class CoffeeForm {
    private Long id;
    private String name;
    private String price;


    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }
}
