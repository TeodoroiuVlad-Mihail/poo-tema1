package classes;

import actor.ActorsAwards;
import java.util.ArrayList;
import java.util.Map;

public class Actor {
    //mostly a copy of fileio.ActorInputData
    private String name;
    private String careerDescription;
    private ArrayList<String> filmography = new ArrayList<>();
    private Map<ActorsAwards, Integer> awards;

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public String getCareerDescription() {
        return careerDescription;
    }
    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }
    public void setFilmography(final ArrayList<String> videos) {
        this.filmography = videos;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }
    public void setAwards(final Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }

    public Actor(final String n, final String cd, final ArrayList<String> f,
                 final Map<ActorsAwards, Integer> a) {
        //names are shortened because otherwise too long line
        this.name = n;
        this.careerDescription = cd;
        this.filmography = f;
        this.awards = a;
    }

}
