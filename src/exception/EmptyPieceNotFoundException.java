package exception;

/**
 * This class represents an exception thrown when the empty piece is not found.
 *
 * @author Yahav Karpel
 */
public class EmptyPieceNotFoundException extends RuntimeException {

    /**
     * This method constructs a new EmptyPieceNotFoundException with a default message.
     */
    public EmptyPieceNotFoundException() {
        super("Unable to find the empty piece.");
    }
}
