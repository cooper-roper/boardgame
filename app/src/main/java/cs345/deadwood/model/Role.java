package cs345.deadwood.model;

import cs345.deadwood.view.IModelObserver;

import java.util.ArrayList;
import java.util.List;

public class Role implements IRole, IModel {
    private final String name;
    private final String line;
    private final int level;
    private final IArea area;
    private Player player;
    private List<IModelObserver> observers = new ArrayList<>();
    private boolean isCardRole;

    public Role(String name, String line, int level, IArea area) {
        this.name = name;
        this.line = line;
        this.level = level;
        this.area = area;
        this.isCardRole = false;
    }

    public void setPlayer(Player player) {
        this.player = player;
        notifyObservers();
    }

    public boolean getIsCardRole() {
        return isCardRole;
    }

    public void setIsCardRole(boolean bool) {
        this.isCardRole = bool;
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


    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public String getLine() {
        return line;
    }

    @Override
    public IArea getArea() {
        return area;
    }
}
