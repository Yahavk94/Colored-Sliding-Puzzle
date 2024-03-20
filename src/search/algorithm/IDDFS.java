package search.algorithm;

import component.GeneratingOperator;
import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;
import search.data.DepthFirstData;
import service.SearchService;

/**
 * This class represents Iterative-Deepening Depth-First Search algorithm for searching in a state space.
 *
 * @author Yahav Karpel
 */
public class IDDFS implements Searchable {

    private static final int DEPTH_LIMIT = 40;

    /**
     * This method performs IDDFS starting from the provided root node.
     *
     * @param root the root node from which the search begins.
     * @return The target node if found, null otherwise.
     */
    @Override
    public Node search(InformedDepthFirstNode root) {
        DepthFirstData depthFirstData = new DepthFirstData();
        for (int i = 1; i < DEPTH_LIMIT; i++) {
            Node targetNode = depthLimitedSearch(root, i, depthFirstData);
            if (targetNode != null) {
                return targetNode;
            }
        }

        return null;
    }

    /**
     * This method performs depth-limited search from the provided node up to the remaining depth.
     *
     * @param node           the node being expanded.
     * @param depth          the remaining depth for the search.
     * @param depthFirstData the data structure designed for Depth-First algorithms.
     * @return The target node if found, or null if depth limit reached.
     */
    private Node depthLimitedSearch(Node node, int depth, DepthFirstData depthFirstData) {
        if (node.isTarget()) {
            return node;
        }

        if (depth > 0) {
            String nodeCode = node.toString();
            depthFirstData.putInLoopAvoidance(nodeCode, node);
            for (GeneratingOperator generatingOperator : GeneratingOperator.values()) {
                Node neighbor = SearchService.expandNode(node, generatingOperator);
                if (neighbor != null && depthFirstData.isNotInLoopAvoidance(neighbor)) {
                    Node targetNode = depthLimitedSearch(neighbor, depth - 1, depthFirstData);
                    if (targetNode != null) {
                        return targetNode;
                    }
                }
            }

            depthFirstData.removeFromLoopAvoidance(nodeCode);
        }

        return null;
    }
}
