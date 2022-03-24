package cs345.deadwood.model;

import cs345.deadwood.view.IModelObserver;

public interface IRole extends IModel {


    void setPlayer(Player player);

    Player getPlayer();

    /**
     * @return Name of the role
     */
    String getName();

    /**
     * @return Level of the role
     */
    int getLevel();

    /**
     * @return Line of the role
     */
    String getLine();

    /**
     * @return Area of the role
     */
    IArea getArea();


    void registerObserver(IModelObserver ob);


    void removeObserver(IModelObserver ob);


    void notifyObservers();

    void setIsCardRole(boolean b);

    boolean getIsCardRole();
}
