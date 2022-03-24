package cs345.deadwood.model;

import java.util.List;

/**
 * Interface representing a basic set on the board. Every set on the board should implement this interface.
 */
public interface ISet {

    /**
     * @return Name of the set
     */
    String getName();

    /**
     * @return List of neighbors of this set
     */
    List<ISet> getNeighbors();

    void addNeighbor(ISet neighbor);

    List<String> getNeighborNames();

    /**
     * @return Area of this set
     */
    IArea getArea();

    /**
     * Returns the list of blank areas on this set. The list should be sorted by the blank number. That is, the first item in the list should be blank number 1, second being blank number 2, and so forth.
     *
     * @return List of blank areas on this set, sorted by number.
     */
    List<IArea> getBlankAreas();

    void addPlayer(Player player);

    void removePlayer(Player player);

    boolean checkBlankForPlayer(Player player);

}
