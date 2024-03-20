package search.algorithm;

import component.GeneratingOperator;
import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;
import search.data.BFSData;
import service.SearchService;

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
        BFSData bfsData = new BFSData();
        bfsData.addInQueueAndFrontier(root);
        while (bfsData.hasNotEmptyQueue()) {
            Node node = bfsData.removeFromQueue();
            bfsData.moveToHistoryFromFrontier(node);
            Node targetNode = visitNeighbors(node, bfsData);
            if (targetNode != null) {
                return targetNode;
            }
        }

        return null;
    }

    /**
     * This method visits the neighbors of the provided node in the search space.
     *
     * @param node    the node being expanded.
     * @param bfsData the data structure designed for BFS algorithm.
     * @return The target node if found, null otherwise.
     */
    private Node visitNeighbors(Node node, BFSData bfsData) {
        for (GeneratingOperator generatingOperator : GeneratingOperator.values()) {
            Node neighbor = SearchService.expandNode(node, generatingOperator);
            if (neighbor != null && processNeighbor(neighbor, bfsData)) {
                return neighbor;
            }
        }

        return null;
    }

    /**
     * This method processes the provided neighbor and updates the queue and frontier if necessary.
     *
     * @param neighbor the neighbor being processed.
     * @param bfsData  the data structure designed for BFS algorithm.
     * @return true if the neighbor represents the target, false otherwise.
     */
    private boolean processNeighbor(Node neighbor, BFSData bfsData) {
        String neighborCode = neighbor.toString();
        if (bfsData.isNew(neighborCode)) {
            if (neighbor.isTarget()) {
                return true;
            }

            bfsData.addInQueueAndFrontier(neighbor, neighborCode);
        }

        return false;
    }
}
