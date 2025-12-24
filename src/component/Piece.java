package component;

import service.ComponentService;

/**
 * This record represents a piece on the board.
 *
 * @author Yahav Karpel
 */
public record Piece(String rawData, int data, Color color) {

    /**
     * This method checks if the piece is empty.
     *
     * @return true if the piece is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this == ComponentService.emptyPiece;
    }

    /**
     * This method checks if the piece is fixed on the board.
     *
     * @return true if the piece is fixed on the board, false otherwise.
     */
    public boolean isFixedOnBoard() {
        return color == Color.GRAY;
    }

    /**
     * This method checks if the piece is not fixed on the board.
     *
     * @return true if the piece is not fixed on the board, false otherwise.
     */
    public boolean isNotFixedOnBoard() {
        return !isFixedOnBoard();
    }

    /**
     * This method returns a string representation of the piece.
     *
     * @return The string representation of the piece.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[EMPTY]";
        }

        return String.format("[%s, %s]", rawData, color);
    }
}
