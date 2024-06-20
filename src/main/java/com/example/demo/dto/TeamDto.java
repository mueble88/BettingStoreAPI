package com.example.demo.dto;

import javax.persistence.Column;

public class TeamDto {
    private long id;
    private String name;
    private String dt;

    private String hrefFacebook;
    private String hrefInstagram;
    private String hrefGoogle;

    private String image;

    public TeamDto() {
    }

    public TeamDto(long id, String name, String dt, String hrefFacebook, String hrefInstagram, String hrefGoogle, String image) {
        this.id = id;
        this.name = name;
        this.dt = dt;
        this.hrefFacebook = hrefFacebook;
        this.hrefInstagram = hrefInstagram;
        this.hrefGoogle = hrefGoogle;
        this.image = image;
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

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


//    Método toString para mostrar los datos del objeto
    @Override
    public String toString() {
        return "TeamDto [id="+id+" ,name="+name+" , dt="+dt+", hrefFacebook="+hrefFacebook+", hrefInstagram="+hrefInstagram+", hrefGoogle="+hrefGoogle+", image="+image+"]";
    }
}
