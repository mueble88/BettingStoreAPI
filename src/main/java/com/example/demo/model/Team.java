package com.example.demo.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

//modelo de equipos de futbol de colombia.

@Entity
@Table(name="team")
@EntityListeners(AuditingEntityListener.class)
public class Team implements Comparable<Team>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @Column(nullable = false, length = 40)
    private  String name;

    @Column(length = 30)
    private String dt;

    @Column(name="href_facebook")
    private String hrefFacebook;

    @Column(name="href_instagram")
    private String hrefInstagram;

    @Column(name="href_google")
    private String hrefGoogle;

    @JoinColumn(name = "picture_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Picture shield;

    public Team(){}

    public Team(long id, String name, String dt, Picture shield) {
        this.id = id;
        this.name = name;
        this.dt = dt;
        this.shield = shield;
    }

    public Team(long id, String name, String dt, String hrefFacebook, String hrefInstagram, String hrefGoogle, Picture shield) {
        this.id = id;
        this.name = name;
        this.dt = dt;
        this.hrefFacebook = hrefFacebook;
        this.hrefInstagram = hrefInstagram;
        this.hrefGoogle = hrefGoogle;
        this.shield = shield;
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

    public String getDT() {
        return dt;
    }

    public void setDT(String dt) {
        this.dt = dt;
    }

    public String getHrefFacebook() {
        return hrefFacebook;
    }

    public void setHrefFacebook(String hrefFacebook) {
        this.hrefFacebook = hrefFacebook;
    }

    public String getHrefInstagram() {
        return hrefInstagram;
    }

    public void setHrefInstagram(String hrefInstagram) {
        this.hrefInstagram = hrefInstagram;
    }

    public String getHrefGoogle() {
        return hrefGoogle;
    }

    public void setHrefGoogle(String hrefGoogle) {
        this.hrefGoogle = hrefGoogle;
    }

    public Picture getShield() {
        return shield;
    }

    public void setShield(Picture shield) {
        this.shield = shield;
    }

    //    Método toString para mostrar los datos del objeto
    @Override
    public String toString() {
        return "Team [id="+id+" ,name="+name+" , dt="+dt+", hrefFacebook="+hrefFacebook+", hrefInstagram="+hrefInstagram+", hrefGoogle="+hrefGoogle+", shield="+shield+"]";
    }

    @Override
    public int compareTo(Team team) {
        return 0;
    }
}
