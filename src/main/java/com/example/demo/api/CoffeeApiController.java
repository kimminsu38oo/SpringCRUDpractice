package com.example.demo.api;

import com.example.demo.dto.CoffeeForm;
import com.example.demo.entity.Coffee;
import com.example.demo.repository.CoffeeRepository;
import com.example.demo.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.results.graph.collection.internal.CollectionFetch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CoffeeApiController {
    private final CoffeeService coffeeService;

    // 단일 조회
    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id) {

        Coffee find = coffeeService.show(id);

        return (find != null) ?
                ResponseEntity.status(HttpStatus.OK).body(find) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 전부 조회
    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return coffeeService.index();
    }

    // 생성
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm dto) {

        Coffee createdCoffee = coffeeService.create(dto);
        return (createdCoffee != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdCoffee) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto) {
        Coffee updated = coffeeService.update(id, dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee deleted = coffeeService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
