package com.alpdurmaz.presentation.restcontroller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieDetailAPI {
    @JsonProperty("Title")
    private String Title;

    @JsonProperty("Runtime")
    private String Runtime;

    @JsonProperty("Director")
    private String Director;

    @JsonProperty("Writer")
    private String Writer;

    @JsonProperty("Plot")
    private String Plot;

    @JsonProperty("Awards")
    private String Awards;

    @JsonProperty("imdbRating")
    private String imdbRating;

    @JsonProperty("imdbVotes")
    private String imdbVotes;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    @Override
    public String toString() {
        return "MovieDetailAPI{" +
                "Title='" + Title + '\'' +
                ", \nRuntime='" + Runtime + '\'' +
                ", \nDirector='" + Director + '\'' +
                ", \nWriter='" + Writer + '\'' +
                ", \nPlot='" + Plot + '\'' +
                ", \nAwards='" + Awards + '\'' +
                ", \nimdbRating='" + imdbRating + '\'' +
                ", \nimdbVotes='" + imdbVotes + '\'' +
                '}';
    }
}
