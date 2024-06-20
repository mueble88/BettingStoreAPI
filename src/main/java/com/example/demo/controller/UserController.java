package com.example.demo.controller;

import com.example.demo.dto.APIResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Team;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository repository;

    private PasswordEncoder passwordEncoder;

    /*
    @GetMapping("/users")
    public List<User> allUser(){
        return repository.findAll();
    }*/

    @GetMapping("/users")
    private APIResponseDto<Page<User>> getUsers(@RequestParam(defaultValue = "0") int offset,
                                                @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "name") String field,
                                                @RequestParam(defaultValue = "asc") String sort){

        Page<User> userWithPagination = findUserWithPaginationAndSorting(offset, pageSize, field, sort);
        return new APIResponseDto<>(userWithPagination.getSize(), userWithPagination);
    }

    public Page<User> findUserWithPaginationAndSorting(int offset, int pageSize, String field, String sort){
        Page<User> users;
        String desc = "desc";
        if(sort.equals(desc)){
            users = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC, field)));
            return users;
        }
        users = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)));
        return users;
    }

    @GetMapping(value = "/user/{id}")
    public UserDto getUser(@PathVariable long id){
        List<User> user = repository.findById(id);
        UserDto userDto = new UserDto();
        if(user != null){
            User oneUser = user.get(0);
            UserDto dto = userDto.fromUser(oneUser);
            return dto;
        }
        return null;
    }

    @GetMapping(value = "/user/email/{email}")
    public UserDto getUserForEmail(@PathVariable String email){
        String email1 = email;
        List<User> user = repository.findByEmail(email1);
        UserDto userDto = new UserDto();
        if(user != null){
            User oneUser = user.get(0);
            UserDto dto = userDto.fromUser(oneUser);
            return dto;
        }
        return null;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        /* Create password encryption  */
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String passwordEncrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);

        /* user creation timestamp */
        long createDate = System.currentTimeMillis()/1000;
        user.setCreatedAt(createDate);
        long modificationDate = System.currentTimeMillis()/1000;
        user.setLastUpdatedAt(modificationDate);

        return repository.save(user);
    }

    @PutMapping("/user/{id}")
    public Object update(@PathVariable long id , @RequestBody User user) {

        User oneUser = new User();
        oneUser.setId(id);
        long idUser = oneUser.getId();
        oneUser.setName(user.getName());
        String name = oneUser.getName();
        oneUser.setLastName(user.getLastName());
        String lastName = oneUser.getLastName();
        oneUser.setEmail(user.getEmail());
        String email = oneUser.getEmail();
        oneUser.setPhone(user.getPhone());
        String phone = oneUser.getPhone();
        oneUser.setDateOfBirth(user.getDateOfBirth());
        Date birth = oneUser.getDateOfBirth();
        oneUser.setCreatedAt(user.getCreatedAt());
        long createDate = oneUser.getCreatedAt();
        oneUser.setLastUpdatedAt(user.getLastUpdatedAt());
        long lastModificationDate = System.currentTimeMillis()/1000;
        user.setLastUpdatedAt(lastModificationDate);
        long modificationDate = user.getLastUpdatedAt();
        oneUser.setLastUpdatedAt(user.getLastUpdatedAt());

        int result = repository.updatePublicFields(idUser,name,lastName,email, phone, birth, createDate, modificationDate );
        if(result == 1){
            return oneUser;
        }
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        
        repository.deleteById(id);
    }

}


