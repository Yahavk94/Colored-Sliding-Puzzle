package service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class provides a set of methods for various string manipulation operations.
 *
 * @author Yahav Karpel
 */
public class StringService {

    /**
     * This method tokenizes the provided string into a list using the specified delimiter.
     *
     * @param string    the string to be tokenized.
     * @param delimiter the delimiter used to split the string.
     * @return A list of strings representing the tokens after trimming each element.
     */
    public static List<String> tokenizeIntoList(String string, String delimiter) {
        return Arrays.stream(string.split(delimiter))
                     .map(String::trim)
                     .collect(Collectors.toList());
    }

    /**
     * This method extracts the substring after the first colon in the provided string.
     *
     * @param string the string from which the substring is extracted.
     * @return A string representing the substring after the first colon, or the entire string if no colon is found.
     */
    public static String extractAfterColon(String string) {
        int colonIndex = string.indexOf(':');
        return colonIndex != -1 ? string.substring(colonIndex + 1) : string;
    }
}
