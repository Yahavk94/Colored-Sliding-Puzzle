package exception;

/**
 * This class represents an exception thrown during the creation of the output file.
 *
 * @author Yahav Karpel
 */
public class OutputFileCreationException extends RuntimeException {

    /**
     * This method constructs a new OutputFileCreationException with a default message and a cause.
     *
     * @param cause the cause of the exception, providing additional context about the error.
     */
    public OutputFileCreationException(Throwable cause) {
        super("Error while creating the output file.", cause);
    }
}
