package com.alpdurmaz.presentation.restcontroller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieAPI {
    @JsonProperty("Title")
    private String Title;

    @JsonProperty("Year")
    private String Year;

    @JsonProperty("Actors")
    private String Actors;

    @JsonProperty("Genre")
    private String Genre;

    public MovieAPI() {
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        this.Year = year;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        this.Actors = actors;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        this.Genre = genre;
    }

    @Override
    public String toString() {
        return "MovieAPI{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", Actors='" + Actors + '\'' +
                ", Genre='" + Genre + '\'' +
                '}';
    }

    public String getTitle() {
        return Title;
    }
}

