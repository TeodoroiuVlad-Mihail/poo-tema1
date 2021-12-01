package classes;

import fileio.MovieInputData;

import java.util.HashMap;
import java.util.List;

public class Movies {
    HashMap<String, Movie> movies = new HashMap<>();

    public Movies(final List<MovieInputData> list) {
        for (MovieInputData i : list) {
            Movie movie = new Movie(
                    i.getTitle(),
                    i.getYear(),
                    i.getGenres(),
                    i.getDuration()
            );
            movies.put(i.getTitle(), movie);
        }
    }
    /**
     * adds to total ratings
     */
    public void addRating(final double r, final String title) {
        movies.get(title).addRating(r);
    }
    /**
     * checks if it is a movie or not
     */
    public boolean isMovie(final String title) {
        return movies.containsKey(title);
    }
    /**
     * adds to views
     */
    public void addViews(final String title) {
        movies.get(title).addViews();
    }
    /**
     * adds to favorite counter
     */
    public void addFav(final String title) {
        movies.get(title).addFavorite();
    }

}
