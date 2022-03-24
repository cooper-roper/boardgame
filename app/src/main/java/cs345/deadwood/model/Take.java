package cs345.deadwood.model;

import cs345.deadwood.view.IModelObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Take extends Area implements IModel {

    private Player player;
    private List<IModelObserver> observers = new ArrayList<>();
    private Boolean rehearsed;

    public Take(int x, int y, int h, int w) {
        super(x, y, h, w);
    }

    public Take(Area area){
        super(area.getX(), area.getY(), area.getH(), area.getW());
    }

    public void setActed(Boolean rehearsed){
        this.rehearsed = rehearsed;
        notifyObservers();
    }

    public Boolean getActed(){
        return this.rehearsed;
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
