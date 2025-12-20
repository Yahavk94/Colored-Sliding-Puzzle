package service;

import component.Dimension;

/**
 * This class provides a set of methods for managing 2D indices.
 *
 * @author Yahav Karpel
 */
public class PointService {

    /**
     * This method converts a 1D index to the corresponding row index in a 2D space.
     *
     * @param index the 1D index.
     * @return The row index in a 2D space.
     */
    public static int toRow(int index) {
        return index / Dimension.NUM_OF_COLS;
    }

    /**
     * This method converts a 1D index to the corresponding column index in a 2D space.
     *
     * @param index the 1D index.
     * @return The column index in a 2D space.
     */
    public static int toColumn(int index) {
        return index % Dimension.NUM_OF_COLS;
    }

    private PointService() {
    }
}
