package com.example.demo.controller;

import com.example.demo.model.Matches;
import com.example.demo.repository.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchesController {

    @Autowired
    private MatchesRepository repository;

    @GetMapping("/matches")
    private List<Matches> allMatches(){
        return repository.findAll();
    }

    @PostMapping("/matches")
    private Matches createMatches(@RequestBody Matches matches){
        return repository.save(matches);
    }

    @PutMapping("/matches/{id}")
    private Matches updateMatches(@PathVariable long id, @RequestBody Matches matches){
        return repository.save(matches);
    }

    @DeleteMapping("matches/{id}")
    public void deleteMatches(@PathVariable("id") long id) {

        repository.deleteById(id);
    }
}
