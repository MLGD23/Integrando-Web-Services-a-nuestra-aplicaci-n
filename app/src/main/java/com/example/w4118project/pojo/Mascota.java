package com.example.w4118project.pojo;

public class Mascota {

    private  String id;
    private String nombre;
    private int rating;
    private String urlFoto;

    public Mascota() {
    }

    public Mascota(String nombre, int rating, String urlFoto) {
        this.nombre = nombre;
        this.rating = rating;
        this.urlFoto = urlFoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
