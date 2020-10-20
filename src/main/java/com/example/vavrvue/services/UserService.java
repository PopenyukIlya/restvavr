package com.example.vavrvue.services;

import com.example.vavrvue.Exception.ApplicationError;
import com.example.vavrvue.controllers.dto.UserDto;
import com.example.vavrvue.domain.User;
import com.example.vavrvue.repos.UserRepo;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;


    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserDto> findAll() {
        return userRepo.findAll()
                .stream()
                .map(tag -> modelMapper.map(tag, UserDto.class))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        userRepo.deleteById(id);}

    //don't work exactly

    public Either<ApplicationError, User>  create(UserDto userDto) {
       User user=new User();
       user.setName(userDto.getName());
        return Try
                .of(() -> userRepo.save(user))
                .toEither()
                .mapLeft(exc -> {
                    String message = String.format("Exception while saving a new user with name %s and id %s.", user.getName(), user.getId());
                    return new ApplicationError(message);
                });
    }

//    //don't work
//    public Either<ApplicationError, UserDto> update(UserDto userDto) {
//        return userRepo.findByNameAndId(userDto.getName())
//                .toEither(() -> new ApplicationError(String.format("Can't find user by id %s and user name %s",  userDto.getName())))
//                .peek(user -> user.setName(userDto.getName()))
//                .map(userRepo::save)
//                .map(user -> modelMapper.map(user, UserDto.class));
//    }


}
