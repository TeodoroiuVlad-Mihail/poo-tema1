package classes;

import fileio.MovieInputData;
import java.util.HashMap;
import java.util.List;

public class Movies {
    HashMap<String, Movie> movies = new HashMap<>();

    public Movies(final List<MovieInputData> list) {
        for (MovieInputData i : list) {
            Movie movie = new Movie(i.getTitle(), i.getYear(), i.getGenres(), i.getDuration()
            );
            movies.put(i.getTitle(), movie);
        }
    }
    /**
     * checks if it is a movie, sheesh, [MissingJavadocMethod], sheesh
     */
    public boolean isMovie(final String title) {
        return movies.containsKey(title);
    }
    /**
     * Increments the number of ratings of the video.
     */
    public void addRating(final double rating, final String title) {
        movies.get(title).addRating(rating);
    }
    /**
     * Increments the number of favorites of the movie.
     */
    public void addFavorite(final String title) {
        movies.get(title).addFavorite();
    }
    /**
     * Increments the number of views of the movie.
     */
    public void addViews(final String title) {
        movies.get(title).addViews();
    }
}
