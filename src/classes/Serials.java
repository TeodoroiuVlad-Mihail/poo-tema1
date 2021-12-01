package classes;

import fileio.SerialInputData;

import java.util.HashMap;
import java.util.List;

public class Serials {
    HashMap<String, Serial> serial = new HashMap<>();
    public Serials(List<SerialInputData> list) {
        for(SerialInputData i : list) {
            Serial new_serial = new Serial(
                    i.getTitle(),
                    i.getYear(),
                    i.getGenres(),
                    i.getNumberSeason(),
                    i.getSeasons()
            );
            serial.put(i.getTitle(), new_serial);
        }
    }
    public void addRating(double r, String title, int nr_s, String user_name) {
        serial.get(title).getSeasons().get(nr_s - 1).addRating(r);
        serial.get(title).getSeasons().get(nr_s - 1).getUsers().add(user_name);
    }

    public boolean wasRated(String usr, String title, int nr) {
        return serial.get(title).getSeasons().get(nr - 1).getUsers().contains(usr);
    }

    public void addViews(String title){
        serial.get(title).addViews();
    }
    public void addFav(String title) {
        serial.get(title).addFavorite();
    }
}