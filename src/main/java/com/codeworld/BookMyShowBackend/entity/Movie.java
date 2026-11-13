package com.codeworld.BookMyShowBackend.entity;


import jakarta.persistence.*;

@Entity
@Table(name="movies",uniqueConstraints = @UniqueConstraint(name="uk_movie_tittle",columnNames = "tittle"))
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;




    private String title;

    private String language;

    private String genre;

    private Integer durationMinutes;

    private String certificate;

    private String description;

    private String PosterUrl;

    private String trailerUrl;

    private boolean active=true;

    public Movie()
    {

    }

    public Movie(String title, String language, String genre, Integer durationMinutes, String certificate, String description,String posterUrl, String trailerUrl, boolean active) {
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.certificate = certificate;
        this.description = description;
        this.PosterUrl = posterUrl;
        this.trailerUrl = trailerUrl;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return PosterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        PosterUrl = posterUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
