package com.example.models;

public class Komuna {
    private int id;
    private String emri;
    private String kodiPostar;

    public Komuna(int id, String emri, String kodiPostar) {
        this.id = id;
        this.emri = emri;
        this.kodiPostar = kodiPostar;
    }

    public int getId() { return id; }
    public String getEmri() { return emri; }
    public String getKodiPostar() { return kodiPostar; }
}
