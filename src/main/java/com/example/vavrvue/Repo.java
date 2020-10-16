package com.example.vavrvue;

import io.vavr.collection.Seq;
import io.vavr.control.Option;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo  extends org.springframework.data.repository.Repository<User,Long> {
    Option<User> findById(long id);
    Seq<User> findByName(String name);
    User deleteById(Long id);
    User save(User user);
}
