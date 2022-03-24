package cs345.deadwood.model;

import cs345.deadwood.view.BlankAreaView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SetTrailer implements ISet {
    private static SetTrailer instance;
    private final String name;
    private final IArea area;
    private final List<IArea> blankAreas;
    private final List<ISet> neighbors;
    private int num = 0;
    private boolean visited = false;

    public List<String> getNeighborNames() {
        return neighborNames;
    }

    private final List<String> neighborNames;

    public SetTrailer(IArea area, List<IArea> blankAreas, List<String> neighborNames) {
        this.name = "Trailer";
        this.area = area;
        this.blankAreas = blankAreas;
        neighbors = new ArrayList<>();
        this.neighborNames = neighborNames;
        instance = this;
    }


    public static SetTrailer getInstance() {
        return instance;
    }

//    public void addPlayer(Player player, JFrame board) {
//        IArea blank = blankAreas.get(num);
//        BlankArea blankArea = new BlankArea((BlankArea) blank);
//        new BlankAreaView(blankArea);
//        blankArea.setPlayer(player, board);
//        System.out.println("Adding Player: " + player.getPlayerNumber() + " to x: " + blankArea.getX() + " y: " + blankArea.getY());
//        num++;
//
//    }

    public void addPlayer(Player player) {
        visited = true;
        for(int i = 0; i < 8; i++) {
            BlankArea blankArea = (BlankArea) blankAreas.get(i);
            if (blankArea.getPlayer() == null) {
                blankArea.setPlayer(player);
                break;
            }
        }
    }


    public void removePlayer(Player player){
        for(int i = 0; i < 8; i++){
            BlankArea blankArea = (BlankArea) blankAreas.get(i);
            if(blankArea.getPlayer() == player){
                blankArea.setPlayer(null);
                break;
            }
        }
    }



    public boolean checkBlankForPlayer(Player player){
        for(int i = 0; i < 8; i++){
            BlankArea blankArea = (BlankArea) blankAreas.get(i);
            if(blankArea.getPlayer() == player){
                return true;
            }
        }
        return false;
    }


    public void addNeighbor(ISet set) {
        neighbors.add(set);
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
    public IArea getArea() {
        return area;
    }

    @Override
    public List<IArea> getBlankAreas() {
        return blankAreas;
    }

}
