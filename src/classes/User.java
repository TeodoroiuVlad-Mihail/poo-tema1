package classes;


import java.util.ArrayList;
import java.util.Map;

public class User {
    private final String username;
    private final String subscription;
    private final Map<String, Integer> history;
    private final ArrayList<String> favorite;
    private final ArrayList<String> rated;
    private int actions = 0;

    /**
     * gets username
     */
    public String getUsername() {
        return this.username;
    }
    /**
     * gets history of user, as in what they have viewed
     */
    public Map<String, Integer> getHistory() {
        return this.history;
    }
    /**
     * gets favorite shows
     */
    public ArrayList<String> getFavorite() {
        return this.favorite;
    }
    /**
     * gets what they have rated
     */
    public ArrayList<String> getRated() {
        return this.rated;
    }
    /**
     * gets how many actions they have done
     */
    public int getActions() {
        return this.actions;
    }
    public User(final String username, final String subscription,
                final Map<String, Integer> history, final ArrayList<String> favMovies) {
        this.username = username;
        this.subscription = subscription;
        this.history = history;
        this.favorite = favMovies;
        this.rated = new ArrayList<>();
    }
    /**
     * adds favorite to either movie or serials
     */
    public String addFavorite(final String v, final Movies movies, final Serials serials) {
        if (this.history.containsKey(v) && !this.favorite.contains(v)) {
            this.favorite.add(v);
            if (movies.isMovie(v)) {
                movies.addFav(v);
            } else {
                serials.addFav(v);
            }
            return "success -> " + v + " was added as favourite";
        } else if (this.favorite.contains(v)) {
            return "error -> " + v + " is already in favourite list";
        } else {
            return "error -> " + v + " is not seen";
        }
    }
    /**
     * Views show, movie or serials
     */
    public String viewing(final String v, final Movies movies, final Serials serials) {
        if (this.history.containsKey(v)) {
            this.history.replace(v, this.history.get(v) + 1);
            if (movies.isMovie(v)) {
                movies.addViews(v);
            } else {
                serials.addViews(v);
            }
            return  "success -> " + v + " was viewed with total views of " + this.history.get(v);
        } else {
            this.history.put(v, 1);
            return  "success -> " + v + " was viewed with total views of " + 1;
        }
    }
    /**
     * Specifically rates Movie
     */
    public String rateMovie(final String m, final double r, final Movies movies) {
        if (this.history.containsKey(m) && !this.rated.contains(m)) {
            this.rated.add(m);
            this.actions++;
            movies.addRating(r, m);
            return "success -> " + m + " was rated with " + r + " by " + this.username;
        } else if (!history.containsKey(m)) {
            return "error -> " + m + " is not seen";
        } else {
            return "error -> " + m + " has been already rated";
        }

    }
    /**
     * Specifically rates Serial
     */
    public String rateSerial(final String s, final double r, final int pos,
                             final Serials serials) {
        if (this.history.containsKey(s) && !serials.wasRated(this.username, s, pos)) {
            this.rated.add(s);
            this.actions++;
            serials.addRating(r, s, pos, this.username);
            return "success -> " + s + " was rated with " + r + " by " + this.username;
        } else if (!this.history.containsKey(s)) {
            return "error -> " + s + " is not seen";
        } else {
            return "error -> " + s + " has been already rated";
        }
    }
}
