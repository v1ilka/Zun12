package com.example.zun.domain;

public class Photo {
    private int   id;
    private String picture;

    public int getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public Photo(int id, String picture) {
        this.id = id;
        this.picture = picture;
    }
}
