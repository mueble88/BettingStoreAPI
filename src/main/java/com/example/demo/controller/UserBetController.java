package com.example.demo.controller;

import com.example.demo.model.UserBet;
import com.example.demo.repository.UserBetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserBetController  {

    @Autowired
    private UserBetRepository repository;

    @GetMapping("/userbets")
    private List<UserBet> allUserBet(){
        return repository.findAll();
    }

    @GetMapping("/userbets/{id}")
    private List<UserBet> getUserBetById(@PathVariable("id") long id){
        return repository.findById(id);
    }

    @PostMapping("/userbet")
    private UserBet createUserBet(@RequestBody UserBet userBet){
        return repository.save(userBet);
    }

    @PutMapping("/userbet/{id}")
    private UserBet updateUserBet(@PathVariable long id,@RequestBody UserBet userBet){
        return repository.save(userBet);
    }

    @DeleteMapping("userbet/{id}")
    public void deleteUserBet(@PathVariable("id") long id) {

        repository.deleteById(id);
    }
}
