package service;

import component.Dimension;
import component.GeneratingOperator;
import component.Piece;
import component.Point;
import constants.SearchConstants;
import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;
import infrastructure.State;

import java.util.Collections;
import java.util.Map;

/**
 * This class provides a set of methods for assisting the search package.
 *
 * @author Yahav Karpel
 */
public class SearchService {

    /**
     * This method expands the provided node based on the generating operator.
     *
     * @param node               the node being expanded.
     * @param generatingOperator the generating operator for the expansion.
     * @return The node resulting from the expansion, or null if expansion is not possible.
     */
    public static InformedDepthFirstNode expandNode(Node node, GeneratingOperator generatingOperator) {
        if (generatingOperator.isNotOpposite(node.getGeneratingOperator())) {
            int nextEmptyPieceIndex = findNextEmptyPieceIndex(node, generatingOperator);
            if (nextEmptyPieceIndex != SearchConstants.NOT_FOUND) {
                InformedDepthFirstNode neighbor = new InformedDepthFirstNode(node, generatingOperator);
                updateNeighbor(neighbor, nextEmptyPieceIndex);
                return neighbor;
            }
        }

        return null;
    }

    /**
     * This method finds the index of the next empty piece based on the provided state and generating operator.
     *
     * @param state              a configuration of the puzzle.
     * @param generatingOperator the generating operator representing the move direction.
     * @return The index of the next empty piece, or NOT_FOUND if not found.
     */
    private static int findNextEmptyPieceIndex(State state, GeneratingOperator generatingOperator) {
        int emptyPieceIndex = state.getEmptyPieceIndex();
        if (isMovePermitted(emptyPieceIndex, generatingOperator)) {
            int nextEmptyPieceIndex = calcNextEmptyPieceIndex(emptyPieceIndex, generatingOperator);
            if (state.getBoard().get(nextEmptyPieceIndex).isNotFixedOnBoard()) {
                return nextEmptyPieceIndex;
            }
        }

        return SearchConstants.NOT_FOUND;
    }

    /**
     * This method checks if a move is permitted based on the provided generating operator.
     *
     * @param emptyPieceIndex    the index of the current empty piece.
     * @param generatingOperator the generating operator representing the move direction.
     * @return true if the move is permitted, false otherwise.
     */
    private static boolean isMovePermitted(int emptyPieceIndex, GeneratingOperator generatingOperator) {
        Point emptyPiecePoint = new Point(emptyPieceIndex);
        return switch (generatingOperator) {
            case L -> emptyPiecePoint.isNotOnRightmostColumn();
            case U -> emptyPiecePoint.isNotOnBottomRow();
            case R -> emptyPiecePoint.isNotOnLeftmostColumn();
            case D -> emptyPiecePoint.isNotOnTopRow();
        };
    }

    /**
     * This method calculates the index of the next empty piece based on the provided empty piece index and generating operator.
     *
     * @param emptyPieceIndex    the index of the current empty piece.
     * @param generatingOperator the generating operator representing the move direction.
     * @return The index of the next empty piece.
     */
    private static int calcNextEmptyPieceIndex(int emptyPieceIndex, GeneratingOperator generatingOperator) {
        return switch (generatingOperator) {
            case L -> emptyPieceIndex + 1;
            case U -> emptyPieceIndex + Dimension.NUM_OF_COLS;
            case R -> emptyPieceIndex - 1;
            case D -> emptyPieceIndex - Dimension.NUM_OF_COLS;
        };
    }

    /**
     * This method updates the properties of the newly created node after the expansion.
     *
     * @param neighbor            the newly created node.
     * @param nextEmptyPieceIndex the index of the next empty piece.
     */
    private static void updateNeighbor(InformedDepthFirstNode neighbor, int nextEmptyPieceIndex) {
        Piece nextEmptyPiece = neighbor.getBoard().get(nextEmptyPieceIndex);
        neighbor.setWeight(neighbor.getWeight() + nextEmptyPiece.color().cost);
        neighbor.setEdgeTagFromParent(nextEmptyPiece.rawData() + neighbor.getGeneratingOperator());
        performSwap(neighbor, nextEmptyPieceIndex);
    }

    /**
     * This method performs the swap of the empty piece with the next empty piece.
     *
     * @param state               a configuration of the puzzle.
     * @param nextEmptyPieceIndex the index of the next empty piece.
     */
    private static void performSwap(State state, int nextEmptyPieceIndex) {
        int emptyPieceIndex = state.getEmptyPieceIndex();
        Collections.swap(state.getBoard(), emptyPieceIndex, nextEmptyPieceIndex);
        state.setEmptyPieceIndex(nextEmptyPieceIndex);
    }

    /**
     * This method tags the provided neighbor based on loop avoidance and target conditions.
     *
     * @param neighbor      the neighbor being considered.
     * @param neighborCode  the unique code representing the neighbor.
     * @param loopAvoidance the map that tracks visited nodes to prevent cycles during the search.
     * @return A string indicating the tag of the neighbor (TARGET, POTENTIAL or REDUNDANT).
     */
    public static String tagNeighbor(InformedDepthFirstNode neighbor, String neighborCode, Map<String, InformedDepthFirstNode> loopAvoidance) {
        if (!loopAvoidance.containsKey(neighborCode)) {
            if (neighbor.isTarget()) {
                return SearchConstants.TARGET;
            }

            return SearchConstants.POTENTIAL;
        }

        InformedDepthFirstNode node = loopAvoidance.get(neighborCode);
        if (node.isNotMarked() && neighbor.getF() < node.getF()) {
            node.mark();
            return SearchConstants.POTENTIAL;
        }

        return SearchConstants.REDUNDANT;
    }

    /**
     * This method tags the provided neighbor based on loop avoidance and target conditions.
     *
     * @param neighbor      the neighbor being considered.
     * @param loopAvoidance the map that tracks visited nodes to prevent cycles during the search.
     * @return A string indicating the tag of the neighbor (TARGET, POTENTIAL or REDUNDANT).
     */
    public static String tagNeighbor(InformedDepthFirstNode neighbor, Map<String, InformedDepthFirstNode> loopAvoidance) {
        return tagNeighbor(neighbor, neighbor.toString(), loopAvoidance);
    }

    private SearchService() {
    }
}
