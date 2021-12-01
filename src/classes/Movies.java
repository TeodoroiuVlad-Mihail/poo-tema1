package classes;

import fileio.MovieInputData;

import java.util.HashMap;
import java.util.List;

public class Movies {
    HashMap<String, Movie> movies = new HashMap<>();

    public Movies(List<MovieInputData> list) {
        for (MovieInputData i : list) {
            Movie new_film = new Movie(
                    i.getTitle(),
                    i.getYear(),
                    i.getGenres(),
                    i.getDuration()
            );
            movies.put(i.getTitle(), new_film);
        }
    }

    public void addRating(double r, String title) {
        movies.get(title).addRating(r);
    }

    public boolean isMovie(String title) {
        return movies.containsKey(title);
    }

    public void addViews(String title) {
        movies.get(title).addViews();
    }

    public void addFav(String title) {
        movies.get(title).addFavorite();
    }



}