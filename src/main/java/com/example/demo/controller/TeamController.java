package com.example.demo.controller;


import com.example.demo.dto.APIResponseDto;
import com.example.demo.dto.TeamDto;
import com.example.demo.model.Picture;
import com.example.demo.model.Team;
import com.example.demo.model.User;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.service.PictureStorageService;


import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.values;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TeamController  {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PictureStorageService pictureStorageService;

    /*
    @GetMapping("/teams")
    private APIResponseDto<List<Team>> getTeamsWithSort(){
        List<Team> allTeams = teamService.getAllTeams();
        return new APIResponseDto<>(allTeams.size(), allTeams);
    }

    @GetMapping("/teams/{field}")
    private APIResponseDto<List<Team>> getTeamsWithSort(@PathVariable String field){
        List<Team> allTeams = teamService.findTeamWithSorting(field);
        return new APIResponseDto<>(allTeams.size(), allTeams);
    }

    @GetMapping("/teams/paginationAndSort/{offset}/{pageSize}/{field}")
    private APIResponseDto<Page<Team>> getTeamsWithPaginationAndSort(@PathVariable int offset,@PathVariable int pageSize, @PathVariable String field){
        Page<Team> teamWithPagination = teamService.findTeamWithPaginationAndSorting(offset, pageSize, field);
        return new APIResponseDto<>(teamWithPagination.getSize(), teamWithPagination);
    }


    @GetMapping("/teams/paginationAndSort/{offset}/{pageSize}/{field}")
    private APIResponseDto<Page<Team>> getTeamsWithPaginationAndSort(@PathVariable int offset,
                                                                     @PathVariable int pageSize,
                                                                     @PathVariable String field){
        Page<Team> teamWithPagination = teamService.findTeamWithPaginationAndSorting(offset, pageSize, field);
        return new APIResponseDto<>(teamWithPagination.getSize(), teamWithPagination);
    }
*/

    @GetMapping("/teams")
    private APIResponseDto<Page<Team>> getTeams(@RequestParam(defaultValue = "0") int offset,
                                                @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "name") String field,
                                                @RequestParam(defaultValue = "asc") String sort){

        Page<Team> teamWithPagination = teamService.findTeamWithPaginationAndSorting(offset, pageSize, field, sort);
        return new APIResponseDto<>(teamWithPagination.getSize(), teamWithPagination);
    }

    @GetMapping(value = "team/{id}")
    private Team dataTeam(@PathVariable long id){
        return teamService.getTeam(id);
    }
/*
    @PostMapping("/team")
    private void createTeam(@RequestBody Team team){
        boolean teamFielded = teamService.getName(team.getName());
        System.out.println(teamFielded);
        /*if (teamFielded){
            teamService.save(team);
            return new ResponseEntity<Team>(team, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
*/

    @RequestMapping(path = "/team/{id}", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Object updateTeam(@PathVariable long id ,@RequestParam String name, @RequestParam String dt, @RequestPart MultipartFile image) throws IOException {
        System.out.println("image:"+image);
        System.out.println("name:"+name);
        System.out.println("dt:"+dt);
        int resultTeam = teamService.update(id,name, dt);
        Team oneTeam = teamService.getTeam(id);
        long idPicture = oneTeam.getShield().getId();
        String encoded = Base64Utils.encodeToString(image.getBytes());
        String type = image.getContentType();
        int resultPicture = pictureStorageService.updateImage(idPicture, encoded, type);
        if(!image.isEmpty()){
            if(resultTeam == 1 && resultPicture == 1){
                TeamDto teamDto = new TeamDto();
                teamDto.setId(idPicture);
                teamDto.setName(name);
                teamDto.setDt(dt);
                teamDto.setImage(encoded);
                return teamDto;
            }
        }
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/team/{id}")
    private void deleteTeam(@PathVariable("id") long id){
        teamService.deleteTeamById(id);
    }

    private List<Team> orderList(List<Team> teams){

//        List<Team> teamList = Arrays.asList(new Team());
        List<Team> teamList = new ArrayList<Team>(teams);
        System.out.println(teamList);
        Collections.sort(teamList);

        System.out.println(teamList);
        return teamList;
    }


}