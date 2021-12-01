package classes;

import fileio.UserInputData;

import java.util.HashMap;
import java.util.List;

public class Users {
    HashMap<String, User> users = new HashMap<>();

    public Users(List<UserInputData> list) {
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

    public String addFav(String name, String title, Movies db1, Serials db2) {
        return users.get(name).addFavorite(title, db1,db2);
    }

    public String addRatingFilm(String name, String title, Movies db, double r) {
        return users.get(name).rateMovie(title, r, db);
    }

    public String addRatingSerial(String name, String title, int nr_sez, double r, Serials db) {
        return users.get(name).rateSerial(title, r, nr_sez, db);
    }

    public String addViews(String name, String title, Movies db1, Serials db2) {
        return users.get(name).viewing(title, db1,db2);
    }
}