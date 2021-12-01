package classes;

import actor.ActorsAwards;
import fileio.ActionInputData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public
class Query { // also reccomendations
    Actors actors;
    Movies movies;
    Serials serials;
    Users users;

    public
    Query(final Actors a, final Movies m, final Serials s, final Users u) {
        actors = a;
        movies = m;
        serials = s;
        users = u;
    }

    /**
     * filters users based on how many ratings they have given
     */
    public
    String nrOfRatings(final ActionInputData action) {
        ArrayList<User> aux = new ArrayList<>();
        int i;
        aux.addAll(users.users.values());
        if (action.getSortType().equals("asc")) {
            for (int j = 0; j < aux.size() - 1; j++) {
                for (i = 0; i < aux.size() - 1; i++) {
                    if (aux.get(i).getActions() > aux.get(i + 1).getActions()
                            || (aux.get(i).getActions() == aux.get(i + 1).getActions()
                            && aux.get(i).getUsername().compareTo(
                                            aux.get(i + 1).getUsername()) > 0)) {
                        Collections.swap(aux, i, i + 1);
                    }
                }
            }
        } else {
            for (int j = 0; j < aux.size() - 1; j++) {
                for (i = 0; i < aux.size() - 1; i++) {
                    if (aux.get(i).getActions() < aux.get(i + 1).getActions()
                            || (aux.get(i).getActions() == aux.get(i + 1).getActions()
                            && aux.get(i).getUsername().compareTo(
                                            aux.get(i + 1).getUsername()) < 0)) {
                        Collections.swap(aux, i, i + 1);
                    }
                }
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for (i = 0; i < aux.size() && result.size() < action.getNumber(); i++) {
            if (aux.get(i).getRated().size() > 0) {
                result.add(aux.get(i).getUsername());
            }
        }
        return "Query result: " + result;
    }
    /**
     * filters actors based on their average rating
     */
    public
    String average(final ActionInputData action) {
        ArrayList<Actor> aux = new ArrayList<>();
        int i, j;
        for (i = 0; i < actors.actors.size(); i++) {
            Actor temp = actors.actors.get(i);
            double sum = 0;
            double nr = 0;
            int universalOk = 0; // for actors that are partially reviews, they are
            // added, but with 0
            for (String f : temp.getFilmography()) {
                if (movies.isMovie(f)) {
                    for (Movie m : movies.movies.values()) {
                        if (m.getName().equals(f)) {
                            if (m.getRatings().size() > 0) {
                                for (double k : m.getRatings()) {
                                    sum = sum + k;
                                    nr = nr + 1;
                                }
                            }
                        }
                    }
                } else {
                    for (Serial s : serials.serials.values()) {
                        if (s.getName().equals(f)) {
                            int ok = 1; // to see if the series is fully reviewed at least
                            // once
                            double sumSeasons = 0;
                            double nrSeasons = 0;
                            for (Seasons ss : s.getSeasons()) {
                                if (ss.getRatings().size() == 0) {
                                    ok = 0;
                                } else {
                                    universalOk = 1;
                                }
                                for (double k : ss.getRatings()) {
                                    sumSeasons = sumSeasons + k;
                                    nrSeasons = nrSeasons + 1;
                                }
                            }
                            if (ok == 1) {
                                sum = sum + sumSeasons;
                                nr = nr + nrSeasons;
                            }
                        }
                    }
                }
            }
            if (nr > 0) {
                temp.setAverage(sum / nr);
                aux.add(temp);
            } else if (universalOk == 1) {
                temp.setAverage(0);
                aux.add(temp);
            }
        }
        for (i = 0; i < aux.size() - 1; i++) {
            for (j = 0; j < aux.size() - 1; j++) {
                Actor aux1 = aux.get(j);
                Actor aux2 = aux.get(j + 1);
                double sum1 = aux1.getAverage();
                double sum2 = aux2.getAverage();

                if (action.getSortType().equals("asc")) {
                    if (sum1 > sum2) {
                        Collections.swap(aux, j, j + 1);
                    } else if (sum1 == sum2) {
                        if (aux1.getName().compareTo(aux2.getName()) > 0) {
                            Collections.swap(aux, j, j + 1);
                        }
                    }
                } else {
                    if (sum1 < sum2) {
                        Collections.swap(aux, j, j + 1);
                    } else if (sum1 == sum2) {
                        if (aux1.getName().compareTo(aux2.getName()) < 0) {
                            Collections.swap(aux, j, j + 1);
                        }
                    }
                }
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for (i = 0; i < aux.size() && result.size() < action.getNumber(); i++) {
            result.add(aux.get(i).getName());
        }
        return "Query result: " + result;
    }
    /**
     * filters actors based on their awards
     */
    public
    String awards(final ActionInputData action) {
        ArrayList<Actor> aux = new ArrayList<>();
        int i, j;
        for (i = 0; i < actors.actors.size(); i++) {
            boolean ok = true;
            Actor temp = actors.actors.get(i);
            for (j = 0; j < action.getFilters().get(3).size(); j++) {
                if (!temp.getAwards().containsKey(
                        ActorsAwards.valueOf((action.getFilters().get(3).get(j))))) {
                    ok = false;
                }
            }
            if (ok) {
                aux.add(temp);
            }
        }
        for (i = 0; i < aux.size() - 1; i++) {
            for (j = 0; j < aux.size() - 1; j++) {
                Actor aux1 = aux.get(j);
                Actor aux2 = aux.get(j + 1);
                int sum1 = 0;
                int sum2 = 0;
                /*for (int k = 0; k < action.getFilters().get(3).size(); k++) {
                    sum1 = sum1 +
                aux1.getAwards().get(ActorsAwards.valueOf((action.getFilters().get(3).get(k))));
                }
                for (int k = 0; k < action.getFilters().get(3).size(); k++) {
                    sum2 = sum2 +
                aux2.getAwards().get(ActorsAwards.valueOf((action.getFilters().get(3).get(k))));
                }*/
                // apparently it is for all awards, not just the filtered awards
                for (Integer val : aux1.getAwards().values()) {
                    sum1 = sum1 + val;
                }
                for (Integer val : aux2.getAwards().values()) {
                    sum2 = sum2 + val;
                }
                if (action.getSortType().equals("asc")) {
                    if (sum1 > sum2) {
                        Collections.swap(aux, j, j + 1);
                    } else if (sum1 == sum2) {
                        if (aux1.getName().compareTo(aux2.getName()) > 0) {
                            Collections.swap(aux, j, j + 1);
                        }
                    }
                } else {
                    if (sum1 < sum2) {
                        Collections.swap(aux, j, j + 1);
                    } else if (sum1 == sum2) {
                        if (aux1.getName().compareTo(aux2.getName()) < 0) {
                            Collections.swap(aux, j, j + 1);
                        }
                    }
                }
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for (i = 0; i < aux.size(); i++) {
            result.add(i, aux.get(i).getName());
        }
        return "Query result: " + result;
    }
    /**
     * filters actors based on their description
     */
    public
    String filterDescription(final ActionInputData action) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Actor> aux = new ArrayList<>();
        int i, j;
        for (i = 0; i < actors.actors.size() - 1; i++) {
            boolean contine = true;
            Actor temp = actors.actors.get(i);
            for (j = 0; j < action.getFilters().get(2).size(); j++) {
                if (!temp.getCareerDescription().toLowerCase().contains(
                        " " + action.getFilters().get(2).get(j))
                        && !temp.getCareerDescription().toLowerCase().contains(
                                action.getFilters().get(2).get(j) + " ")) {
                    contine = false;
                }
            }
            if (contine) {
                aux.add(temp);
            }
        }
        for (i = 0; i < aux.size() - 1; i++) {
            if (action.getSortType().equals("asc")) {
                for (j = 0; j < aux.size() - 1; j++) {
                    if (aux.get(j).getName().compareTo(aux.get(j + 1).getName()) > 0) {
                        Collections.swap(aux, j, j + 1);
                    }
                }
            } else {
                for (j = 0; j < aux.size() - 1; j++) {
                    if (aux.get(j).getName().compareTo(aux.get(j + 1).getName()) < 0) {
                        Collections.swap(aux, j, j + 1);
                    }
                }
            }
        }
        for (i = 0; i < aux.size(); i++) {
            result.add(aux.get(i).getName());
        }
        return "Query result: " + result;
    }
    /**
     * filters shows based on their ratings
     */
    public
    String rating(final ActionInputData action) {
        ArrayList<Show> result = new ArrayList<>();
        ArrayList<Show> aux = new ArrayList<>();
        for (Movie f : movies.movies.values()) {
            double sum = 0;
            for (Double d : f.getRatings()) {
                if (d != 0) {
                    sum = sum + d;
                }
            }
            f.setTotalRatings(sum / f.getRatings().size());
            if (sum != 0) {
                aux.add(f);
            }
        }
        return "Query result: " + result;
    }
    /**
     * filters shows based on their favorite score
     */
    public
    String favorite(final ActionInputData action) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Show> aux = new ArrayList<>();
        int i;
        if (action.getObjectType().equals("movies")) {
            for (Movie j : movies.movies.values()) {
                if ((action.getFilters().get(0).get(0) == null
                        || Integer.parseInt(action.getFilters().get(0).get(0))
                        == j.getYear())
                        && (action.getFilters().get(1).get(0) == null
                        || j.getGenre().contains(action.getFilters().get(1).get(0)))) {
                    aux.add(j);
                }
            }
        } else { // for shows/serials
            for (Serial j : serials.serials.values()) {
                if ((action.getFilters().get(0).get(0) == null
                        || Integer.parseInt(action.getFilters().get(0).get(0))
                        == j.getYear())
                        && (action.getFilters().get(1).get(0) == null
                        || j.getGenre().contains(action.getFilters().get(1).get(0)))) {
                    aux.add(j);
                }
            }
        }
        if (aux.size() == 0) {
            return "Query result: " + result;
        }
        // recalculate favorites
        for (Movie m : movies.movies.values()) {
            m.setFavorite(0);
        }
        for (Serial s : serials.serials.values()) {
            s.setFavorite(0);
        }
        for (User u : users.users.values()) {
            for (String s : u.getFavorite()) {
                if ((movies.isMovie(s))) {
                    movies.addFav(s);
                } else {
                    serials.addFav(s);
                }
            }
        }
        // end of favorite recalculation
        for (i = 0; i < aux.size() - 1; i++) {
            for (int j = 0; j < aux.size() - 1; j++) {
                if (action.getSortType().equals("asc")) {
                    if (aux.get(j).getFavorite() > aux.get(j + 1).getFavorite()) {
                        Collections.swap(aux, j, j + 1);
                    } else if (aux.get(j).getFavorite() == aux.get(j + 1).getFavorite()
                            && aux.get(j).getName().compareTo(aux.get(j + 1).getName()) > 0) {
                        Collections.swap(aux, j, j + 1);
                    }
                } else {
                    if (aux.get(j).getFavorite() < aux.get(j + 1).getFavorite()) {
                        Collections.swap(aux, j, j + 1);
                    } else if (aux.get(j).getFavorite() == aux.get(j + 1).getFavorite()
                            && aux.get(j).getName().compareTo(aux.get(j + 1).getName()) < 0) {
                        Collections.swap(aux, j, j + 1);
                    }
                }
            }
        }
        for (i = 0; i < action.getNumber() && i < aux.size(); i++) {
            result.add(i, aux.get(i).getName());
        }
        return "Query result: " + result;
    }
    /**
     * filters shows based on length
     */
    public
    String longest(final ActionInputData action) {
        int i;
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Show> aux = new ArrayList<>();
        if (action.getObjectType().equals("movies")) {
            for (Movie j : movies.movies.values()) {
                if ((action.getFilters().get(0).get(0) == null
                        || Integer.parseInt(action.getFilters().get(0).get(0))
                        == j.getYear())
                        && (action.getFilters().get(1).get(0) == null
                        || j.getGenre().contains(action.getFilters().get(1).get(0)))) {
                    aux.add(j);
                }
            }
        } else { // for shows/serials
            for (Serial j : serials.serials.values()) {
                if ((action.getFilters().get(0).get(0) == null
                        || Integer.parseInt(action.getFilters().get(0).get(0))
                        == j.getYear())
                        && (action.getFilters().get(1).get(0) == null
                        || j.getGenre().contains(action.getFilters().get(1).get(0)))) {
                    aux.add(j);
                }
            }
        }
        for (i = 0; i < aux.size(); i++) {
            if (action.getSortType().equals("asc")) {
                for (int j = 0; j < aux.size() - 1; j++) {
                    if (aux.get(j).getDuration() > aux.get(j + 1).getDuration()) {
                        Collections.swap(aux, j, j + 1);
                    }
                }
            } else {
                for (int j = 0; j < aux.size() - 1; j++) {
                    if (aux.get(j).getDuration() < aux.get(j + 1).getDuration()) {
                        Collections.swap(aux, j, j + 1);
                    }
                }
            }
        }
        for (i = 0; i < action.getNumber() && i < aux.size(); i++) {
            result.add(i, aux.get(i).getName());
        }

        return "Query result: " + result;
    }
    /**
     * filters shows based on how many times they have been viewed
     */
    public
    String mostViewed(final ActionInputData action) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Show> aux = new ArrayList<>();
        int i;
        if (action.getObjectType().equals("movies")) {
            for (Movie j : movies.movies.values()) {
                if ((action.getFilters().get(0).get(0) == null
                        || Integer.parseInt(action.getFilters().get(0).get(0))
                        == j.getYear())
                        && (action.getFilters().get(1).get(0) == null
                        || j.getGenre().contains(action.getFilters().get(1).get(0)))) {
                    aux.add(j);
                }
            }
        } else { // for shows/serials
            for (Serial j : serials.serials.values()) {
                if ((action.getFilters().get(0).get(0) == null
                        || Integer.parseInt(action.getFilters().get(0).get(0))
                        == j.getYear())
                        && (action.getFilters().get(1).get(0) == null
                        || j.getGenre().contains(action.getFilters().get(1).get(0)))) {
                    aux.add(j);
                }
            }
        }
        if (aux.size() == 0) {
            return "Query result: " + result;
        }
        // recalculate views
        for (Movie m : movies.movies.values()) {
            m.setViews(0);
        }
        for (Serial s : serials.serials.values()) {
            s.setViews(0);
        }
        for (User u : users.users.values()) {
            for (Map.Entry e : u.getHistory().entrySet()) {
                for (int k = 0; k < (int) e.getValue(); k++) {
                    if ((movies.isMovie((String) e.getKey()))) {
                        movies.addViews((String) e.getKey());
                    } else {
                        serials.addViews((String) e.getKey());
                    }
                }
            }
        }
        // end of views recalculation
        for (int j = 0; j < aux.size() - 1; j++) {
            if (action.getSortType().equals("asc")) {
                for (i = 0; i < aux.size() - 1; i++) {
                    if (aux.get(i).getViews() > aux.get(i + 1).getViews()
                            || (aux.get(i).getViews() == aux.get(i + 1).getViews()
                            && aux.get(i).getName().compareTo(aux.get(i + 1).getName()) > 0)) {
                        Collections.swap(aux, i, i + 1);
                    }
                }
            } else {
                for (i = 0; i < aux.size() - 1; i++) {
                    if (aux.get(i).getViews() < aux.get(i + 1).getViews()
                            || (aux.get(i).getViews() == aux.get(i + 1).getViews()
                            && aux.get(i).getName().compareTo(aux.get(i + 1).getName()) < 0)) {
                        Collections.swap(aux, i, i + 1);
                    }
                }
            }
        }
        for (i = 0; i < action.getNumber() && i < aux.size(); i++) {
            result.add(result.size(), aux.get(i).getName());
        }
        return "Query result: " + result;
    }
    /**
     * supposed to search based on what the user hasn't seen
     */
    public
    String recSearch(final ActionInputData action) {
        return "SearchRecommendation cannot be applied!";
    }
    /**
     * supposed to search based on their most rated favorite
     */
    public
    String recFavorite(final ActionInputData action) {
        return "FavoriteRecommendation cannot be applied!";
        // Don't have anymore time to do it properly
    }
    /**
     * supposed to search what they haven't seen
     */
    public
    String recStandard(final ActionInputData action) {
        return "StandardRecommendation cannot be applied!";
        // Don't have anymore time to do it properly
    }
    /**
     * supposed to show the best show they haven't seen
     */
    public
    String recBestUnseen(final ActionInputData action) {
        return "BestRatedUnseenRecommendation cannot be applied!";
        // Don't have anymore time to do it properly
    }
    /**
     * supposed to show the most popular show they have seen
     */
    public
    String recPopular(final ActionInputData action) {
        return "PopularRecommendation cannot be applied!";
        // Don't have anymore time to do it properly
    }
}
