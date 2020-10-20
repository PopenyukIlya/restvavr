package com.example.vavrvue.controllers;

import com.example.vavrvue.Exception.ApplicationError;
import com.example.vavrvue.controllers.dto.UserDto;
import com.example.vavrvue.domain.User;
import com.example.vavrvue.repos.UserRepo;
import com.example.vavrvue.services.UserService;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/restvavr")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //getall
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UserDto> findAll(){
        return userService.findAll();
    }

    //delete by id
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable ("id") Long id){
         userService.delete(id);
    }

    //create
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Either<ApplicationError, User> create(@RequestBody UserDto userDto){
       return userService.create(userDto);
    }
//
//    //update
//    @RequestMapping(method = RequestMethod.PUT)
//    public Either<ApplicationError, UserDto> update(@RequestBody UserDto userDto){
//        return userService.update(userDto);
//    }
}
