package search.data;

import java.util.PriorityQueue;

/**
 * This class represents a data structure designed for A* algorithm.
 *
 * @author Yahav Karpel
 */
public class AStarData extends BreadthFirstData {

    /**
     * This method constructs a new AStarData.
     */
    public AStarData() {
        super(new PriorityQueue<>());
    }
}
