package classes;

import fileio.ActorInputData;
import java.util.ArrayList;
import java.util.List;

public class Actors {
    ArrayList<Actor> actors = new ArrayList<>();
    public Actors(final List<ActorInputData> list) {
        for (ActorInputData i : list) {
            Actor actor = new Actor(i.getName(), i.getCareerDescription(), i.getFilmography(),
                    i.getAwards());
            this.actors.add(actor);
        }
    }
}
