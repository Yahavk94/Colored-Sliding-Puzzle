package constants;

import java.nio.file.Paths;

/**
 * This class stores the constants used by the io package.
 *
 * @author Yahav Karpel
 */
public class IOConstants {

    public static final String INPUT_FILE_PATH = String.valueOf(Paths.get("inputs", "Input1.txt"));
    public static final String DIMENSION_DELIMITER = "x";
    public static final String NUMERIC_VALUES_DELIMITER = PunctuationConstants.COMMA;

    public static final String OUTPUT_FILE_NAME = "Output.txt";

    private IOConstants() {
    }
}
