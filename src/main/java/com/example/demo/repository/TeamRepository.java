package com.example.demo.repository;

import com.example.demo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByName(String name);

    List<Team> findById(long id);

    List<Team> findAll();

    @Modifying
    @Transactional
    @Query("UPDATE Team team SET team.name = :name, team.dt = :dt WHERE team.id = :id")
    int updateTeamFields( @Param("id") long id,
                          @Param("name")String name,
                          @Param("dt")String dt);


}
