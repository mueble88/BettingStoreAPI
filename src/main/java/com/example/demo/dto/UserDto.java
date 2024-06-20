package com.example.demo.dto;

import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDto implements Serializable {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private  String phone;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    private String password;

    private long createdAt;

    private long lastUpdatedAt;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {

        this.dateOfBirth = dateOfBirth;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public long getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(long lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }


    public UserDto fromUser(User user){
        UserDto userDto= new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setLastUpdatedAt(user.getLastUpdatedAt());

        return userDto;
    }


}
