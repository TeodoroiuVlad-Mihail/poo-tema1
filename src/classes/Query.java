package classes;

import actor.ActorsAwards;
import fileio.ActionInputData;

import java.util.*;

public class Query { //also reccomendations
    Actors actors;
    Movies movies;
    Serials serials;
    Users users;

    public Query(Actors a, Movies m, Serials s, Users u) {
        actors = a;
        movies = m;
        serials = s;
        users = u;
    }

    public String NrOfRatings(final ActionInputData action) {
        ArrayList<User> aux = new ArrayList<>();
        int i;
        aux.addAll(users.users.values());
        if (action.getSortType().equals("asc")) {
            for (int j = 0; j < aux.size() - 1; j ++)
                for (i = 0; i < aux.size() - 1; i++) {
                    if (aux.get(i).getActions() > aux.get(i + 1).getActions()
                            || (aux.get(i).getActions() == aux.get(i + 1).getActions() &&
                            aux.get(i).getUsername().compareTo(aux.get(i + 1).getUsername()) > 0))
                        Collections.swap(aux, i, i + 1);
                }
        } else {
            for (int j = 0; j < aux.size() - 1; j ++)
                for (i = 0; i < aux.size() - 1; i++) {
                    if (aux.get(i).getActions() < aux.get(i + 1).getActions()
                            || (aux.get(i).getActions() == aux.get(i + 1).getActions() &&
                            aux.get(i).getUsername().compareTo(aux.get(i + 1).getUsername()) < 0))
                        Collections.swap(aux, i, i + 1);
                }
        }
        ArrayList<String> result = new ArrayList<>();
        for (i = 0; i < aux.size() && result.size() < action.getNumber(); i++) {
            if (aux.get(i).getRated().size() > 0)
                result.add(aux.get(i).getUsername());
        }
        return "Query result: " + result.toString();
    }

