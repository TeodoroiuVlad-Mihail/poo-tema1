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
        ArrayList<String> result = new ArrayList<>();
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

        for (i = 0; i < aux.size() && i < action.getNumber(); i++) {
            if (aux.get(i).getActions() != 0)
                result.add(result.size(), aux.get(i).getUsername());
        }
        return "Query result: " + result.toString();
    }

    public String Awards(final ActionInputData action) {
        ArrayList<Actor> result = new ArrayList<>();
        int i, j;
        for (i = 0; i < actors.actors.size() - 1; i++) {
            boolean ok = true;
            Actor temp = actors.actors.get(i);
            for (j = 0; j < action.getFilters().get(3).size(); j++) {
                System.out.println(ActorsAwards.valueOf((action.getFilters().get(3).get(j))));
                //^ to see if I am accessing the correct string or not
                if (! temp.getAwards().containsKey(ActorsAwards.valueOf((action.getFilters().get(3).get(j))))){
                    ok = false;
                }
            }
            if (ok)
                result.add(temp);
        }
        for (i = 0; i < result.size() - 2; i++) {
                Actor aux1 = result.get(i);
                Actor aux2 = result.get(i + 1);
                int suma1 = 0;
                int suma2 = 0;
                for (Integer val : aux1.getAwards().values()) {
                    if (val != 0)
                        suma1 += val;
                }
                for (Integer val : aux2.getAwards().values()) {
                    if (val != 0)
                        suma2 += val;
                }
                if (action.getSortType().equals("asc")) {
                    if (suma1 > suma2) {
                        Collections.swap(result, i, i + 1);
                    }
                    if (suma1 == suma2) {
                        if (aux1.getName().compareTo(aux2.getName()) > 0)
                            Collections.swap(result, i, i + 1);
                    }
                } else {
                    if (suma1 < suma2) {
                        Collections.swap(result, i, i + 1);
                    }
                    if (suma1 == suma2) {
                        if (aux1.getName().compareTo(aux2.getName()) < 0)
                            Collections.swap(result, i, i + 1);
                    }
                }
        }
        ArrayList<String> aux = new ArrayList<>();
        for (i = 0; i < result.size() - 1; i++)
            aux.add(i, result.get(i).getName());
        return "Query result: " + aux.toString();
    }

    public String FilterDescription(final ActionInputData action) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Actor> aux = new ArrayList<>();
        int i, j;
        for (i = 0; i < actors.actors.size() - 1; i++) {
            boolean contine = true;
            Actor temp = actors.actors.get(i);
            for (j = 0; j < action.getFilters().get(2).size(); ++j) {
                if (!temp.getCareerDescription().contains(action.getFilters().get(2).get(j)))
                    contine = false;
            }
            if (contine)
                aux.add(temp);
        }
        if (action.getSortType().equals("asc")) {
            for (i = 0; i < aux.size() - 2; i++) {
                if (aux.get(i).getCareerDescription().compareTo(aux.get(i + 1).getCareerDescription()) > 0)
                    Collections.swap(aux, i, i + 1);
            }
        } else {
            for (i = 0; i < aux.size() - 2; i++) {
                if (aux.get(i).getCareerDescription().compareTo(aux.get(i + 1).getCareerDescription()) < 0)
                    Collections.swap(aux, i, i + 1);
            }
        }
        for (i = 0; i < action.getNumber() && i < aux.size(); i++)
            result.add(i, aux.get(i).getName());

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
            aux.addAll(movies.movies.values());
        }
        else {
            aux.addAll(serials.serial.values());
        }
        if (aux.size() == 0)
            return "Query result: " + result.toString();
        for (i = 0; i < aux.size() - 2; i++) {
            if (action.getSortType().equals("asc")) {
                if (aux.get(i).getFavorite() > aux.get(i + 1).getFavorite()) {
                    Collections.swap(aux, i, i + 1);
                }
            } else {
                if (aux.get(i).getFavorite() < aux.get(i + 1).getFavorite()) {
                    Collections.swap(aux, i, i + 1);
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
        if (action.getObjectType().equals("movies")){
            for (Movie j : movies.movies.values()){
                if (action.getFilters().get(0).get(0) == null || Integer.parseInt(action.getFilters().get(0).get(0)) == j.getYear())
                    aux.add(j);
            }
            System.out.println(action.getFilters().get(0).get(0) + " " + action.getFilters().get(1) +
                    " " + action.getFilters().get(2) + " " + action.getFilters().get(3));
        }
        else {
            for (Serial s : serials.serial.values()) {
                s.setDuration();
                if (s.getDuration() != 0)
                    aux.add(s);
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
        aux.addAll(movies.movies.values());
        aux.addAll(serials.serial.values());
        if (aux.size() == 0)
            return "Query result: " + result.toString();
        if (action.getSortType().equals("asc")) {
            for (i = 0; i < aux.size() - 2; i++) {
                if (aux.get(i).getFavorite() > aux.get(i + 1).getFavorite())
                    Collections.swap(aux, i, i + 1);
            }
        } else {
            for (i = 0; i < aux.size() - 2; i++) {
                if (aux.get(i).getFavorite() > aux.get(i + 1).getFavorite())
                    Collections.swap(aux, i, i + 1);
            }

        }
        for (i = 0; i < action.getNumber()  && i < aux.size(); i++)
            result.add(result.size(), aux.get(i).getName());
        return "Query result: " + result.toString();
    }

    public String Search(final ActionInputData action) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Video> aux = new ArrayList<>();
        int i;
        aux.addAll(movies.movies.values());
        aux.addAll(serials.serial.values());
        if (aux.size() == 0)
            return "Query result: " + result.toString();
        /*if (action.getSortType().equals("asc")) {
            for (i = 0; i < aux.size() - 2; i++) {
                if (aux.get(i).getFavorite() > aux.get(i + 1).getFavorite())
                    Collections.swap(aux, i, i + 1);
            }
        } else {
            for (i = 0; i < aux.size() - 2; i++) {
                if (aux.get(i).getFavorite() > aux.get(i + 1).getFavorite())
                    Collections.swap(aux, i, i + 1);
            }

        }*/
        for (i = 0; i < action.getNumber()  && i < aux.size(); i++)
            result.add(result.size(), aux.get(i).getName());
        return "Query result: " + result.toString();
    }

    public String RecFavorite(final ActionInputData action) {
        return "FavoriteRecommendation cannot be applied!";
        //Hurrah for easy command
    }

}