package exception;

/**
 * This class represents an exception thrown during the processing of the input file.
 *
 * @author Yahav Karpel
 */
public class InputFileProcessingException extends RuntimeException {

    /**
     * This method constructs a new InputFileProcessingException with a default message and a cause.
     *
     * @param cause the cause of the exception, providing additional context about the error.
     */
    public InputFileProcessingException(Throwable cause) {
        super("Error while processing the input file.", cause);
    }
}
