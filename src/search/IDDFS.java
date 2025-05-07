package search;

import component.GeneratingOperator;
import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;
import service.SearchService;

import java.util.HashMap;
import java.util.Map;

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
        for (int i = 1; i < DEPTH_LIMIT; i++) {
            Node targetNode = depthLimitedSearch(root, i, new HashMap<>());
            if (targetNode != null) {
                return targetNode;
            }
        }

        return null;
    }

    /**
     * This method performs depth-limited search from the provided node up to the remaining depth.
     *
     * @param node          the node being expanded.
     * @param depth         the remaining depth for the search.
     * @param loopAvoidance the map that tracks visited nodes to prevent cycles during the search.
     * @return The target node if found, or null if depth limit reached.
     */
    private Node depthLimitedSearch(Node node, int depth, Map<String, Node> loopAvoidance) {
        if (node.isTarget()) {
            return node;
        }

        if (depth > 0) {
            String nodeCode = node.toString();
            loopAvoidance.put(nodeCode, node);
            for (GeneratingOperator generatingOperator : GeneratingOperator.values()) {
                Node neighbor = SearchService.expandNode(node, generatingOperator);
                if (neighbor != null && !loopAvoidance.containsKey(neighbor.toString())) {
                    Node targetNode = depthLimitedSearch(neighbor, depth - 1, loopAvoidance);
                    if (targetNode != null) {
                        return targetNode;
                    }
                }
            }

            loopAvoidance.remove(nodeCode);
        }

        return null;
    }
}
