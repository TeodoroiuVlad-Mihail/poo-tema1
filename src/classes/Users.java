package classes;

import fileio.UserInputData;

import java.util.HashMap;
import java.util.List;

public class Users {
    HashMap<String, User> users = new HashMap<>();

    public Users(final List<UserInputData> list) {
        for (UserInputData i : list) {
            User user = new User(
                    i.getUsername(),
                    i.getSubscriptionType(),
                    i.getHistory(),
                    i.getFavoriteMovies()
            );
            users.put(i.getUsername(), user);
        }
    }
    /**
     * Favourites show
     */
    public String addFav(final String name, final String title, final Movies m, final Serials s) {
        return users.get(name).addFavorite(title, m, s);
    }
    /**
     * Adds rating specifically to movie
     */
    public String addRatingFilm(final String name, final String title, final Movies m,
                                final double r) {
        return users.get(name).rateMovie(title, r, m);
    }
    /**
     * Adds rating specifically to serials
     */
    public String addRatingSerial(final String name, final String title, final int nrSez,
                                  final double r, final Serials s) {
        return users.get(name).rateSerial(title, r, nrSez, s);
    }
    /**
     * Adds views
     */
    public String addViews(final String name, final String title, final Movies m,
                           final Serials s) {
        return users.get(name).viewing(title, m, s);
    }
}
