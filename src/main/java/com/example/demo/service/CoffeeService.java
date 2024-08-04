package com.example.demo.service;

import com.example.demo.dto.CoffeeForm;
import com.example.demo.entity.Coffee;
import com.example.demo.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    //단일 조회
    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee create(CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        if (coffee.getId()==null) {
            return null;
        }

        return coffeeRepository.save(coffee);

    }

    public Coffee update(Long id, CoffeeForm dto) {
        Coffee newCoffee = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if (target == null || !newCoffee.getId().equals(id)) {
            return null;
        }

        target.patch(newCoffee);

        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id) {
        Coffee find = coffeeRepository.findById(id).orElse(null);

        if (find == null) {
            return null;
        }
        coffeeRepository.delete(find);
        return find;
    }
}
