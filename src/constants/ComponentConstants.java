package constants;

import static component.Dimension.NUM_OF_COLS;
import static component.Dimension.NUM_OF_ROWS;

/**
 * This class stores the constants used by the component package.
 *
 * @author Yahav Karpel
 */
public class ComponentConstants {

    public static final int EMPTY_PIECE_DATA = -1;
    public static final String EMPTY_PIECE_RAW_DATA = "_";

    public static final int TOP_ROW = 0;
    public static final int BOTTOM_ROW = NUM_OF_ROWS - 1;
    public static final int LEFTMOST_COLUMN = 0;
    public static final int RIGHTMOST_COLUMN = NUM_OF_COLS - 1;
}
