package com.example.vavrvue;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restvavr")

public class Controller {
    @Autowired
    private Repo repo;

    //find by id
    @GetMapping
    public ResponseEntity<?> getVavrResult(@RequestParam ("id") Long id) {
        Option<User> user=repo.findById(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user.get());
    }

    @DeleteMapping
    public void delete(@RequestParam ("id") Long id){
        repo.deleteById(id);
    }

    @PostMapping
    public void create(@RequestBody User user){
repo.save(user);
    }

}
