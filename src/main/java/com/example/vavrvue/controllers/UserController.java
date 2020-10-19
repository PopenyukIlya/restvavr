package com.example.vavrvue.controllers;

import com.example.vavrvue.domain.User;
import com.example.vavrvue.repos.UserRepo;
import com.example.vavrvue.services.UserService;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/restvavr")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //getall
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(){
        return userService.findAll();
    }

    //delete by id
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable ("id") Long id){
        return userService.delete(id);
    }

    //create
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid User user){
       return userService.create(user);
    }

    //update
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable ("id") Long id,
                                    @RequestBody @Valid User user){
        return userService.update(id,user);
    }
}