    public String Average(final ActionInputData action) {
        ArrayList<Actor> aux = new ArrayList<>();
        int i, j;
        for (i = 0; i < actors.actors.size(); i++) {
            Actor temp = actors.actors.get(i);
            double sum = 0;
            double nr = 0;
            for (String f: temp.getFilmography()){
                if(movies.isMovie(f)) {
                    for(Movie m: movies.movies.values()){
                        if(m.getName().equals(f)){
                            for(double k: m.getRatings()){
                                sum = sum + k;
                                nr = nr + 1;
                            }
                        }
                    }
                } else {
                    for(Serial s: serials.serial.values()){
                        if(s.getName().equals(f)){
                            for(Seasons ss: s.getSeasons())
                                for(double k: ss.getRatings()){
                                    sum = sum + k;
                                    nr = nr + 1;
                                }
                        }
                    }
                }
            }
            if (nr > 0 ) {
                temp.SetAverage(sum / nr);
                aux.add(temp);
            }
        }
        for (i = 0; i < aux.size() - 1; i++) {
            for(j = 0; j < aux.size() - 1; j++) {
                Actor aux1 = aux.get(j);
                Actor aux2 = aux.get(j + 1);
                double sum1 = aux1.getAverage();
                double sum2 = aux2.getAverage();
                System.out.println(sum1 + " " + sum2);

                if (action.getSortType().equals("asc")) {
                    if (sum1 > sum2) {
                        Collections.swap(aux, j, j + 1);
                    }
                    else if (sum1 == sum2) {
                        if (aux1.getName().compareTo(aux2.getName()) > 0)
                            Collections.swap(aux, j, j + 1);
                    }
                } else {
                    if (sum1 < sum2) {
                        Collections.swap(aux, j, j + 1);
                    }
                    else if (sum1 == sum2) {
                        if (aux1.getName().compareTo(aux2.getName()) < 0)
                            Collections.swap(aux, j, j + 1);
                    }
                }
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for (i = 0; i < aux.size() && result.size() < action.getNumber(); i++)
            result.add(aux.get(i).getName());
        return "Query result: " + result.toString();
    }

    public String Awards(final ActionInputData action) {
        ArrayList<Actor> aux = new ArrayList<>();
        int i, j;
        for (i = 0; i < actors.actors.size(); i++) {
            boolean ok = true;
            Actor temp = actors.actors.get(i);
            for (j = 0; j < action.getFilters().get(3).size(); j++) {
                if (! temp.getAwards().containsKey(ActorsAwards.valueOf((action.getFilters().get(3).get(j))))){
                    ok = false;
                }
            }
            if (ok)
                aux.add(temp);
        }
        for (i = 0; i < aux.size() - 1; i++) {
            for(j = 0; j < aux.size() - 1; j++) {
                Actor aux1 = aux.get(j);
                Actor aux2 = aux.get(j + 1);
                int sum1 = 0;
                int sum2 = 0;
                /*for (int k = 0; k < action.getFilters().get(3).size(); k++) {
                    sum1 = sum1 + aux1.getAwards().get(ActorsAwards.valueOf((action.getFilters().get(3).get(k))));
                }
                for (int k = 0; k < action.getFilters().get(3).size(); k++) {
                    sum2 = sum2 + aux2.getAwards().get(ActorsAwards.valueOf((action.getFilters().get(3).get(k))));
                }*/
                //apparently it is for all awards, not just the filtered awards
                for (Integer val : aux1.getAwards().values()) {
                    sum1 = sum1 + val;
                }
                for (Integer val : aux2.getAwards().values()) {
                    sum2 = sum2 + val;
                }
                if (action.getSortType().equals("asc")) {
                    if (sum1 > sum2) {
                        Collections.swap(aux, j, j + 1);
                    }
                    else if (sum1 == sum2) {
                        if (aux1.getName().compareTo(aux2.getName()) > 0)
                            Collections.swap(aux, j, j + 1);
                    }
                } else {
                    if (sum1 < sum2) {
                        Collections.swap(aux, j, j + 1);
                    }
                    else if (sum1 == sum2) {
                        if (aux1.getName().compareTo(aux2.getName()) < 0)
                            Collections.swap(aux, j, j + 1);
                    }
                }
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for (i = 0; i < aux.size(); i++)
            result.add(i, aux.get(i).getName());
        return "Query result: " + result.toString();
    }

    public String FilterDescription(final ActionInputData action) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Actor> aux = new ArrayList<>();
        int i, j;
        for (i = 0; i < actors.actors.size() - 1; i++) {
            boolean contine = true;
            Actor temp = actors.actors.get(i);
            for (j = 0; j < action.getFilters().get(2).size(); j++) {
                if (!temp.getCareerDescription().toLowerCase().contains(" " + action.getFilters().get(2).get(j)))
                    contine = false;
            }
            if (contine)
                aux.add(temp);
        }
        if (action.getSortType().equals("asc")) {
            for (i = 0; i < aux.size() - 1; i++) {
                if (aux.get(i).getCareerDescription().compareTo(aux.get(i + 1).getCareerDescription()) > 0)
                    Collections.swap(aux, i, i + 1);
            }
        } else {
            for (i = 0; i < aux.size() - 1; i++) {
                if (aux.get(i).getCareerDescription().compareTo(aux.get(i + 1).getCareerDescription()) < 0)
                    Collections.swap(aux, i, i + 1);
            }
        }
        for (i = 0; i < aux.size() /*&& (result.size() < action.getNumber() || action.getNumber() <= 0)*/; i++)
            result.add(aux.get(i).getName());

        return "Query result: " + result.toString();
    }

    public String Rating(final ActionInputData action) {
        ArrayList<Video> result = new ArrayList<>();
        ArrayList<Video> aux = new ArrayList<>();
        for (Movie f : movies.movies.values()) {
            double sum = 0;
            for (Double d : f.getRatings()) {
                if (d != 0) {
                    sum += d;
                }
            }
            f.setTotalRatings(sum / f.getRatings().size());
            if (sum != 0)
                aux.add(f);
        }
        return "Query result: " + result.toString();
    }

    public String Favorite(final ActionInputData action) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Video> aux = new ArrayList<>();
        int i;
        if (action.getObjectType().equals("movies")) {
            for (Movie j : movies.movies.values()){
                if ( (action.getFilters().get(0).get(0) == null || Integer.parseInt(action.getFilters().get(0).get(0)) == j.getYear() )
                        && (action.getFilters().get(1).get(0) == null || j.getGenre().contains(action.getFilters().get(1).get(0))) )
                    aux.add(j);
            }
        }
        else { //for shows/serials
            for (Serial j : serials.serial.values()){
                if ( (action.getFilters().get(0).get(0) == null || Integer.parseInt(action.getFilters().get(0).get(0)) == j.getYear() )
                        &&(action.getFilters().get(1).get(0) == null || j.getGenre().contains(action.getFilters().get(1).get(0))) )
                    aux.add(j);
            }
        }
        if (aux.size() == 0)
            return "Query result: " + result.toString();
        //recalculate favorites
        for(Movie m: movies.movies.values()){
            m.SetFavorite(0);
        }
        for(Serial s: serials.serial.values()){
            s.SetFavorite(0);
        }
        for (User u: users.users.values()){
            for(String s: u.getFavorite())
                if((movies.isMovie(s))) {
                    movies.addFav(s);
                }else{
                    serials.addFav(s);
                }
        }
        //end of favorite recalculation
        for (i = 0; i < aux.size() - 1; i++) {
            for(int j = 0; j < aux.size() - 1; j++) {
                if (action.getSortType().equals("asc")) {
                    if (aux.get(j).getFavorite() > aux.get(j + 1).getFavorite()) {
                        Collections.swap(aux, j, j + 1);
                    } else if (aux.get(j).getFavorite() == aux.get(j + 1).getFavorite() && aux.get(j).getName().compareTo(aux.get(j+1).getName()) > 0)
                        Collections.swap(aux, j, j + 1);
                } else {
                    if (aux.get(j).getFavorite() < aux.get(j + 1).getFavorite()) {
                        Collections.swap(aux, j, j + 1);
                    } else if (aux.get(j).getFavorite() == aux.get(j + 1).getFavorite() && aux.get(j).getName().compareTo(aux.get(j+1).getName()) < 0)
                        Collections.swap(aux, j, j + 1);
                }
            }
        }
        for (i = 0; i < action.getNumber() && i < aux.size(); i++)
            result.add(i, aux.get(i).getName());
        return "Query result: " + result.toString();
    }

    public String Longest(final ActionInputData action) {
        int i;
        int dur = 0;
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Video> aux = new ArrayList<>();
        /*for (Movie f : movies.movies.values()) {
            if (f.getDuration() != 0)
                aux.add(f);
        }*/
        if (action.getObjectType().equals("movies")) {
            for (Movie j : movies.movies.values()){
                if ( (action.getFilters().get(0).get(0) == null || Integer.parseInt(action.getFilters().get(0).get(0)) == j.getYear() )
                        && (action.getFilters().get(1).get(0) == null || j.getGenre().contains(action.getFilters().get(1).get(0))) )
                    aux.add(j);
            }
        }
        else { //for shows/serials
            for (Serial j : serials.serial.values()){
                if ( (action.getFilters().get(0).get(0) == null || Integer.parseInt(action.getFilters().get(0).get(0)) == j.getYear() )
                        &&(action.getFilters().get(1).get(0) == null || j.getGenre().contains(action.getFilters().get(1).get(0))) )
                    aux.add(j);
            }
        }
        if (action.getSortType().equals("asc")) {
            for (i = 0; i < aux.size() - 2; i++) {
                if (aux.get(i).getDuration() > aux.get(i + 1).getDuration())
                    Collections.swap(aux, i, i + 1);
            }
        } else {
            for (i = 0; i < aux.size() - 2; i++) {
                if (aux.get(i).getDuration() < aux.get(i + 1).getDuration())
                    Collections.swap(aux, i, i + 1);
            }
        }
        for (i = 0; i < action.getNumber() && i < aux.size(); i++)
            result.add(i, aux.get(i).getName());

        return "Query result: " + result.toString();
    }

    public String MostViewed(final ActionInputData action) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Video> aux = new ArrayList<>();
        int i;
        if (action.getObjectType().equals("movies")) {
            for (Movie j : movies.movies.values()){
                if ( (action.getFilters().get(0).get(0) == null || Integer.parseInt(action.getFilters().get(0).get(0)) == j.getYear() )
                        && (action.getFilters().get(1).get(0) == null || j.getGenre().contains(action.getFilters().get(1).get(0))) )
                    aux.add(j);
            }
        }
        else { //for shows/serials
            for (Serial j : serials.serial.values()){
                if ( (action.getFilters().get(0).get(0) == null || Integer.parseInt(action.getFilters().get(0).get(0)) == j.getYear() )
                        &&(action.getFilters().get(1).get(0) == null || j.getGenre().contains(action.getFilters().get(1).get(0))) )
                    aux.add(j);
            }
        }
        if (aux.size() == 0)
            return "Query result: " + result.toString();
        //recalculate views
        for(Movie m: movies.movies.values()){
            m.SetViews(0);
        }
        for(Serial s: serials.serial.values()){
            s.SetViews(0);
        }
        for (User u: users.users.values()){
            for(Map.Entry e: u.getHistory().entrySet()){
                for(int k = 0; k < (int)e.getValue(); k++){
                    if ((movies.isMovie((String) e.getKey()))) {
                        movies.addViews((String) e.getKey());
                    } else {
                        serials.addViews((String) e.getKey());
                    }
                }
            }
        }
        //end of views recalculation
        for(int j = 0; j < aux.size() - 1; j ++) {
            if (action.getSortType().equals("asc")) {
                for (i = 0; i < aux.size() - 1; i++) {
                    if (aux.get(i).getViews() > aux.get(i + 1).getViews())
                        Collections.swap(aux, i, i + 1);
                }
            } else {
                for (i = 0; i < aux.size() - 1; i++) {
                    if (aux.get(i).getViews() < aux.get(i + 1).getViews())
                        Collections.swap(aux, i, i + 1);
                }
            }
        }
        for (i = 0; i < action.getNumber()  && i < aux.size(); i++)
            result.add(result.size(), aux.get(i).getName());
        return "Query result: " + result.toString();
    }

    public String Search(final ActionInputData action) {
        return "SearchRecommendation cannot be applied!";
    }

    public String RecFavorite(final ActionInputData action) {
        return "FavoriteRecommendation cannot be applied!";
        //Hurrah for easy command
    }

    public String Standard(final ActionInputData action) {
        return "StandardRecommendation cannot be applied!";
        //Hurrah for easy command
    }

    public String BestUnseen(final ActionInputData action) {
        return "BestRatedUnseenRecommendation cannot be applied!";
        //Hurrah for easy command
    }
    public String Popular(final ActionInputData action) {
        return "PopularRecommendation cannot be applied!";
        //Hurrah for easy command
    }

}