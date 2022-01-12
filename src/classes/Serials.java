package classes;

import fileio.SerialInputData;

import java.util.HashMap;
import java.util.List;

public class Serials {
    HashMap<String, Serial> serials = new HashMap<>();
    public Serials(final List<SerialInputData> list) {
        for (SerialInputData i : list) {
            Serial serial = new Serial(
                    i.getTitle(),
                    i.getYear(),
                    i.getGenres(),
                    i.getNumberSeason(),
                    i.getSeasons()
            );
            serials.put(i.getTitle(), serial);
        }
    }
    /**
     * adds to ratings
     */
    public void addRating(final double r, final String title, final int nrS,
                          final String userName) {
        serials.get(title).getSeasons().get(nrS - 1).addRating(r);
        serials.get(title).getSeasons().get(nrS - 1).getUsers().add(userName);
    }
    /**
     * checks if serials was rated
     */
    public boolean wasRated(final String usr, final String title, final int nr) {
        return serials.get(title).getSeasons().get(nr - 1).getUsers().contains(usr);
    }
    /**
     * adds to views
     */
    public void addViews(final String title) {
        serials.get(title).addViews();
    }
    /**
     * adds to favorite counter
     */
    public void addFav(final String title) {
        serials.get(title).addFavorite();
    }
}
