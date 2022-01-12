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
    /**
     * sets duration
     */
    public void setDuration() {
        int sum = 0;
        for (Seasons s : seasons) {
            sum = sum + s.getDuration();
        }
        super.setDuration(sum);
    }
    /**
     * gets total ratings
     */
    public double getTotalRatings() {
        return super.getTotalRatings();
    }
    /**
     * sets total ratings
     */
    public void setTotalRatings(final double totalRatings) {
        super.setTotalRatings(totalRatings);
    }


    public Serial(final String name, final int year, final ArrayList<String> genre,
                  final int nrSeasons, final ArrayList<Season> seasons) {
        super(name, year, genre);
        this.nrSeasons = nrSeasons;
        for (int i = 0; i < nrSeasons; ++i) {
            Seasons aux = new Seasons(i + 1, seasons.get(i).getDuration());
            this.seasons.add(aux);
        }
        this.setDuration();
    }
}
