package com.example.demo.service;

import com.example.demo.dto.PictureDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Picture;
import com.example.demo.model.Team;
import com.example.demo.model.User;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    public List<Team> findTeamWithSorting(String field){
        return teamRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Team> findTeamWithPagination(int offset, int pageSize){
        Page<Team> teams = teamRepository.findAll(PageRequest.of(offset, pageSize));
        return teams;
    }

    public Page<Team> findTeamWithPaginationAndSorting(int offset, int pageSize, String field, String sort){
        Page<Team> teams;
        String desc = "desc";
        if(sort.equals(desc)){
            teams = teamRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC, field)));
            return teams;
        }
            teams = teamRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)));
            return teams;
    }

    public Team getTeam(long id){
        Team team = teamRepository.findById(id).get(0);
        return team;
    }

    public boolean getName(String name){
        boolean nameFound = false;
        Team team = teamRepository.findByName(name).get(0);
        if (team != null){
            return nameFound = true;
        }
        return nameFound;
    }


    public Object save(Team team){
        return teamRepository.save(team);
    }

    @Transactional
    public int update(long id, String name,String dt){
        return teamRepository.updateTeamFields(id, name, dt);
    }

    public void deleteTeamById(long id){
        teamRepository.deleteById(id);
    }

}
