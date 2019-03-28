package com.alpdurmaz.logic.movie;

public class Movie {
    private int movieID;
    private String title;
    private String mainActor;
    private int releasedYear;
    private String genre;
    private int price;

    public Movie(int movieID, String title) {
        this.movieID = movieID;
        this.title = title;
    }

    public Movie() {
    }

    public Movie(int movieID, String title, String mainActor, int releasedYear, String genre, int price) {
        this.movieID = movieID;
        this.title = title;
        this.mainActor = mainActor;
        this.releasedYear = releasedYear;
        this.genre = genre;
        this.price = price;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(int releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + movieID +
                ", title='" + title + '\'' +
                ", mainActor='" + mainActor + '\'' +
                ", releasedYear=" + releasedYear +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                '}';
    }
}
