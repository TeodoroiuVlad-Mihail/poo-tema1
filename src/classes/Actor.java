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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCareerDescription() {
        return careerDescription;
    }
    public void setCareerDescription(String careerDescription) {
        this.careerDescription = careerDescription;
    }

    public ArrayList<String> getFilmography() {
        return fimography;
    }
    public void setFimography(ArrayList<String> filmography) {
        this.fimography = fimography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }
    public void setAwards(Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }

    public void SetAverage(double average) {
        this.average = average;
    }
    public double getAverage() {
        return this.average;
    }

    public Actor(String name, String careerDescription, ArrayList<String> filmography, Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.fimography = filmography;
        this.awards = awards;
    }

}