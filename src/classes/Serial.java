package classes;

import entertainment.Season;

import java.util.ArrayList;

public class Serial extends Show {
    private int nrSeasons;
    private ArrayList<Seasons> seasons = new ArrayList<Seasons>();
    /**
     * gets number of seasons
     */
    public int getNrSeasons() {
        return nrSeasons;
    }
    /**
     * sets number of seasons
     */
    public void setNrSeasons(final int nrSeasons) {
        this.nrSeasons = nrSeasons;
    }
    /**
     * gets seasons array
     */
    public ArrayList<Seasons> getSeasons() {
        return seasons;
    }
    /**
     * gets seasons array
     */
    public void setSeasons(final ArrayList<Seasons> seasons) {
        this.seasons = seasons;
    }
    /**
     * gets duration
     */
    public int getDuration() {
        return super.getDuration();
    }
    public void setDuration() {
        int sum = 0;
        for(Seasons s : seasons)
            sum += s.getDuration();
        super.setDuration(sum);
    }

    public void setTotalRatings(double totalRatings) {
        super.setTotalRatings(totalRatings);
    }
    public double getTotalRatings() {
        return super.getTotalRatings();
    }


    public Serial(String name, int year, ArrayList<String> genre, int nrSeasons, ArrayList<Season> seasons) {
        super(name, year, genre);
        this.nrSeasons = nrSeasons;
        for(int i = 0; i < nrSeasons; ++i) {
            Seasons aux = new Seasons(i + 1, seasons.get(i).getDuration());
            this.seasons.add(aux);
        }
        this.setDuration();
    }
}
