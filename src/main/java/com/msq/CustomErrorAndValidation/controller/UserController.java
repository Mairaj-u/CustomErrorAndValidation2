package com.msq.CustomErrorAndValidation.controller;

import com.msq.CustomErrorAndValidation.dto.UserRequest;
import com.msq.CustomErrorAndValidation.entity.User;
import com.msq.CustomErrorAndValidation.exception.UserNotFound;
import com.msq.CustomErrorAndValidation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
  private  UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRequest userRequest){
        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUsers = userService.getAllUser();

        if (allUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) throws UserNotFound, InterruptedException {
        System.out.println(id);
        System.out.println(new Date().toString());
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
    }

}
