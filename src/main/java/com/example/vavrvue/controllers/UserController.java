package com.example.vavrvue.controllers;

import com.example.vavrvue.Exception.ApplicationError;
import com.example.vavrvue.controllers.dto.UserDto;
import com.example.vavrvue.domain.User;
import com.example.vavrvue.services.UserService;
import io.vavr.control.Either;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restvavr")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
    public Either<ApplicationError, UserDto> create(@RequestBody User user){
       return userService.create(user);
    }
//
//    //update
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Either<ApplicationError, UserDto> update(@PathVariable ("id") Long id, @RequestBody UserDto userDto){
        return userService.update(id,userDto);
    }
}
