package com.example.vavrvue.repos;

import com.example.vavrvue.domain.User;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface UserRepo extends Repository<User,Long> {
    Option<User> findById(Long id);
    Option<User> findAll();
    Try deleteById(Long id);
    Try save(User user);
    Seq<User> findByName(String name);
}
