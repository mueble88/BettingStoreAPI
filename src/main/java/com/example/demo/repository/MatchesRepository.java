package com.example.demo.repository;


import com.example.demo.model.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Long> {
    List<Matches>findByPlayed(boolean played);

}
