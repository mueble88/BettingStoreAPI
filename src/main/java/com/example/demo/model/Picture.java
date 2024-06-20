package com.example.demo.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Base64Utils;

import javax.persistence.*;
import java.sql.Blob;


@Entity
@Table(name="picture")
@EntityListeners(AuditingEntityListener.class)
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private String image;

    private String type;

    public Picture(){}

    public Picture(long id, String image, String type) {
        this.id = id;
        this.image = image;
        this.type = type;

    }

    public Picture(String image, String type) {
        this.image = image;

        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
