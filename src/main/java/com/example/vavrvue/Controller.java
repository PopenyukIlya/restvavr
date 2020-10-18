package com.example.vavrvue;

import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    //find by id
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable ("id") Long id) {
        Option<User> user=repo.findById(id);
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    //delete by id
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable ("id") Long id){
        Option<User> oldUser=repo.findById(id);
        if (oldUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //create
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody User user){
        if (user.getName()==""){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Seq<User> userInDb=repo.findByName(user.getName());
        if (!userInDb.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            Try<User> saveUser=repo.save(user);
            return new ResponseEntity<>(saveUser.get(),HttpStatus.CREATED);}
    }

    //update
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable ("id") Long id,
                                    @RequestBody User user){
        Option<User> oldUser=repo.findById(id);
        Seq<User> byNewName=repo.findByName(user.getName());
        if (!byNewName.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        if (oldUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
           Try<User> saveUser= repo.save(new User(id,user.getName()));
            return new ResponseEntity<>(saveUser.get(),HttpStatus.OK);
        }
    }
}
