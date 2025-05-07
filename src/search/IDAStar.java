package search;

import component.GeneratingOperator;
import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;
import service.SearchService;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static constants.SearchConstants.POTENTIAL;
import static constants.SearchConstants.TARGET;

/**
 * This class represents Iterative-Deepening A* algorithm for searching in a state space.
 *
 * @author Yahav Karpel
 */
public class IDAStar implements Searchable {

    /**
     * This method performs IDA* search starting from the provided root node.
     *
     * @param root the root node from which the search begins.
     * @return The target node if found, null otherwise.
     */
    @Override
    public Node search(InformedDepthFirstNode root) {
        Stack<InformedDepthFirstNode> stack = new Stack<>();
        Map<String, InformedDepthFirstNode> loopAvoidance = new HashMap<>();
        IDAStarData idaStarData = new IDAStarData();
        idaStarData.setSearchLimit(root.calcF());
        while (idaStarData.getSearchLimit() < Integer.MAX_VALUE) {
            idaStarData.resetMinF();
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
                    Node targetNode = visitNeighbors(node, stack, loopAvoidance, idaStarData);
                    if (targetNode != null) {
                        return targetNode;
                    }
                }
            }

            idaStarData.updateSearchLimit();
        }

        return null;
    }

    /**
     * This method visits the neighbors of the provided node in the search space.
     *
     * @param node          the node being expanded.
     * @param stack         the DFS stack.
     * @param loopAvoidance the map that tracks visited nodes to prevent cycles during the search.
     * @param idaStarData   the data object storing the current search limit and minF tracking.
     * @return The target node if found, null otherwise.
     */
    private Node visitNeighbors(InformedDepthFirstNode node, Stack<InformedDepthFirstNode> stack, Map<String, InformedDepthFirstNode> loopAvoidance, IDAStarData idaStarData) {
        for (GeneratingOperator generatingOperator : GeneratingOperator.values()) {
            InformedDepthFirstNode neighbor = SearchService.expandNode(node, generatingOperator);
            if (neighbor != null && processNeighbor(neighbor, stack, loopAvoidance, idaStarData)) {
                return neighbor;
            }
        }

        return null;
    }

    /**
     * This method processes the provided neighbor and updates the stack and loop avoidance if necessary.
     *
     * @param neighbor      the neighbor being processed.
     * @param stack         the DFS stack.
     * @param loopAvoidance the map that tracks visited nodes to prevent cycles during the search.
     * @param idaStarData   the data object storing the current search limit and minF tracking.
     * @return true if the neighbor represents the target, false otherwise.
     */
    private boolean processNeighbor(InformedDepthFirstNode neighbor, Stack<InformedDepthFirstNode> stack, Map<String, InformedDepthFirstNode> loopAvoidance, IDAStarData idaStarData) {
        neighbor.updateF();
        int neighborF = neighbor.getF();
        if (neighborF > idaStarData.getSearchLimit()) {
            idaStarData.tryReducingMinF(neighborF);
        } else {
            String neighborCode = neighbor.toString();
            String tag = SearchService.tagNeighbor(neighbor, neighborCode, loopAvoidance);
            if (POTENTIAL.equals(tag)) {
                stack.push(neighbor);
                loopAvoidance.put(neighborCode, neighbor);
            } else {
                return TARGET.equals(tag);
            }
        }

        return false;
    }

    private static class IDAStarData {

        private int searchLimit;
        private int minF;

        public void resetMinF() {
            setMinF(Integer.MAX_VALUE);
        }

        public void tryReducingMinF(int f) {
            if (f < minF) {
                setMinF(f);
            }
        }

        public void updateSearchLimit() {
            setSearchLimit(minF);
        }

        public int getSearchLimit() {
            return searchLimit;
        }

        public void setSearchLimit(int searchLimit) {
            this.searchLimit = searchLimit;
        }

        public void setMinF(int minF) {
            this.minF = minF;
        }
    }
}
