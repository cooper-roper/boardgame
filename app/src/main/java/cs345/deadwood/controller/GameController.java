package cs345.deadwood.controller;

import cs345.deadwood.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameController implements IController {
    private final GameEngine model;
    private int currentPlayerIndex;
    private GameLog gameLog;
    private List<Player> playerList;
    private int scenesCompleted;
    private SetTrailer setTrailer = SetTrailer.getInstance();

    public GameController(GameEngine model) {
        this.model = model;
        this.currentPlayerIndex = 1;
        this.model.getPlayers().get(currentPlayerIndex-1).setIsActive(true);
        this.gameLog = GameLog.getInstance();
        gameLog.GameLog(this);
        this.scenesCompleted = 0;
    }

    @Override
    public void clicked(IRole role) {
        Player player = model.getPlayers().get(currentPlayerIndex - 1);
        if(role.getPlayer() == null && player.getPlayerRole() == null && player.getRank() >= role.getLevel()) {
                if (player.getPlayerLocation().checkBlankForPlayer(player)) {
                    player.getPlayerLocation().removePlayer(player);
                }
                role.setPlayer(player);
                player.setPlayerRole(role);
                clickedEnd();
            } else {
                gameLog.log("Player can not take that role\n");
        }
    }



    @Override
    public void clicked(ISet set, boolean bool) {
        this.playerList = new ArrayList<>();
        if (bool) {
            for (Player p : model.getPlayers()) {
                if (p.getPlayerLocation() == set) {
                    playerList.add(p);
                }
            }
        } else {
            playerList.add(model.getPlayers().get(currentPlayerIndex - 1));
        }
        for (Player p : playerList) {
            for (ISet neighbor : p.getPlayerLocation().getNeighbors()) {
                if (neighbor.getName().equals(set.getName()) || set == p.getPlayerLocation()) {
                    if (p.getPlayerRole() != null) p.getPlayerRole().setPlayer(null);
                    if (p.getPlayerLocation().checkBlankForPlayer(p)) p.getPlayerLocation().removePlayer(p);
                    p.setPlayerRole(null);
                    set.addPlayer(p);
                    p.setPlayerLocation(set);
                    if (set.getName().equals("Trailer") || set.getName().equals("Office")) {
                    } else {
                        ISetScene setScene = (ISetScene) set;
                        setScene.getVisited();
                    }
                    model.getPlayers().get(currentPlayerIndex - 1).setMoved(true);

                    break;
                }
            }
            if (p.getPlayerLocation() != set) {
                gameLog.log("invalid\n");
            }
        }
    }


    @Override
    public void clickedMove(String action) {
        if(action.contains("Set")){
            if(!model.getPlayers().get(currentPlayerIndex - 1).getMoved()){
                for(ISet set : model.getSets()){
                    if (action.contains(set.getName())) {
                        clicked(set,false);
                        break;
                    }
                }
            } else {
                gameLog.log("Player " + model.getPlayers().get(currentPlayerIndex-1).getPlayerNumber() + " has already moved this turn\n");
            }
        }
    }

    public void clickedRehearsed() {
        Player currentP = model.getPlayers().get(currentPlayerIndex-1);
        if (currentP.getPlayerRole() != null) {
            System.out.println("HERE");
            ISetScene location = (ISetScene) model.getPlayers().get(currentPlayerIndex-1).getPlayerLocation();
            if (currentP.getPChips() < location.getSceneCard().getBudget()) {
                System.out.println("HERE2");
                currentP.setPChips(currentP.getPChips()+1);
                gameLog.log("Player " + currentP.getPlayerNumber() + " collected 1PC.\n");
                clickedEnd();
            } else {
                gameLog.log("Player " + currentP.getPlayerNumber() + " has the maximum number of PC.\n");
            }
        } else {
            gameLog.log("Player " + currentP.getPlayerNumber() + " does nto have a role.\n");
        }
    }

    public void clickedUpgrade(){
        Player player = model.getPlayers().get(currentPlayerIndex-1);
        if(player.getPlayerLocation().getName().equals("Office")){
            if(player.getDice().contains("1")){
                if(player.getMoney() >= 4){
                    player.setDice(player.getDice().replace("1","2"));
                    player.setRank(2);
                    player.setMoney(player.getMoney() - 4);
                }
                else if(player.getCredits() >= 5){
                    player.setDice(player.getDice().replace("1","2"));
                    player.setRank(2);
                    player.setCredits(player.getCredits() - 5);
                }
                else gameLog.log("can not afford\n");
            }
            else if(player.getDice().contains("2")){
                if(player.getMoney() >= 10){
                    player.setDice(player.getDice().replace("2","3"));
                    player.setRank(3);
                    player.setMoney(player.getMoney() - 10);
                }
                else if(player.getCredits() >= 10){
                    player.setDice(player.getDice().replace("2","3"));
                    player.setRank(3);
                    player.setCredits(player.getCredits() - 10);
                }
                else gameLog.log("can not afford\n");
            }
            else if(player.getDice().contains("3")){
                if(player.getMoney() >= 18){
                    player.setDice(player.getDice().replace("3","4"));
                    player.setRank(4);
                    player.setMoney(player.getMoney() - 18);
                }
                else if(player.getCredits() >= 15){
                    player.setDice(player.getDice().replace("3","4"));
                    player.setRank(4);
                    player.setCredits(player.getCredits() - 15);
                }
                else gameLog.log("can not afford\n");
            }
            else if(player.getDice().contains("4")){
                if(player.getMoney() >= 28){
                    player.setDice(player.getDice().replace("4","5"));
                    player.setRank(5);
                    player.setMoney(player.getMoney() - 28);
                }
                else if(player.getCredits() >= 20){
                    player.setDice(player.getDice().replace("4","5"));
                    player.setRank(5);
                    player.setCredits(player.getCredits() - 20);
                }
                else gameLog.log("can not afford\n");
            }
            else if(player.getDice().contains("5")){
                if(player.getMoney() >= 40){
                    player.setDice(player.getDice().replace("5","6"));
                    player.setRank(6);
                    player.setMoney(player.getMoney() - 40);
                }
                else if(player.getCredits() >= 25){
                    player.setDice(player.getDice().replace("5","6"));
                    player.setRank(6);
                    player.setCredits(player.getCredits() - 25);
                }
                else gameLog.log("can not afford\n");
            }
            else gameLog.log("can not upgrade\n");
        }
        clicked(player.getPlayerLocation(), false);
    }

    public void clickedRole(String action) {
        Player player = model.getPlayers().get(currentPlayerIndex - 1);
        if (action.contains("Role")) {
            if (player.getPlayerLocation() instanceof ISetScene) {
                ISetScene set = (ISetScene) player.getPlayerLocation();
                if (!set.getHasWrapped()) {
                    for (IRole role : set.getRoles()) {
                        if (action.contains(role.getName())) {
                            clicked(role);
                            break;
                        }
                    }
                    for (IRole role : set.getSceneCard().getRoles()) {
                        if (action.contains(role.getName())) {
                            clicked(role);
                            break;
                        }
                    }
                } else {
                    gameLog.log("No active scene card at " + set.getName() + "\n");
                }
            }
        }
    }

    public void clickedAct() {
        Player player = model.getPlayers().get(currentPlayerIndex-1);
        if (model.getPlayers().get(currentPlayerIndex-1).getPlayerRole() != null) {
            ISetScene set = (ISetScene) player.getPlayerLocation();
            int diceRoll = ThreadLocalRandom.current().nextInt(1, 6 + 1);
            gameLog.log("Player " + model.getPlayers().get(currentPlayerIndex-1).getPlayerNumber() + " rolled a " + diceRoll + " + " + player.getPChips() + "PC\n");
            if(set.getSceneCard().getBudget() <= diceRoll + player.getPChips()){
                player.setCredits(player.getCredits() + 2);
                Take take = (Take)set.getTakes().get(set.getNumberOfActs()-1);
                take.setActed(true);
                set.numberOfActs();
                gameLog.log("Player roll successful\n");
            }
            else if(set.getSceneCard().getBudget() > diceRoll){
                gameLog.log("Player roll failure\n");
            }
            if(set.getNumberOfActs() == 0){
                sceneWrap(set);
                System.out.println("1");
                set.resetActs();
                System.out.println("2");
                set.setSceneCard(null);
                System.out.println("3");
            }
            gameLog.setAction("end turn");
        }
        else gameLog.log("can not act anymore\n");
    }


    public void clickedEnd() {
        model.getPlayers().get(currentPlayerIndex-1).setIsActive(false);
        if (model.getPlayers().get(currentPlayerIndex-1).getPlayerRole() == null) {
            model.getPlayers().get(currentPlayerIndex-1).setMoved(false);
        }
        currentPlayerIndex++;
        if (currentPlayerIndex >= model.getPlayers().size()+1) {
            currentPlayerIndex = 1;
        }
        model.getPlayers().get(currentPlayerIndex-1).setIsActive(true);
    }

    public void nextDay() {
        this.scenesCompleted = 0;
        for (Player player : model.getPlayers()) {
            player.getPlayerLocation().removePlayer(player);
            player.setPlayerLocation(setTrailer);
            player.setMoved(false);
            clickedEnd();
        }
        for (ISet set : model.getSets()) {
            if (set instanceof  ISetScene) {
                ISetScene set2 = (ISetScene) set;
                for(IArea aTake : set2.getTakes()) {
                    Take take = (Take)aTake;
                    take.setActed(false);
                }
                set2.setSceneCard(null);
                set2.setNextDay(true);
                set2.getVisited();
                set2.setHasWrapped(false);
            }
        }
        clicked(setTrailer, true);
    }


    public void sceneWrap(ISetScene set) {
        gameLog.log("Wrapping up scene on set " + set.getName() + "\n");
        List<Integer> diceRolls = new ArrayList<>();
        List<IRole> sceneCardRolls = new ArrayList<>();
        List<Integer> sceneCardRollLevels = new ArrayList<>();

        Player player = model.getPlayers().get(currentPlayerIndex-1);
        ISetScene playerLocation = (ISetScene) player.getPlayerLocation();
        for (int i = 0; i < playerLocation.getSceneCard().getRoles().size(); i++) {
            int diceRoll = ThreadLocalRandom.current().nextInt(1, 6 + 1);
            diceRolls.add(diceRoll);
        }
        Collections.sort(diceRolls);
        gameLog.log("Scene Wrap dice rolls: ");
        for (int thisRole : diceRolls) {
            gameLog.log(thisRole + " ");
        }
        gameLog.log("\n");

        for (IRole role : playerLocation.getSceneCard().getRoles()) {
            sceneCardRolls.add(role);
            sceneCardRollLevels.add(role.getLevel());
        }
        Collections.sort(sceneCardRollLevels);
        int j = 0;
        for (int i : sceneCardRollLevels) {
            for (IRole role : playerLocation.getSceneCard().getRoles()) {
                if (i == role.getLevel() && role.getPlayer() != null) {
                    int tempMoney = i + diceRolls.get(j);
                    gameLog.log("Player " + role.getPlayer().getPlayerNumber() + ": gets $" + tempMoney + " --> $" + diceRolls.get(j) + " (dice roll) + $" + role.getLevel() + " (roll)\n");
                    role.getPlayer().setMoney(tempMoney);
                    role.getPlayer().setPChips(0);
                    role.getPlayer().setMoved(false);
                    j++;
                }
            }
        }

        for (IRole role : playerLocation.getRoles()) {
            if (role.getPlayer() != null) {
                gameLog.log("Player " + role.getPlayer().getPlayerNumber() + ": gets $" + role.getLevel() + "\n");
                role.getPlayer().setMoney(role.getLevel());
                role.getPlayer().setPChips(0);
                role.getPlayer().setMoved(false);
            }
        }
        set.setHasWrapped(true);
        clicked(set,true);
        this.scenesCompleted++;

        if (scenesCompleted >= 9) {
            nextDay();
        }
    }


}
