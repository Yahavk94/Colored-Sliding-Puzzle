package search;

import component.GeneratingOperator;
import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;
import service.SearchService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * This class represents Breadth-First Search algorithm for searching in a state space.
 *
 * @author Yahav Karpel
 */
public class BFS implements Searchable {

    /**
     * This method performs BFS starting from the provided root node.
     *
     * @param root the root node from which the search begins.
     * @return The target node if found, null otherwise.
     */
    @Override
    public Node search(InformedDepthFirstNode root) {
        Queue<Node> queue = new LinkedList<>();
        Map<String, Node> frontier = new HashMap<>();
        Map<String, Node> history = new HashMap<>();
        queue.add(root);
        frontier.put(root.toString(), root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            String nodeCode = node.toString();
            frontier.remove(nodeCode);
            history.put(nodeCode, node);
            Node targetNode = visitNeighbors(node, queue, frontier, history);
            if (targetNode != null) {
                return targetNode;
            }
        }

        return null;
    }

    /**
     * This method visits the neighbors of the provided node in the search space.
     *
     * @param node     the node being expanded.
     * @param queue    the queue that holds nodes to be processed.
     * @param frontier the map containing discovered nodes that have not yet been expanded.
     * @param history  the map containing nodes that have already been expanded.
     * @return The target node if found, null otherwise.
     */
    private Node visitNeighbors(Node node, Queue<Node> queue, Map<String, Node> frontier, Map<String, Node> history) {
        for (GeneratingOperator generatingOperator : GeneratingOperator.values()) {
            Node neighbor = SearchService.expandNode(node, generatingOperator);
            if (neighbor != null && processNeighbor(neighbor, queue, frontier, history)) {
                return neighbor;
            }
        }

        return null;
    }

    /**
     * This method processes the provided neighbor and updates the queue and frontier if necessary.
     *
     * @param neighbor the neighbor being processed.
     * @param queue    the queue that holds nodes to be processed.
     * @param frontier the map containing discovered nodes that have not yet been expanded.
     * @param history  the map containing nodes that have already been expanded.
     * @return true if the neighbor represents the target, false otherwise.
     */
    private boolean processNeighbor(Node neighbor, Queue<Node> queue, Map<String, Node> frontier, Map<String, Node> history) {
        String neighborCode = neighbor.toString();
        if (!history.containsKey(neighborCode) && !frontier.containsKey(neighborCode)) {
            if (neighbor.isTarget()) {
                return true;
            }

            queue.add(neighbor);
            frontier.put(neighborCode, neighbor);
        }

        return false;
    }
}
