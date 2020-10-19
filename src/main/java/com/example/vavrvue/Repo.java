package com.example.vavrvue;

import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface Repo  extends Repository<User,Long> {
    Option<User> findById(long id);
    Option<User> findAll();
    Try deleteById(Long id);
    Try save(User user);
    Seq<User> findByName(String name);

}
