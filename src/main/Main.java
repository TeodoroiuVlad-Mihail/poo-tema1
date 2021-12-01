package main;

import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import fileio.ActionInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

//And now my classes, God I wish classes.* did not give a checkstyle error
import classes.Actors;
import classes.Movies;
import classes.Serials;
import classes.Users;
import classes.Query;
import classes.Command;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //entry point to implementation

        Actors actors = new Actors(input.getActors());
        Movies movies = new Movies(input.getMovies());
        Serials serials = new Serials(input.getSerials());
        Users users = new Users(input.getUsers());
        Query query = new Query(actors,movies,serials,users);

        for(ActionInputData action : input.getCommands()) {
            JSONObject object = null;
            switch (action.getActionType()) {
                case "command":
                    object = fileWriter.writeFile(action.getActionId(),"",Command.command(action,users, movies, serials));
                    break;
                case "query" :
                    switch (action.getObjectType()) {
                        case "users" :
                            object = fileWriter.writeFile(action.getActionId(),"",query.NrOfRatings(action));
                            break;
                        case "actors":
                            switch(action.getCriteria()){
                                case "filter_description":
                                    object = fileWriter.writeFile(action.getActionId(),"",query.FilterDescription(action));
                                    break;
                                case "awards":
                                    object = fileWriter.writeFile(action.getActionId(),"",query.Awards(action));
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default :
                            switch(action.getCriteria()){
                                case "most_viewed":
                                    object = fileWriter.writeFile(action.getActionId(),"",query.MostViewed(action));
                                    break;
                                case "longest":
                                    object = fileWriter.writeFile(action.getActionId(),"",query.Longest(action));
                                    break;
                                case "favorite":
                                    object = fileWriter.writeFile(action.getActionId(),"",query.Favorite(action));
                                    break;
                                case "ratings":
                                    object = fileWriter.writeFile(action.getActionId(),"",query.Rating(action));
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                    break;
                case "recommendation":
                    switch (action.getType()){
                        case "search":
                            object = fileWriter.writeFile(action.getActionId(),"",query.Search(action));
                            break;
                        case "favorite":
                            object = fileWriter.writeFile(action.getActionId(),"",query.RecFavorite(action));
                            break;
                        default: break;

                    }

                default: break;
            }
            arrayResult.add(arrayResult.size(), object);
        }

        //I should probably clean up the query calls like with the commands, but there's just so many of them
        //end of student implementation

        fileWriter.closeJSON(arrayResult);
    }
}
