package search.algorithm;

import component.GeneratingOperator;
import infrastructure.InformedDepthFirstNode;
import infrastructure.InformedNode;
import infrastructure.Node;
import search.data.AStarData;
import service.SearchService;

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
        AStarData aStarData = new AStarData();
        aStarData.addInQueueAndFrontier(root);
        while (aStarData.hasNotEmptyQueue()) {
            Node node = aStarData.removeFromQueue();
            if (node.isTarget()) {
                return node;
            }

            aStarData.moveToHistoryFromFrontier(node);
            visitNeighbors(node, aStarData);
        }

        return null;
    }

    /**
     * This method visits the neighbors of the provided node in the search space and processes them.
     *
     * @param node      the node being expanded.
     * @param aStarData the data structure designed for A* algorithm.
     */
    private void visitNeighbors(Node node, AStarData aStarData) {
        for (GeneratingOperator generatingOperator : GeneratingOperator.values()) {
            InformedNode neighbor = SearchService.expandNode(node, generatingOperator);
            if (neighbor != null) {
                processNeighbor(neighbor, aStarData);
            }
        }
    }

    /**
     * This method processes the provided neighbor and updates the queue and frontier if necessary.
     *
     * @param neighbor  the neighbor being processed.
     * @param aStarData the data structure designed for A* algorithm.
     */
    private void processNeighbor(InformedNode neighbor, AStarData aStarData) {
        String neighborCode = neighbor.toString();
        if (aStarData.isNotInHistory(neighborCode)) {
            neighbor.updateF();
            if (isPromisingCandidate(neighbor, neighborCode, aStarData)) {
                aStarData.addInQueueAndFrontier(neighbor, neighborCode);
            }
        }
    }

    /**
     * This method checks if the provided neighbor is a promising candidate, either as the target
     * or with reduced cost to the target.
     *
     * @param neighbor     the neighbor being considered.
     * @param neighborCode the unique code representing the neighbor.
     * @param aStarData    the data structure designed for A* algorithm.
     * @return true if the neighbor is a promising candidate, false otherwise.
     */
    private boolean isPromisingCandidate(InformedNode neighbor, String neighborCode, AStarData aStarData) {
        if (aStarData.isNotInFrontier(neighborCode)) {
            return true;
        }

        Node node = aStarData.getFromFrontier(neighborCode);
        if (neighbor.getF() < ((InformedNode)node).getF()) {
            return aStarData.removeFromQueue(node);
        }

        return false;
    }
}
