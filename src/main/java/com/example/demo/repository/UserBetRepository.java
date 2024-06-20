package com.example.demo.repository;


import com.example.demo.model.UserBet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBetRepository extends JpaRepository<UserBet, Long> {
    List<UserBet>findById(@Param("id") long id);
}
