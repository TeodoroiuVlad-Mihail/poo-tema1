package classes;

import java.util.ArrayList;

public class Video {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genre = new ArrayList<>();
    //private ArrayList<Username> users = new ArrayList<Username>(); //who rated it
    private double totalRatings;
    private int favorite = 0;
    private int views = 0;

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }
    public void setYear(final int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }
    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }

    public double getTotalRatings() {
        return totalRatings;
    }
    public void setTotalRatings(final double totalRatings) {
        this.totalRatings = totalRatings;
    }

    public Video(final String name) {
        this.name = name;
    }

    public Video(final String name, final int year, final ArrayList<String> genre) {
        this.name = name;
        this.year = year;
        this.genre = genre;
    }

    public int getFavorite() {
        return favorite;
    }
    /**
     * Increments the number of favorites of the video.
     */
    public void addFavorite() {
        ++favorite;
    }

    public int getViews() {
        return views;
    }
    /**
     * Increments the number of views of the video.
     */
    public void addViews() { //increments the views
        ++views;
    }
}
