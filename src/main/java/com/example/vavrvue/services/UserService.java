package com.example.vavrvue.services;

import com.example.vavrvue.Exception.ApplicationError;
import com.example.vavrvue.controllers.dto.UserDto;
import com.example.vavrvue.domain.User;
import com.example.vavrvue.repos.UserRepo;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
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
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        userRepo.deleteById(id);}

    //work!!!!exactly
//   добавиь проверку на существующего юзера
    public Either<ApplicationError, User>  create(UserDto userDto) {
      User user=new User(userDto.getName());
       return Try.of(()-> userRepo.save(user))
               .toEither(()->  new ApplicationError(String.format("Exception while saving a new user with name %s and id %s.", user.getName(), user.getId()))
               );
    }
//
    //don't work
    public Either<ApplicationError, UserDto> update(Long id,UserDto userDto) {
        return Option.ofOptional(userRepo.findById(id))
                .peek(user -> {
            user.setName(userDto.getName());
        }).map(userRepo::save).map(user -> modelMapper.map(user,UserDto.class))
                .toEither(() -> new ApplicationError(String.format("User with id: %s not found: ", id)));
    }


}
