package com.example.vavrvue;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private Repo repo;

    @GetMapping(value="/getdb")
    public String getdb() {
        repo.save(new User("Vasya"));
        repo.save(new User("Kolya"));
        repo.save(new User("Masha"));
        repo.save(new User("Tanya"));

        return "DB inited";
    }
}
