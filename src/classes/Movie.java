package classes;

import java.util.ArrayList;
import java.util.List;

public class Movie extends Video {
    private int duration;
    private List<Double> ratings = new ArrayList<>();

    public int getDuration() {
        return duration;
    }
    /**
     * sets the duration of the video
     */
    public void setDuration(final int duration) {
        this.duration = duration;
        super.setDuration(duration);
    }

    @Override
    public double getTotalRatings() {
        return super.getTotalRatings();
    }
    /**
     * sets the total ratings of the movie, [MissingJavadocMethod] is so !fun!
     */
    public void setTotalRatings(final double totalRatings) {
        super.setTotalRatings(totalRatings);
    }

    public List<Double> getRatings() {
        return ratings;
    }
    public void setRatings(final List<Double> ratings) {
        this.ratings = ratings;
    }
    /**
     * Increments the number of ratings of the movie.
     */
    public void addRating(final double rating) {
        ratings.add(rating);
    }

    Movie(final String name, final int year, final ArrayList<String> genre, final int duration) {
        super(name, year, genre);
        this.duration = duration;
    }
}
