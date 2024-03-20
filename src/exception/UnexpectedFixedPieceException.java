package exception;

/**
 * This class represents an exception thrown when unexpected fixed piece is encountered.
 *
 * @author Yahav Karpel
 */
public class UnexpectedFixedPieceException extends IllegalArgumentException {

    /**
     * This method constructs a new UnexpectedFixedPieceException with a default message.
     */
    public UnexpectedFixedPieceException() {
        super("Not all fixed pieces are set correctly on the board.");
    }
}
