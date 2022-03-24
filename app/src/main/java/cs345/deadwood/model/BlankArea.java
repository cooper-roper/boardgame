package cs345.deadwood.model;

import cs345.deadwood.view.IModelObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BlankArea extends Area implements IModel {

    private Player player;
    private List<IModelObserver> observers = new ArrayList<>();

    public BlankArea(int x, int y, int h, int w) {
        super(x, y, h, w);
    }

    public BlankArea(Area area){
        super(area.getX(), area.getY(), area.getH(), area.getW());
    }


    public void setPlayer(Player player) {
        this.player = player;
        notifyObservers();
    }


    public Player getPlayer(){
        return player;
    }

    public void registerObserver(IModelObserver ob) { observers.add(ob); }

    public void removeObserver(IModelObserver ob) {
        observers.remove(ob);
    }

    @Override
    public void notifyObservers() {
        for (IModelObserver ob : observers) {
            ob.updateResult();
        }
    }


}
