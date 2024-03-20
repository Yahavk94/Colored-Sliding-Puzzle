package infrastructure;

import component.Piece;
import io.InputParser;
import service.InfrastructureService;

import java.util.ArrayList;
import java.util.List;

import static component.Dimension.BOARD_SIZE;

/**
 * This class represents a configuration of the puzzle.
 *
 * @author Yahav Karpel
 */
public abstract class State {

    private List<Piece> board;
    private int emptyPieceIndex;

    /**
     * This method constructs the input state.
     */
    public State() {
        setBoard(InputParser.parseInputBoard());
        setEmptyPieceIndex(InfrastructureService.findEmptyPieceIndex(board));
    }

    /**
     * This method constructs a deep copy of the specified state.
     *
     * @param state the state to copy.
     */
    public State(State state) {
        setBoard(new ArrayList<>(state.board));
        setEmptyPieceIndex(state.emptyPieceIndex);
    }

    /**
     * This method returns the board of the state.
     *
     * @return The board.
     */
    public List<Piece> getBoard() {
        return board;
    }

    /**
     * This method sets the board of the state.
     *
     * @param board the new board.
     */
    private void setBoard(List<Piece> board) {
        this.board = board;
    }

    /**
     * This method returns the index of the empty piece.
     *
     * @return The index of the empty piece.
     */
    public int getEmptyPieceIndex() {
        return emptyPieceIndex;
    }

    /**
     * This method sets the index of the empty piece.
     *
     * @param emptyPieceIndex the new index of the empty piece.
     */
    public void setEmptyPieceIndex(int emptyPieceIndex) {
        this.emptyPieceIndex = emptyPieceIndex;
    }

    /**
     * This method checks if the state represents the target pattern.
     *
     * @return true if the state represents the target pattern, false otherwise.
     */
    public boolean isTarget() {
        List<String> targetRawBoard = InfrastructureService.getTargetRawBoard();
        for (int i = 0; i < BOARD_SIZE; i++) {
            String rawData = board.get(i).getRawData();
            String targetRawData = targetRawBoard.get(i);
            if (!rawData.equals(targetRawData)) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method returns a string representation of the state.
     *
     * @return The string representation of the state.
     */
    @Override
    public String toString() {
        return board.toString();
    }
}
