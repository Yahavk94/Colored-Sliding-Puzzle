package search.algorithm;

import component.GeneratingOperator;
import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;
import search.data.IDAStarData;
import service.SearchService;

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
        IDAStarData idaStarData = new IDAStarData();
        idaStarData.setSearchLimit(root.calcF());
        while (idaStarData.hasFiniteSearchLimit()) {
            idaStarData.resetMinF();
            root.unmark();
            idaStarData.addInStackAndLoopAvoidance(root);
            while (idaStarData.hasNotEmptyStack()) {
                InformedDepthFirstNode node = idaStarData.popFromStack();
                if (node.isMarked()) {
                    idaStarData.removeFromLoopAvoidance(node);
                } else {
                    node.mark();
                    idaStarData.pushToStack(node);
                    Node targetNode = visitNeighbors(node, idaStarData);
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
     * @param node        the node being expanded.
     * @param idaStarData the data structure designed for IDA* algorithm.
     * @return The target node if found, null otherwise.
     */
    private Node visitNeighbors(InformedDepthFirstNode node, IDAStarData idaStarData) {
        for (GeneratingOperator generatingOperator : GeneratingOperator.values()) {
            InformedDepthFirstNode neighbor = SearchService.expandNode(node, generatingOperator);
            if (neighbor != null && processNeighbor(neighbor, idaStarData)) {
                return neighbor;
            }
        }

        return null;
    }

    /**
     * This method processes the provided neighbor and updates the stack and loop avoidance if necessary.
     *
     * @param neighbor    the neighbor being processed.
     * @param idaStarData the data structure designed for IDA* algorithm.
     * @return true if the neighbor represents the target, false otherwise.
     */
    private boolean processNeighbor(InformedDepthFirstNode neighbor, IDAStarData idaStarData) {
        neighbor.updateF();
        int neighborF = neighbor.getF();
        if (neighborF > idaStarData.getSearchLimit()) {
            idaStarData.tryReducingMinF(neighborF);
        } else {
            String neighborCode = neighbor.toString();
            String tag = SearchService.tagNeighbor(neighbor, neighborCode, idaStarData);
            if (POTENTIAL.equals(tag)) {
                idaStarData.addInStackAndLoopAvoidance(neighbor, neighborCode);
            } else {
                return TARGET.equals(tag);
            }
        }

        return false;
    }
}
