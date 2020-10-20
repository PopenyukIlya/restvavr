package com.example.vavrvue.repos;

import com.example.vavrvue.domain.User;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Option<User> findByNameAndId(String name,Long id);
}
