package search.data;

import infrastructure.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * This class represents the common data structure used in Breadth-First algorithms.
 *
 * @author Yahav Karpel
 */
public abstract class BreadthFirstData {

    private final Queue<Node> queue;
    private final Map<String, Node> frontier = new HashMap<>();
    private final Map<String, Node> history = new HashMap<>();

    /**
     * This method constructs a new BreadthFirstData with the specified queue.
     *
     * @param queue the queue for storing nodes.
     */
    public BreadthFirstData(Queue<Node> queue) {
        this.queue = queue;
    }

    /**
     * This method checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean hasEmptyQueue() {
        return queue.isEmpty();
    }

    /**
     * This method checks if the queue is not empty.
     *
     * @return true if the queue is not empty, false otherwise.
     */
    public boolean hasNotEmptyQueue() {
        return !hasEmptyQueue();
    }

    /**
     * This method removes the front node from the queue.
     *
     * @return The front node of the queue.
     */
    public Node removeFromQueue() {
        return queue.remove();
    }

    /**
     * This method removes the provided node from the queue.
     *
     * @param node the node to be removed from the queue.
     * @return true if the node was successfully removed, false otherwise.
     */
    public boolean removeFromQueue(Node node) {
        return queue.remove(node);
    }

    /**
     * This method checks if the node with the provided code is in the frontier.
     *
     * @param code the unique code representing the node.
     * @return true if the node is in the frontier, false otherwise.
     */
    public boolean isInFrontier(String code) {
        return frontier.containsKey(code);
    }

    /**
     * This method checks if the node with the provided code is not in the frontier.
     *
     * @param code the unique code representing the node.
     * @return true if the node is not in the frontier, false otherwise.
     */
    public boolean isNotInFrontier(String code) {
        return !isInFrontier(code);
    }

    /**
     * This method returns the node with the provided code from the frontier.
     *
     * @param code the unique code representing the node.
     * @return The node from the frontier with the provided code.
     */
    public Node getFromFrontier(String code) {
        return frontier.get(code);
    }

    /**
     * This method checks if the node with the provided code is in the history.
     *
     * @param code the unique code representing the node.
     * @return true if the node is in the history, false otherwise.
     */
    public boolean isInHistory(String code) {
        return history.containsKey(code);
    }

    /**
     * This method checks if the node with the provided code is not in the history.
     *
     * @param code the unique code representing the node.
     * @return true if the node is not in the history, false otherwise.
     */
    public boolean isNotInHistory(String code) {
        return !isInHistory(code);
    }

    /**
     * This method checks if the node with the provided code is new.
     *
     * @param code the unique code representing the node.
     * @return true if the node is new, false otherwise.
     */
    public boolean isNew(String code) {
        return isNotInHistory(code) && isNotInFrontier(code);
    }

    /**
     * This method adds the provided node to both the queue and frontier using the provided code.
     *
     * @param node     the node to be added.
     * @param nodeCode the unique code representing the node.
     */
    public void addInQueueAndFrontier(Node node, String nodeCode) {
        queue.add(node);
        frontier.put(nodeCode, node);
    }

    /**
     * This method adds the provided node to both the queue and frontier.
     *
     * @param node the node to be added.
     */
    public void addInQueueAndFrontier(Node node) {
        addInQueueAndFrontier(node, node.toString());
    }

    /**
     * This method moves the provided node from the frontier to the history.
     *
     * @param node the node to be moved to the history from the frontier.
     */
    public void moveToHistoryFromFrontier(Node node) {
        String nodeCode = node.toString();
        frontier.remove(nodeCode);
        history.put(nodeCode, node);
    }
}
