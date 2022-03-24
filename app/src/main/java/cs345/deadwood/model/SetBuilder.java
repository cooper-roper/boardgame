package cs345.deadwood.model;

import java.util.List;

public class SetBuilder {

    private String name;
    private IArea area;
    private List<IArea> blankAreas;
    private List<IRole> roles;
    private List<IArea> takes;
    private List<String> neighborNames;


    public SetCastingOffice GetCastingOffice() {
        SetCastingOffice set = new SetCastingOffice(area, blankAreas, neighborNames);
        return set;
    }

    public SetTrailer GetTrailer() {
        SetTrailer set = new SetTrailer(area, blankAreas, neighborNames);
        return set;
    }

    public SetScene GetSetScene() {
        SetScene set = new SetScene(name, area, blankAreas, roles, takes, neighborNames);
        return set;
    }

    public SetBuilder addName(String name){
        this.name = name;
        return this;
    }

    public SetBuilder addArea(IArea area){
        this.area = area;
        return this;
    }

    public SetBuilder addBlankAreas(List<IArea> blankAreas){
        this.blankAreas = blankAreas;
        return this;
    }

    public SetBuilder addRoles(List<IRole> roles){
        this.roles = roles;
        return this;
    }

    public SetBuilder addTakes(List<IArea> takes){
        this.takes = takes;
        return this;
    }

    public SetBuilder addNeighborNames(List<String> neighborNames){
        this.neighborNames = neighborNames;
        return this;
    }


}
