package search.algorithm;

import component.GeneratingOperator;
import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;
import search.data.DFBnBData;
import service.SearchService;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

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
        DFBnBData dfbnbData = new DFBnBData();
        dfbnbData.resetSearchLimit();
        root.unmark();
        dfbnbData.addInStackAndLoopAvoidance(root);
        while (dfbnbData.hasNotEmptyStack()) {
            InformedDepthFirstNode node = dfbnbData.popFromStack();
            if (node.isMarked()) {
                dfbnbData.removeFromLoopAvoidance(node);
            } else {
                node.mark();
                dfbnbData.pushToStack(node);
                visitNeighbors(node, dfbnbData);
            }
        }

        return dfbnbData.getTargetNode();
    }

    /**
     * This method visits the neighbors of the provided node in the search space and processes them.
     *
     * @param node      the node being expanded.
     * @param dfbnbData the data structure designed for DFBnB algorithm.
     */
    private void visitNeighbors(InformedDepthFirstNode node, DFBnBData dfbnbData) {
        Queue<InformedDepthFirstNode> pq = createNeighbors(node, dfbnbData);
        Stack<InformedDepthFirstNode> candidates = extractCandidates(pq, dfbnbData);
        while (!candidates.isEmpty()) {
            dfbnbData.addInStackAndLoopAvoidance(candidates.pop());
        }
    }

    /**
     * This method creates neighbors from the provided node and returns them in a priority queue.
     *
     * @param node      the node from which neighbors are created.
     * @param dfbnbData the data structure designed for DFBnB algorithm.
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
     * @param pq        the priority queue containing neighbors.
     * @param dfbnbData the data structure designed for DFBnB algorithm.
     * @return A stack of extracted candidate nodes.
     */
    private Stack<InformedDepthFirstNode> extractCandidates(Queue<InformedDepthFirstNode> pq, DFBnBData dfbnbData) {
        Stack<InformedDepthFirstNode> candidates = new Stack<>();
        while (!pq.isEmpty()) {
            InformedDepthFirstNode neighbor = pq.remove();
            String tag = SearchService.tagNeighbor(neighbor, dfbnbData);
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
}
