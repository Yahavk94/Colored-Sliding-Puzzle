package component;

import constants.ComponentConstants;
import service.PointService;

/**
 * This class represents a point with row and column coordinates.
 *
 * @author Yahav Karpel
 */
public class Point {

    private final int r;
    private final int c;

    /**
     * This method constructs a new point based on the specified 1D index.
     *
     * @param index the 1D index representing the position on the board.
     */
    public Point(int index) {
        r = PointService.toRow(index);
        c = PointService.toColumn(index);
    }

    /**
     * This method checks if the point is on the top row.
     *
     * @return true if the point is on the top row, false otherwise.
     */
    public boolean isOnTopRow() {
        return r == ComponentConstants.TOP_ROW;
    }

    /**
     * This method checks if the point is not on the top row.
     *
     * @return true if the point is not on the top row, false otherwise.
     */
    public boolean isNotOnTopRow() {
        return !isOnTopRow();
    }

    /**
     * This method checks if the point is on the bottom row.
     *
     * @return true if the point is on the bottom row, false otherwise.
     */
    public boolean isOnBottomRow() {
        return r == ComponentConstants.BOTTOM_ROW;
    }

    /**
     * This method checks if the point is not on the bottom row.
     *
     * @return true if the point is not on the bottom row, false otherwise.
     */
    public boolean isNotOnBottomRow() {
        return !isOnBottomRow();
    }

    /**
     * This method checks if the point is on the leftmost column.
     *
     * @return true if the point is on the leftmost column, false otherwise.
     */
    public boolean isOnLeftmostColumn() {
        return c == ComponentConstants.LEFTMOST_COLUMN;
    }

    /**
     * This method checks if the point is not on the leftmost column.
     *
     * @return true if the point is not on the leftmost column, false otherwise.
     */
    public boolean isNotOnLeftmostColumn() {
        return !isOnLeftmostColumn();
    }

    /**
     * This method checks if the point is on the rightmost column.
     *
     * @return true if the point is on the rightmost column, false otherwise.
     */
    public boolean isOnRightmostColumn() {
        return c == ComponentConstants.RIGHTMOST_COLUMN;
    }

    /**
     * This method checks if the point is not on the rightmost column.
     *
     * @return true if the point is not on the rightmost column, false otherwise.
     */
    public boolean isNotOnRightmostColumn() {
        return !isOnRightmostColumn();
    }

    /**
     * This method calculates the absolute row distance to another point.
     *
     * @param point the target point for calculating the row distance.
     * @return The absolute row distance between the point and the target point.
     */
    public int calcAbsRowDistance(Point point) {
        return Math.abs(r - point.r);
    }

    /**
     * This method calculates the absolute column distance to another point.
     *
     * @param point the target point for calculating the column distance.
     * @return The absolute column distance between the point and the target point.
     */
    public int calcAbsColumnDistance(Point point) {
        return Math.abs(c - point.c);
    }
}
