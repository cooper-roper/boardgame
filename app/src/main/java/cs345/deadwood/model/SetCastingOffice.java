package cs345.deadwood.model;

import java.util.ArrayList;
import java.util.List;

public class SetCastingOffice implements ISet {
    private final String name;
    private final IArea area;
    private final List<IArea> blankAreas;
    private final List<ISet> neighbors;
    private final List<String> neighborNames;
    private boolean visited = false;

    public SetCastingOffice(IArea area, List<IArea> blankAreas, List<String> neighborNames) {
        this.name = "Office";
        this.area = area;
        this.blankAreas = blankAreas;
        neighbors = new ArrayList<>();
        this.neighborNames = neighborNames;
    }




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


    

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<ISet> getNeighbors() {
        return neighbors;
    }

    @Override
    public void addNeighbor(ISet set) {
        neighbors.add(set);
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

}
