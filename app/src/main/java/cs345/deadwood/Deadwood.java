/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package cs345.deadwood;

import cs345.deadwood.controller.GameController;
import cs345.deadwood.model.CardParser;
import cs345.deadwood.model.GameEngine;
import cs345.deadwood.model.SetParser;
import cs345.deadwood.view.BoardView;

import java.util.concurrent.ThreadLocalRandom;

public class Deadwood {

    public static void main(String[] args) {
        /* Get number of players from command line arg
         * Usage: $ ./graldew run --args="2"
         */
        int numberOfPlayers = 2;  // default number of players
        if (args.length > 0) {
            try {
                numberOfPlayers = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Usage: ./gradlew run --args \"NUM\", where NUM should be an integer between 2 and 8.");
                return;
            }
            if (numberOfPlayers > 8 || numberOfPlayers < 2) {
                System.out.println("Usage: ./gradlew run --args \"NUM\", where NUM should be an integer between 2 and 8.");
                return;
            }
        }
        int num = ThreadLocalRandom.current().nextInt(1, 3);
        System.out.println(num);
        String shuffle = "";
        if (num == 1) {
            shuffle = "Random";
        } else {
            shuffle = "By-Budget";
        }
        GameEngine model = new GameEngine(numberOfPlayers, new SetParser().getSets(), new CardParser().getCards(), shuffle); // TODO: Replace the null parameters with valid values
        GameController controller = new GameController(model);
        BoardView view = new BoardView(model, controller);
        view.init();
    }
}