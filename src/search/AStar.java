package search;

import component.GeneratingOperator;
import infrastructure.InformedDepthFirstNode;
import infrastructure.InformedNode;
import infrastructure.Node;
import service.SearchService;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class represents A* algorithm for searching in a state space.
 *
 * @author Yahav Karpel
 */
public class AStar implements Searchable {

    /**
     * This method performs A* search starting from the provided root node.
     *
     * @param root the root node from which the search begins.
     * @return The target node if found, null otherwise.
     */
    @Override
    public Node search(InformedDepthFirstNode root) {
        Queue<InformedNode> pq = new PriorityQueue<>();
        Map<String, InformedNode> frontier = new HashMap<>();
        Map<String, InformedNode> history = new HashMap<>();
        pq.add(root);
        frontier.put(root.toString(), root);
        while (!pq.isEmpty()) {
            InformedNode node = pq.remove();
            if (node.isTarget()) {
                return node;
            }

            String nodeCode = node.toString();
            frontier.remove(nodeCode);
            history.put(nodeCode, node);
            visitNeighbors(node, pq, frontier, history);
        }

        return null;
    }

    /**
     * This method visits the neighbors of the provided node in the search space and processes them.
     *
     * @param node     the node being expanded.
     * @param pq       the queue that holds nodes for processing based on priority.
     * @param frontier the map containing discovered nodes that have not yet been expanded.
     * @param history  the map containing nodes that have already been expanded.
     */
    private void visitNeighbors(Node node, Queue<InformedNode> pq, Map<String, InformedNode> frontier, Map<String, InformedNode> history) {
        for (GeneratingOperator generatingOperator : GeneratingOperator.values()) {
            InformedNode neighbor = SearchService.expandNode(node, generatingOperator);
            if (neighbor != null) {
                processNeighbor(neighbor, pq, frontier, history);
            }
        }
    }

    /**
     * This method processes the provided neighbor and updates the queue and frontier if necessary.
     *
     * @param neighbor the neighbor being processed.
     * @param pq       the queue that holds nodes for processing based on priority.
     * @param frontier the map containing discovered nodes that have not yet been expanded.
     * @param history  the map containing nodes that have already been expanded.
     */
    private void processNeighbor(InformedNode neighbor, Queue<InformedNode> pq, Map<String, InformedNode> frontier, Map<String, InformedNode> history) {
        String neighborCode = neighbor.toString();
        if (!history.containsKey(neighborCode)) {
            neighbor.updateF();
            if (isPromisingCandidate(neighbor, neighborCode, pq, frontier)) {
                pq.add(neighbor);
                frontier.put(neighborCode, neighbor);
            }
        }
    }

    /**
     * This method checks if the provided neighbor is a promising candidate, either as the target
     * or with reduced cost to the target.
     *
     * @param neighbor     the neighbor being considered.
     * @param neighborCode the unique code representing the neighbor.
     * @param pq           the queue that holds nodes for processing based on priority.
     * @param frontier     the map containing discovered nodes that have not yet been expanded.
     * @return true if the neighbor is a promising candidate, false otherwise.
     */
    private boolean isPromisingCandidate(InformedNode neighbor, String neighborCode, Queue<InformedNode> pq, Map<String, InformedNode> frontier) {
        if (!frontier.containsKey(neighborCode)) {
            return true;
        }

        InformedNode node = frontier.get(neighborCode);
        if (neighbor.getF() < node.getF()) {
            return pq.remove(node);
        }

        return false;
    }
}
