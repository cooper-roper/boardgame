package cs345.deadwood.model;

import cs345.deadwood.view.IModelObserver;

import java.util.ArrayList;
import java.util.List;

public class SetScene implements ISetScene, IModel {

    private final String name;
    private final IArea area;
    private final List<IArea> blankAreas;
    private final List<IRole> roles;
    private final List<IArea> takes;
    private final List<ISet> neighbors;
    private final List<String> neighborNames;
    private ICard sceneCard;
    private int num = 0;
    private boolean visited = false;
    private int rehearse = 0;
    private int actsCounter;
    private List<IModelObserver> observers = new ArrayList<>();
    private boolean hasWrapped;
    private boolean nextDay;

    public SetScene(String name, IArea area, List<IArea> blankAreas, List<IRole> roles, List<IArea> takes, List<String> neighborNames) {
        this.name = name;
        this.area = area;
        this.blankAreas = blankAreas;
        this.roles = roles;
        this.takes = takes;
        this.neighborNames = neighborNames;
        neighbors = new ArrayList<>();
        this.actsCounter = takes.size();
        this.hasWrapped = false;
        this.nextDay = false;
    }

    public void setNextDay(boolean b) {
        this.nextDay = b;
        // notifyObservers();
    }

    public boolean getNextDay() {
        return nextDay;
    }

    public void setHasWrapped(boolean bool) {
        this.hasWrapped = bool;
    }

    public boolean getHasWrapped () {
        return this.hasWrapped;
    }

    public void resetActs() {
        this.actsCounter = takes.size();
    }

    public void numberOfActs(){
        this.actsCounter--;
    }

    public int getNumberOfActs() {
        return actsCounter;
    }

    public void addPlayer(Player player) {
        notifyObservers();
        visited = true;
        for(int i = 0; i < 8; i++) {
            BlankArea blankArea = (BlankArea) blankAreas.get(i);
            if (blankArea.getPlayer() == null) {
                blankArea.setPlayer(player);
                break;
            }
        }
    }

    @Override
    public boolean getVisited(){
        notifyObservers();
        return visited;
    }

    public void removePlayer(Player player){
        notifyObservers();
        for(int i = 0; i < 8; i++){
            BlankArea blankArea = (BlankArea) blankAreas.get(i);
            if(blankArea.getPlayer() == player){
                blankArea.setPlayer(null);
                break;
            }
        }
    }

    public boolean checkBlankForPlayer(Player player){
        notifyObservers();
        for(int i = 0; i < 8; i++){
            BlankArea blankArea = (BlankArea) blankAreas.get(i);
            if(blankArea.getPlayer() == player){
                return true;
            }
        }
        return false;
    }



    public void rehearse(){
        if(this.rehearse <= 3)
            this.rehearse++;
    }

    public int getRehearse(){
        return this.rehearse;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<ISet> getNeighbors() {
        return neighbors;
    }

    @Override
    public void addNeighbor(ISet neighbor) {
        if (neighbor == null) {
            throw new RuntimeException("Neighbor set should not be null");
        }
        neighbors.add(neighbor);
    }

    public List<String> getNeighborNames() {
        return neighborNames;
    }

    @Override
    public IArea getArea() {
        return area;
    }

    @Override
    public List<IArea> getBlankAreas() {
        return blankAreas;
    }

    @Override
    public List<IArea> getTakes() {
        return takes;
    }

    @Override
    public List<IRole> getRoles() {
        return roles;
    }

    @Override
    public ICard getSceneCard() {
        return sceneCard;
    }

    public void setSceneCard(ICard sceneCard) {
        this.sceneCard = sceneCard;
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
