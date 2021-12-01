package classes;

import java.util.ArrayList;
import java.util.List;

public class Movie extends Video{
    private int duration;
    private List<Double> ratings = new ArrayList<>();

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
        super.setDuration(duration);
    }
    public List<Double> getRatings() {
        return ratings;
    }
    public void setRatings(List<Double> ratings) {
        this.ratings = ratings;
    }
    public void addRating(double rate) {
        ratings.add(rate);
    }

    @Override
    public void setTotalRatings(double totalRatings) {
        super.setTotalRatings(totalRatings);
    }
    public double getTotal_Ratings() {
        return super.getTotalRatings();
    }

    Movie(String name, int year, ArrayList<String> genre, int duration) {
        super(name, year, genre);
        this.duration = duration;
    }
}