package classes;

import java.util.ArrayList;
import java.util.List;

public class Movie extends Show {
    private int duration;
    private List<Double> ratings = new ArrayList<>();
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
        super.setDuration(duration);
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
    public void addRating(final double rate) {
        ratings.add(rate);
    }

    /**
     * gets to total ratings
     */
    @Override
    public double getTotalRatings() {
        return super.getTotalRatings();
    }
    /**
     * sets to total ratings
     */
    @Override
    public void setTotalRatings(final double totalRatings) {
        super.setTotalRatings(totalRatings);
    }

    Movie(final String name, final int year, final ArrayList<String> genre, final int duration) {
        super(name, year, genre);
        this.duration = duration;
    }
}
