package com.example.w4118project.pojo;

public class Foto {

    private int rating;
    private String fotoURL;

    public Foto(int rating, String fotoURL) {
        this.rating = rating;
        this.fotoURL = fotoURL;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFotoURL() {
        return fotoURL;
    }

    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }
}
