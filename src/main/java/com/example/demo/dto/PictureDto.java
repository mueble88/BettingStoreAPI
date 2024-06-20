package com.example.demo.dto;

public class PictureDto {

    private long id;
    private String image;
    private String name;
    private String type;

    public PictureDto(){}

    public PictureDto(long id, String image, String name, String type) {
        this.id = id;
        this.image = image;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
