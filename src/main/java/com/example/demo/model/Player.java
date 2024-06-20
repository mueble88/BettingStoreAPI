package com.example.demo.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name="player")
@EntityListeners(AuditingEntityListener.class)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(name="last_name", length = 30)
    private String lastName;

    @Column(length = 2)
    private String age;

    @Column(length = 5)
    private String height;

    @Column(length = 5)
    private String weight;

    @OneToOne
    @JoinColumn(name ="team_id", unique = true)
    private Team team;


    public Player(){}

    public Player(long id, String name, String lastName, String age, String height, String weight, Team team) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.team = team;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
