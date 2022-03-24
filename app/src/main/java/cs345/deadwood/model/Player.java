package cs345.deadwood.model;

import cs345.deadwood.view.IModelObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends JPanel implements IModel {
    private int rank;
    private int playerNum;
    private ISet location;
    private IRole role;
    private int money;
    private int credits;
    private int score;
    private int pChips;
    private String dice;
    private boolean isActive;
    private boolean move;
    private List<IModelObserver> observers = new ArrayList<>();

    public Player(int playerNum, ISet location, int money, int credits, int score, int pChips, String dice, int rank) {
        this.playerNum = playerNum;
        this.location = location;
        this.money = money;
        this.credits = credits;
        this.score = score;
        this.pChips = pChips;
        this.dice = dice;
        this.isActive = false;
        this.move = false;
        this.rank = rank;
    }

    public void setMoved(boolean bool){
        this.move = bool;
    }

    public boolean getMoved(){return move;}

    public void setIsActive (boolean active) {
        this.isActive = active;
        notifyObservers();
    }
    public void setPlayerLocation (ISet set) {
        this.location = set;
        notifyObservers();
    }

    public void setMoney (int i) {
        this.money = this.money + i;
        notifyObservers();
    }

    public void setPlayerRole (IRole role) {
        this.role = role;
        notifyObservers();
    }

    public void setDice (String dice) {
        this.dice = dice;
        notifyObservers();
    }

    public void setCredits (int creds) {
        notifyObservers();
        this.credits = creds;
    }

    public void setPChips (int pc) {
        notifyObservers();
        this.pChips = pc;
    }


    public Player getPlayer(){
        return this;
    }

    public int getPlayerNumber() { return playerNum; }

    public ISet getPlayerLocation() {
        return location;
    }

    public String getPlayerLocationName() {
        return location.getName();
    }

    public int getMoney() {
        return money;
    }

    public int getCredits() {
        return credits;
    }

    public int getScore() {
        return score;
    }

    public int getPChips() {
        return pChips;
    }

    public String getDice() {
        return dice;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public IRole getPlayerRole () {
        return role;
    }

    public int getRank(){
        return rank;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public void calculateScore () {
        this.score = this.money + this.credits + (5 * this.rank);
    }

    public void registerObserver(IModelObserver ob) { observers.add(ob); }


    public void removeObserver(IModelObserver ob) {
        observers.remove(ob);
    }


    public void notifyObservers() {
        for (IModelObserver ob : observers) {
            ob.updateResult();
        }
    }

}
