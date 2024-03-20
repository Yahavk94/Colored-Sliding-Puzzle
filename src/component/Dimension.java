package component;

import io.InputParser;

import java.util.List;

/**
 * This class represents the dimensions of the puzzle.
 *
 * @author Yahav Karpel
 */
public class Dimension {

    private static final List<Integer> dimensions = InputParser.parseDimensions();
    public static final int NUM_OF_ROWS = dimensions.get(0);
    public static final int NUM_OF_COLS = dimensions.get(1);
    public static final int BOARD_SIZE = NUM_OF_ROWS * NUM_OF_COLS;
}
