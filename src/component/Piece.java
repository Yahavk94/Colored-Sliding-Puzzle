package component;

import static component.Color.GRAY;
import static component.Color.NONE;
import static constants.ComponentConstants.EMPTY_PIECE_DATA;
import static constants.ComponentConstants.EMPTY_PIECE_RAW_DATA;

/**
 * This class represents a piece on the board.
 *
 * @author Yahav Karpel
 */
public class Piece {

    private String rawData;
    private int data;
    private Color color;

    /**
     * This method constructs a new empty piece.
     */
    public Piece() {
        setRawData(EMPTY_PIECE_RAW_DATA);
        setData(EMPTY_PIECE_DATA);
        setColor(NONE);
    }

    /**
     * This method constructs a new piece with the specified attributes.
     *
     * @param rawData the raw data associated with the new piece.
     * @param data    the numeric data associated with the new piece.
     * @param color   the color associated with the new piece.
     */
    public Piece(String rawData, int data, Color color) {
        setRawData(rawData);
        setData(data);
        setColor(color);
    }

    /**
     * This method returns the raw data of the piece.
     *
     * @return The raw data.
     */
    public String getRawData() {
        return rawData;
    }

    /**
     * This method sets the raw data of the piece.
     *
     * @param rawData the new raw data.
     */
    private void setRawData(String rawData) {
        this.rawData = rawData;
    }

    /**
     * This method returns the numeric data of the piece.
     *
     * @return The numeric data.
     */
    public int getData() {
        return data;
    }

    /**
     * This method sets the numeric data of the piece.
     *
     * @param data the new numeric data.
     */
    private void setData(int data) {
        this.data = data;
    }

    /**
     * This method returns the color of the piece.
     *
     * @return The color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * This method sets the color of the piece.
     *
     * @param color the new color.
     */
    private void setColor(Color color) {
        this.color = color;
    }

    /**
     * This method checks if the piece is empty.
     *
     * @return true if the piece is empty, false otherwise.
     */
    public boolean isEmpty() {
        return data == EMPTY_PIECE_DATA;
    }

    /**
     * This method checks if the piece is fixed on the board.
     *
     * @return true if the piece is fixed on the board, false otherwise.
     */
    public boolean isFixedOnBoard() {
        return color == GRAY;
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
            return "(EMPTY)";
        }

        return "(" + rawData + "," + color + ")";
    }
}
