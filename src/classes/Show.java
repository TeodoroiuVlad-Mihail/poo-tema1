package classes;

import java.util.ArrayList;

public class Show {
    private String name;
    private int year;
    private ArrayList<String> genre = new ArrayList<>();
    private double totalRatings;
    private int duration;
    private int favorite = 0;
    private int views = 0;
    private ArrayList<User> users = new ArrayList<User>(); //maybe use this, idk
    /**
     * sets name
     */
    public String getName() {
        return name;
    }
    /**
     * sets name
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * gets year
     */
    public int getYear() {
        return year;
    }
    /**
     * sets year
     */
    public void setYear(final int year) {
        this.year = year;
    }
    /**
     * gets genres
     */
    public ArrayList<String> getGenre() {
        return genre;
    }
    /**
     * sets genres
     */
    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }
    /**
     * gets ands sets total ratings
     */
    public double getTotalRatings() {
        return totalRatings;
    }
    /**
     * sets total ratings
     */
    public void setTotalRatings(final double totalRatings) {
        this.totalRatings = totalRatings;
    }
    /**
     * gets ands sets duration
     */
    public int getDuration() {
        return duration;
    }
    /**
     * sets duration
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }
    /**
     * gets ands sets views
     */
    public void setViews(final int views) {
        this.views = views;
    }
    /**
     * sets views
     */
    public int getViews() {
        return views;
    }
    /**
     * add increment views for this show
     */
    public void addViews() {
        this.views++;
    }
    /**
     * gets ands sets favorite rating
     */
    public void setFavorite(final int favorite) {
        this.favorite = favorite;
    }
    /**
     * sets favorite rating, used by me mainly to recalculate it
     */
    public int getFavorite() {
        return favorite;
    }
    /**
     * add increment favorite for this show
     */
    public void addFavorite() {
        this.favorite++;
    }
    public Show(final String name) {
        this.name = name;
    }

    public Show(final String name, final int year, final ArrayList<String> genre) {
        this.name = name;
        this.year = year;
        this.genre = genre;
    }
}
