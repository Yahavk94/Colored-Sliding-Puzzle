package search;

import component.GeneratingOperator;
import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;
import service.SearchService;

import java.util.*;

import static constants.SearchConstants.POTENTIAL;
import static constants.SearchConstants.TARGET;

/**
 * This class represents Depth-First Branch and Bound algorithm for searching in a state space.
 *
 * @author Yahav Karpel
 */
public class DFBnB implements Searchable {

    /**
     * This method performs DFBnB search starting from the provided root node.
     *
     * @param root the root node from which the search begins.
     * @return The target node if found, null otherwise.
     */
    @Override
    public Node search(InformedDepthFirstNode root) {
        Stack<InformedDepthFirstNode> stack = new Stack<>();
        Map<String, InformedDepthFirstNode> loopAvoidance = new HashMap<>();
        DFBnBData dfbnbData = new DFBnBData();
        root.unmark();
        stack.push(root);
        loopAvoidance.put(root.toString(), root);
        while (!stack.isEmpty()) {
            InformedDepthFirstNode node = stack.pop();
            if (node.isMarked()) {
                loopAvoidance.remove(node.toString());
            } else {
                node.mark();
                stack.push(node);
                visitNeighbors(node, stack, loopAvoidance, dfbnbData);
            }
        }

        return dfbnbData.getTargetNode();
    }

    /**
     * This method visits the neighbors of the provided node in the search space and processes them.
     *
     * @param node          the node being expanded.
     * @param stack         the DFS stack.
     * @param loopAvoidance the map that tracks visited nodes to prevent cycles during the search.
     * @param dfbnbData     the data object storing the current best result and limit.
     */
    private void visitNeighbors(InformedDepthFirstNode node, Stack<InformedDepthFirstNode> stack, Map<String, InformedDepthFirstNode> loopAvoidance, DFBnBData dfbnbData) {
        Queue<InformedDepthFirstNode> pq = createNeighbors(node, dfbnbData);
        Stack<InformedDepthFirstNode> candidates = extractCandidates(pq, loopAvoidance, dfbnbData);
        while (!candidates.isEmpty()) {
            InformedDepthFirstNode candidate = candidates.pop();
            stack.push(candidate);
            loopAvoidance.put(candidate.toString(), candidate);
        }
    }

    /**
     * This method creates neighbors from the provided node and returns them in a priority queue.
     *
     * @param node      the node from which neighbors are created.
     * @param dfbnbData the data object storing the current best result and limit.
     * @return A priority queue containing the created neighbors.
     */
    private Queue<InformedDepthFirstNode> createNeighbors(InformedDepthFirstNode node, DFBnBData dfbnbData) {
        Queue<InformedDepthFirstNode> pq = new PriorityQueue<>();
        for (GeneratingOperator generatingOperator : GeneratingOperator.values()) {
            InformedDepthFirstNode neighbor = SearchService.expandNode(node, generatingOperator);
            if (neighbor != null) {
                neighbor.updateF();
                if (neighbor.getF() < dfbnbData.getSearchLimit()) {
                    pq.add(neighbor);
                }
            }
        }

        return pq;
    }

    /**
     * This method extracts candidate nodes from the provided priority queue and returns them in a stack.
     *
     * @param pq            the priority queue containing neighbors.
     * @param loopAvoidance the map that tracks visited nodes to prevent cycles during the search.
     * @param dfbnbData     the data object storing the current best result and limit.
     * @return A stack of extracted candidate nodes.
     */
    private Stack<InformedDepthFirstNode> extractCandidates(Queue<InformedDepthFirstNode> pq, Map<String, InformedDepthFirstNode> loopAvoidance, DFBnBData dfbnbData) {
        Stack<InformedDepthFirstNode> candidates = new Stack<>();
        while (!pq.isEmpty()) {
            InformedDepthFirstNode neighbor = pq.remove();
            String tag = SearchService.tagNeighbor(neighbor, loopAvoidance);
            if (POTENTIAL.equals(tag)) {
                candidates.push(neighbor);
            } else if (TARGET.equals(tag)) {
                dfbnbData.setSearchLimit(neighbor.getF());
                dfbnbData.setTargetNode(neighbor);
                break;
            }
        }

        return candidates;
    }

    private static class DFBnBData {

        private Node targetNode;
        private int searchLimit = Integer.MAX_VALUE;

        public Node getTargetNode() {
            return targetNode;
        }

        public void setTargetNode(Node targetNode) {
            this.targetNode = targetNode;
        }

        public int getSearchLimit() {
            return searchLimit;
        }

        public void setSearchLimit(int searchLimit) {
            this.searchLimit = searchLimit;
        }
    }
}
