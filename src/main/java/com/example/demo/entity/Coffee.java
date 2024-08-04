package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String price;

    public Coffee(Long id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void patch(Coffee newCoffee) {
        if (newCoffee.name != null) {
            name = newCoffee.name;
        }
        if (newCoffee.price != null) {
            price = newCoffee.price;
        }
    }
}
