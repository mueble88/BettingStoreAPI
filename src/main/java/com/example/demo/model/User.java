package com.example.demo.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para saber que el ID se genera automaticamente...
    private long id;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(name="last_name", nullable = false, length = 30)
    private String lastName;
    private String email;
    @Column(length = 10)
    private String phone;
    @Column(name="date_of_birth",nullable = false)
    private Date dateOfBirth;
    @Column(name="password", nullable = false)
    private String password;

    @Column(name="created_At")
    private long createdAt;

    @Column(name="last_updated_At")
    private long lastUpdatedAt;


    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String lastName, String email, String phone, Date dateOfBirth, String password, long createdAt, long lastUpdatedAt) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public User(long id, String name, String lastName, String email, String phone, Date dateOfBirth, String password, long createdAt, long lastUpdatedAt) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

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

        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)  {
      this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt(long createdAt){
        this.createdAt = createdAt;
    }

    public long getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(long lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Override
    public String toString(){
        return "User [id=" + id + ",name=" + name + ",last_name=" + lastName + ",email=" + email +",phone=" + phone +",date_of_birth=" + dateOfBirth +",password=" + password+",created_At="+createdAt+",last_updated_At="+lastUpdatedAt+"]";
    }


}
