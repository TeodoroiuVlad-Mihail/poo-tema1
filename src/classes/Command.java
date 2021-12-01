package classes;

import fileio.ActionInputData;

public final class Command {
    private Command() {
    }

    public static String command(final ActionInputData action,
                                 final Users user, final Movies movies, final Serials serials) {
        switch (action.getType()) {
            case "favorite":
                return user.addFav(action.getUsername(), action.getTitle(), movies, serials);
            case "view":
                return user.addViews(action.getUsername(), action.getTitle(), movies, serials);
            case "rating":
                if (movies.isMovie(action.getTitle())) {
                    return user.addRatingFilm(action.getUsername(), action.getTitle(),
                            movies, action.getGrade());
                } else {
                    return user.addRatingSerial(action.getUsername(), action.getTitle(),
                            action.getSeasonNumber(), action.getGrade(), serials);
                }
            default:
                return null;
        }
    }
}