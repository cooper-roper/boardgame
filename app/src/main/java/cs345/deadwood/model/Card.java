package cs345.deadwood.model;

import java.util.List;

public class Card implements ICard {
    private final String name;
    private final String imageName;
    private final int budget;
    private final int sceneNumber;
    private final String sceneDescription;
    private final List<IRole> roles;


    public Card(String name, String imageName, int budget, int sceneNumber, String sceneDescription, List<IRole> roles) {
        this.name = name;
        this.imageName = imageName;
        this.budget = budget;
        this.sceneNumber = sceneNumber;
        this.sceneDescription = sceneDescription;
        this.roles = roles;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImageName() {
        return imageName;
    }

    @Override
    public int getBudget() {
        return budget;
    }

    @Override
    public int getSceneNumber() {
        return sceneNumber;
    }

    @Override
    public List<IRole> getRoles() {
        return roles;
    }

    public String getSceneDescription() {
        return sceneDescription;
    }
}
