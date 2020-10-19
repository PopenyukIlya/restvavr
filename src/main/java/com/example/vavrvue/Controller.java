package com.example.vavrvue;

import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/restvavr")
public class Controller {

    @Autowired
    private Repo repo;

    //getall
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(){
        Option<User> users=repo.findAll();
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }


    //delete by id
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable ("id") Long id){
        Try delete=repo.deleteById(id);
        if (delete.isSuccess()){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //create
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid User user){
        Try<User> saveUser=repo.save(user);
        if (saveUser.isSuccess()){
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
    }

    //update
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable ("id") Long id,
                                    @RequestBody @Valid User user){
           Try<User> saveUser= repo.save(user);
           if (saveUser.isSuccess()){
               return new ResponseEntity<>(user, HttpStatus.OK);
           }else {
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
    }
}
