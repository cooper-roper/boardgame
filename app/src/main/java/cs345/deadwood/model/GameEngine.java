package cs345.deadwood.model;

import cs345.deadwood.view.BlankAreaView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameEngine {
    private final int numberOfPlayers;
    private final List<ISet> setList;
    private final List<ICard> cardList;
    private final List<Player> players;
    private String dice;
    private String shuffle;
    private SetTrailer setTrailer = SetTrailer.getInstance();


    public GameEngine(int numberOfPlayers, List<ISet> setList, List<ICard> cardList, String shuffle) {
        this.numberOfPlayers = numberOfPlayers;
        this.setList = setList;
        this.cardList = cardList;
        this.players = new ArrayList<>();
        this.shuffle = shuffle;
        initPlayers();
    }

    public void initPlayers () {

        // initialize players at start of game
        for (int i = 1; i <= numberOfPlayers; i++) {
            switch (i) {
                case 1:
                    dice = "img/dice_b1.png";
                    break;
                case 2:
                    dice = "img/dice_r1.png";
                    break;
                case 3:
                    dice = "img/dice_p1.png";
                    break;
                case 4:
                    dice = "img/dice_y1.png";
                    break;
                case 5:
                    dice = "img/dice_w1.png";
                    break;
                case 6:
                    dice = "img/dice_v1.png";
                    break;
                case 7:
                    dice = "img/dice_g1.png";
                    break;
                case 8:
                    dice = "img/dice_c1.png";
                    break;
            }

            if (numberOfPlayers <= 4){
                Player newPlayer = new Player(i, setTrailer, 0, 10, 10, 0, dice, 1);
                players.add(newPlayer);
            }
            if (numberOfPlayers == 5){
                Player newPlayer = new Player(i, setTrailer, 0, 2, 10, 0, dice, 1);
                players.add(newPlayer);
            }
            if (numberOfPlayers == 6){
                Player newPlayer = new Player(i, setTrailer, 0, 4, 10, 0, dice, 1);
                players.add(newPlayer);
            }
            if (numberOfPlayers >= 7){
                Player newPlayer = new Player(i, setTrailer, 0, 0, 10, 0, dice.replace("1","2"), 2);
                players.add(newPlayer);
            }
        }

    }

    public String getShuffle() {
        return shuffle;
    }

    public boolean isEnabled(){
            return true;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<ISet> getSets() {
        return setList;
    }

    public List<ICard> getCards() {
        return cardList;
    }

}
