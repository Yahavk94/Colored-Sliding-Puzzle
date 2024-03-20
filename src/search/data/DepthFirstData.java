package search.data;

import infrastructure.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the common data structure used in Depth-First algorithms.
 *
 * @author Yahav Karpel
 */
public class DepthFirstData {

    private final Map<String, Node> loopAvoidance = new HashMap<>();

    /**
     * This method checks if the node with the provided code is in the loop avoidance.
     *
     * @param code the unique code representing the node.
     * @return true if the node is in the loop avoidance, false otherwise.
     */
    public boolean isInLoopAvoidance(String code) {
        return loopAvoidance.containsKey(code);
    }

    /**
     * This method checks if the node with the provided code is not in the loop avoidance.
     *
     * @param code the unique code representing the node.
     * @return true if the node is not in the loop avoidance, false otherwise.
     */
    public boolean isNotInLoopAvoidance(String code) {
        return !isInLoopAvoidance(code);
    }

    /**
     * This method checks if the provided node is not in the loop avoidance.
     *
     * @param node the node to check.
     * @return true if the node is not in the loop avoidance, false otherwise.
     */
    public boolean isNotInLoopAvoidance(Node node) {
        return isNotInLoopAvoidance(node.toString());
    }

    /**
     * This method returns the node from the loop avoidance using the provided code.
     *
     * @param code the unique code representing the node.
     * @return The node from the loop avoidance, or null if not present.
     */
    public Node getFromLoopAvoidance(String code) {
        return loopAvoidance.get(code);
    }

    /**
     * This method adds the node to the loop avoidance using the provided code.
     *
     * @param nodeCode the unique code representing the node.
     * @param node     the node to add to the loop avoidance.
     */
    public void putInLoopAvoidance(String nodeCode, Node node) {
        loopAvoidance.put(nodeCode, node);
    }

    /**
     * This method removes the node from the loop avoidance using the provided code.
     *
     * @param code the unique code representing the node.
     */
    public void removeFromLoopAvoidance(String code) {
        loopAvoidance.remove(code);
    }

    /**
     * This method removes the provided node from the loop avoidance.
     *
     * @param node the node to remove from the loop avoidance.
     */
    public void removeFromLoopAvoidance(Node node) {
        removeFromLoopAvoidance(node.toString());
    }
}
