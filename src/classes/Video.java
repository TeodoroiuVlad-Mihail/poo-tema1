package classes;

import java.util.ArrayList;

public class Video {
    private String name;
    private int year;
    private ArrayList<String> genre = new ArrayList<>();
    private double totalRatings;
    private int duration;
    private int favorite = 0;
    private int views = 0;
    private ArrayList<User> users = new ArrayList<User>(); //maybe use this, idk

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }
    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public double getTotalRatings() {
        return totalRatings;
    }
    public void setTotalRatings(double totalRatings) {
        this.totalRatings = totalRatings;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void addFavorite() {
        this.favorite++;
    }

    public void addViews() {
        this.views++;
    }

    public int getFavorite() {
        return favorite;
    }

    public int getViews() {
        return views;
    }

    public Video(String name) {
        this.name = name;
    }

    public Video(String name, int year, ArrayList<String> genre) {
        this.name = name;
        this.year = year;
        this.genre = genre;
    }
}