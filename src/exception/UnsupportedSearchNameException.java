package exception;

/**
 * This class represents an exception thrown when unsupported search name is encountered.
 *
 * @author Yahav Karpel
 */
public class UnsupportedSearchNameException extends IllegalArgumentException {

    /**
     * This method constructs a new UnsupportedSearchNameException with a message indicating the unsupported search name.
     *
     * @param searchName the unsupported search name that triggered the exception.
     */
    public UnsupportedSearchNameException(String searchName) {
        super("Unsupported search name: " + searchName + ".");
    }
}
