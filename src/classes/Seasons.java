package classes;

import java.util.ArrayList;
import java.util.List;

public class Seasons {
    private final int currentSeason;
    private int duration;
    private List<Double> ratings;
    ArrayList<String> users = new ArrayList<>();
    // which users rated the season

    /**
     * gets current season
     */
    public int getCurrentSeason() {
        return currentSeason;
    }
    /**
     * gets users
     */
    public ArrayList<String> getUsers() {
        return users;
    }
    /**
     * sets users
     */
    public void setUsers(final ArrayList<String> users) {
        this.users = users;
    }
    /**
     * gets duration
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
     * gets ratings
     */
    public List<Double> getRatings() {
        return ratings;
    }
    /**
     * sets ratings
     */
    public void setRatings(final List<Double> ratings) {
        this.ratings = ratings;
    }
    /**
     * adds to ratings
     */
    public void addRating(final double r) {
        ratings.add(r);
    }

    public Seasons(final int currentSeason, final int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.ratings = new ArrayList<>();
    }
    /**
     * toString
     */
    @Override
    public String toString() {
        return "Episode{"
                + "currentSeason="
                + currentSeason
                + ", duration="
                + duration
                + '}';
    }
}
