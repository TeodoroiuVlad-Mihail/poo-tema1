package classes;

import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.Map;
public class Actor {
    private String name;
    private String careerDescription;
    private ArrayList<String> fimography = new ArrayList<>();
    private Map<ActorsAwards, Integer> awards; //
    private double average;
    /**
     * gets name
     */
    public String getName() {
        return name;
    }
    /**
     * sets name
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * gets career description
     */
    public String getCareerDescription() {
        return careerDescription;
    }
    /**
     * sets career description
     */
    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }
    /**
     * gets filmography
     */
    public ArrayList<String> getFilmography() {
        return fimography;
    }
    /**
     * setss filmography
     */
    public void setFimography(final ArrayList<String> filmography) {
        this.fimography = fimography;
    }
    /**
     * gets awards
     */
    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }
    /**
     * sets awards
     */
    public void setAwards(final Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }
    /**
     * gets actor average rating
     */
    public void setAverage(final double average) {
        this.average = average;
    }
    /**
     * sets actor average rating
     */
    public double getAverage() {
        return this.average;
    }

    public Actor(final String name, final String careerDescription,
                 final ArrayList<String> filmography, final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.fimography = filmography;
        this.awards = awards;
    }
}
