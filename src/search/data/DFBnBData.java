package search.data;

import infrastructure.Node;

import static java.lang.Integer.MAX_VALUE;

/**
 * This class represents a data structure designed for DFBnB algorithm.
 *
 * @author Yahav Karpel
 */
public class DFBnBData extends InformedDepthFirstData {

    private Node targetNode;

    /**
     * This method resets the search limit to Integer.MAX_VALUE.
     */
    public void resetSearchLimit() {
        setSearchLimit(MAX_VALUE);
    }

    /**
     * This method returns the target node.
     *
     * @return The target node
     */
    public Node getTargetNode() {
        return targetNode;
    }

    /**
     * This method sets the target node.
     *
     * @param targetNode the new target node.
     */
    public void setTargetNode(Node targetNode) {
        this.targetNode = targetNode;
    }
}
