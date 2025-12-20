package service;

import component.Color;
import component.Dimension;
import component.Piece;
import component.Point;
import constants.ComponentConstants;
import exception.EmptyPieceNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a set of methods for assisting the infrastructure package.
 *
 * @author Yahav Karpel
 */
public class InfrastructureService {

    public static final List<String> targetRawBoard = createTargetRawBoard();

    /**
     * This method creates the target raw board.
     *
     * @return The created target raw board.
     */
    private static List<String> createTargetRawBoard() {
        List<String> targetRawBoard = new ArrayList<>();
        for (int i = 1; i < Dimension.BOARD_SIZE; i++) {
            targetRawBoard.add(Integer.toString(i));
        }

        targetRawBoard.add(ComponentConstants.EMPTY_PIECE_RAW_DATA);
        return targetRawBoard;
    }

    /**
     * This method finds the index of the empty piece on the provided board.
     *
     * @param board a configuration of the pieces.
     * @return The index of the empty piece.
     * @throws EmptyPieceNotFoundException if the empty piece is not found on the board.
     */
    public static int findEmptyPieceIndex(List<Piece> board) {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).isEmpty()) {
                return i;
            }
        }

        throw new EmptyPieceNotFoundException();
    }

    /**
     * This method calculates the heuristic evaluation based on the provided board.
     *
     * @param board a configuration of the pieces.
     * @return The calculated heuristic evaluation.
     */
    public static int h(List<Piece> board) {
        int sum = 0;
        for (int i = 0; i < board.size(); i++) {
            Piece piece = board.get(i);
            Color color = piece.color();
            if (color.hasPositiveCost()) {
                sum += color.cost * calcManhattanDistance(piece.data(), i);
            }
        }

        return sum;
    }

    /**
     * This method calculates the Manhattan distance between the provided arguments.
     *
     * @param arg1 the first argument.
     * @param arg2 the second argument.
     * @return The Manhattan distance between the first and second arguments.
     */
    private static int calcManhattanDistance(int arg1, int arg2) {
        Point p1 = new Point(arg1);
        Point p2 = new Point(arg2);
        return p1.calcAbsRowDistance(p2) + p1.calcAbsColumnDistance(p2);
    }

    private InfrastructureService() {
    }
}
