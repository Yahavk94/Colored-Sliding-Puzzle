package exception;

import component.GeneratingOperator;

/**
 * This class represents an exception thrown when unsupported generating operator is encountered.
 *
 * @author Yahav Karpel
 */
public class UnsupportedOperatorException extends IllegalArgumentException {

    /**
     * This method constructs a new UnsupportedOperatorException with a message indicating the unsupported generating operator.
     *
     * @param generatingOperator the unsupported generating operator that triggered the exception.
     */
    public UnsupportedOperatorException(GeneratingOperator generatingOperator) {
        super(String.format("Unsupported generating operator: '%s'.", generatingOperator));
    }
}
