package search.algorithm;

import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;

/**
 * This interface represents a search algorithm for searching in a state space.
 *
 * @author Yahav Karpel
 */
public interface Searchable {

    /**
     * This method performs a search starting from the provided root node.
     *
     * @param root the root node from which the search begins.
     * @return The target node if found, null otherwise.
     */
    Node search(InformedDepthFirstNode root);
}
