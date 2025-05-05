package service;

import component.GeneratingOperator;
import component.Piece;
import component.Point;
import exception.UnsupportedOperatorException;
import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;
import infrastructure.State;
import search.data.InformedDepthFirstData;

import java.util.Collections;

import static component.Dimension.NUM_OF_COLS;
import static constants.SearchConstants.*;

/**
 * This class provides a set of methods for assisting the search package.
 *
 * @author Yahav Karpel
 */
public class SearchService {

    private SearchService() {
        super();
    }

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
            if (nextEmptyPieceIndex != NOT_FOUND) {
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

        return NOT_FOUND;
    }

    /**
     * This method checks if a move is permitted based on the provided generating operator.
     *
     * @param emptyPieceIndex    the index of the current empty piece.
     * @param generatingOperator the generating operator representing the move direction.
     * @return true if the move is permitted, false otherwise.
     * @throws UnsupportedOperatorException if unsupported generating operator is encountered.
     */
    private static boolean isMovePermitted(int emptyPieceIndex, GeneratingOperator generatingOperator) {
        Point emptyPiecePoint = new Point(emptyPieceIndex);
        switch (generatingOperator) {
            case L:
                return emptyPiecePoint.isNotOnRightmostColumn();
            case U:
                return emptyPiecePoint.isNotOnBottomRow();
            case R:
                return emptyPiecePoint.isNotOnLeftmostColumn();
            case D:
                return emptyPiecePoint.isNotOnTopRow();
        }

        throw new UnsupportedOperatorException(generatingOperator);
    }

    /**
     * This method calculates the index of the next empty piece based on the provided empty piece index and generating operator.
     *
     * @param emptyPieceIndex    the index of the current empty piece.
     * @param generatingOperator the generating operator representing the move direction.
     * @return The index of the next empty piece.
     * @throws UnsupportedOperatorException if unsupported generating operator is encountered.
     */
    private static int calcNextEmptyPieceIndex(int emptyPieceIndex, GeneratingOperator generatingOperator) {
        switch (generatingOperator) {
            case L:
                return emptyPieceIndex + 1;
            case U:
                return emptyPieceIndex + NUM_OF_COLS;
            case R:
                return emptyPieceIndex - 1;
            case D:
                return emptyPieceIndex - NUM_OF_COLS;
        }

        throw new UnsupportedOperatorException(generatingOperator);
    }

    /**
     * This method updates the properties of the newly created node after the expansion.
     *
     * @param neighbor            the newly created node.
     * @param nextEmptyPieceIndex the index of the next empty piece.
     */
    private static void updateNeighbor(InformedDepthFirstNode neighbor, int nextEmptyPieceIndex) {
        Piece nextEmptyPiece = neighbor.getBoard().get(nextEmptyPieceIndex);
        neighbor.setWeight(neighbor.getWeight() + nextEmptyPiece.getColor().getCost());
        neighbor.setEdgeTagFromParent(nextEmptyPiece.getRawData() + neighbor.getGeneratingOperator());
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
     * @param neighbor               the neighbor being considered.
     * @param neighborCode           the unique code representing the neighbor.
     * @param informedDepthFirstData the data structure designed for informed DFS algorithms.
     * @return A string indicating the tag of the neighbor (TARGET, POTENTIAL or REDUNDANT).
     */
    public static String tagNeighbor(InformedDepthFirstNode neighbor, String neighborCode, InformedDepthFirstData informedDepthFirstData) {
        if (informedDepthFirstData.isNotInLoopAvoidance(neighborCode)) {
            if (neighbor.isTarget()) {
                return TARGET;
            }

            return POTENTIAL;
        }

        InformedDepthFirstNode node = (InformedDepthFirstNode)informedDepthFirstData.getFromLoopAvoidance(neighborCode);
        if (node.isNotMarked() && neighbor.getF() < node.getF()) {
            node.mark();
            return POTENTIAL;
        }

        return REDUNDANT;
    }

    /**
     * This method tags the provided neighbor based on loop avoidance and target conditions.
     *
     * @param neighbor               the neighbor being considered.
     * @param informedDepthFirstData the data structure designed for informed DFS algorithms.
     * @return A string indicating the tag of the neighbor (TARGET, POTENTIAL or REDUNDANT).
     */
    public static String tagNeighbor(InformedDepthFirstNode neighbor, InformedDepthFirstData informedDepthFirstData) {
        return tagNeighbor(neighbor, neighbor.toString(), informedDepthFirstData);
    }
}
