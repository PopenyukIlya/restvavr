package com.example.vavrvue.services;

import com.example.vavrvue.domain.User;
import com.example.vavrvue.repos.UserRepo;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo repo;

    public UserService(UserRepo userRepo) {
        this.repo = userRepo;
    }

    public ResponseEntity<?> findAll() {
        Option<User> users=repo.findAll();
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> delete(Long id) {
        Try delete=repo.deleteById(id);
        if (delete.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> create(User user) {
        Try<User> saveUser=repo.save(user);
        if (saveUser.isSuccess()){
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> update(Long id, User user) {
        Option<User> oldUser=repo.findById(id);
        if (oldUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            User olDUser= oldUser.get();
            olDUser.setName(user.getName());
            Try<User> saveUser= repo.save(olDUser);
            return new ResponseEntity<>(saveUser.get(),HttpStatus.OK);
        }  }
}
