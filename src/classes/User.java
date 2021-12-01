package classes;


import java.util.ArrayList;
import java.util.Map;

public class User {
    private String username;
    private String subscription;
    private final Map<String, Integer> history;
    private final ArrayList<String> favorite;
    private final ArrayList<String> rated = new ArrayList<>();
    private int actions = 0;

    public User(String username, String subscription,Map<String, Integer> history ,ArrayList<String> fav_films) {
        this.username = username;
        this.subscription = subscription;
        this.history = history;
        favorite = fav_films;

    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getRated() {
        return rated;
    }

    public String addFavorite(String v, Movies movies, Serials serials) {
        if(history.containsKey(v) && !favorite.contains(v)) {
            favorite.add(v);
            if(movies.isMovie(v))
                movies.addFav(v);
            else
                serials.addFav(v);
            return "success -> " + v + " was added as favourite";
        } else if(favorite.contains(v)) {
            return "error -> " + v + " is already in favourite list";
        } else {
            return "error -> " + v + " is not seen";
        }
    }

    public String viewing(String v, Movies movies, Serials serials) {
        if(history.containsKey(v)) {
            history.replace(v, history.get(v) + 1);
            if(movies.isMovie(v)) {
                movies.addViews(v);
            }
            else
                serials.addViews(v);
            return  "success -> " + v + " was viewed with total views of " + history.get(v);
        }
        else {
            history.put(v, 1);
            return  "success -> " + v + " was viewed with total views of " + 1;
        }
    }

    public int getActions() {
        return actions;
    }

    public String rateMovie(String m, double r, Movies movies) {
        if(history.containsKey(m) && !rated.contains(m)) {
            this.rated.add(m);
            movies.addRating(r, m);
            this.actions++;
            return "success -> " + m + " was rated with " + r + " by " + username;
        } else if(!history.containsKey(m)){
            return "error -> " + m + " is not seen";
        } else {
            return "error -> " + m + " has been already rated";
        }

    }
    public String rateSerial(String s, double r, int pos, Serials serials) {
        if (history.containsKey(s) && !serials.wasRated(username, s, pos)) {
            this.actions++;
            serials.addRating(r,s,pos, username);
            return "success -> " + s + " was rated with " + r + " by " + username;
        } else if(!history.containsKey(s)){
            return "error -> " + s + " is not seen";
        } else {
            return "error -> " + s + " has been already rated";
        }
    }
}