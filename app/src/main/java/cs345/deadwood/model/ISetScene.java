package cs345.deadwood.model;

import cs345.deadwood.view.IModelObserver;

import java.util.List;

/**
 * Interface that every scene set should implement.
 */
public interface ISetScene extends ISet, IModel {


    void resetActs();

    void numberOfActs();

    int getNumberOfActs();

    /**
     * Returns the list of takes on this set. The list should be sorted by the take number. That is, the first item in the list should be take number 1, second being take number 2, and so forth.
     *
     * @return List of takes for this set, sorted by number.
     */
    List<IArea> getTakes();

    /**
     * @return List of roles on this set. These would be off-card roles.
     */
    List<IRole> getRoles();

    /**
     * @return Return the scene card for this set or null if no card has been assigned yet.
     */
    ICard getSceneCard();

    void setSceneCard(ICard card);
    

    void rehearse();

    int getRehearse();

    boolean getVisited();

    void setNextDay(boolean b);

    void setHasWrapped(boolean b);

    boolean getHasWrapped();

    boolean getNextDay();
}
