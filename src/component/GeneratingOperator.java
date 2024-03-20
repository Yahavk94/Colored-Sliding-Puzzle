package component;

/**
 * This enum represents the generating operator assigned to each node by its parent in the search tree.
 *
 * @author Yahav Karpel
 */
public enum GeneratingOperator {

    L {
        @Override
        public boolean isOpposite(GeneratingOperator generatingOperator) {
            return generatingOperator == R;
        }
    },
    U {
        @Override
        public boolean isOpposite(GeneratingOperator generatingOperator) {
            return generatingOperator == D;
        }
    },
    R {
        @Override
        public boolean isOpposite(GeneratingOperator generatingOperator) {
            return generatingOperator == L;
        }
    },
    D {
        @Override
        public boolean isOpposite(GeneratingOperator generatingOperator) {
            return generatingOperator == U;
        }
    };

    /**
     * This method checks if the provided generating operator is the opposite of the current one.
     *
     * @param generatingOperator the generating operator to compare against.
     * @return true if the provided generating operator is the opposite, false otherwise.
     */
    public abstract boolean isOpposite(GeneratingOperator generatingOperator);

    /**
     * This method checks if the provided generating operator is not the opposite of the current one.
     *
     * @param generatingOperator the generating operator to compare against.
     * @return true if the provided generating operator is not the opposite, false otherwise.
     */
    public boolean isNotOpposite(GeneratingOperator generatingOperator) {
        return !isOpposite(generatingOperator);
    }
}
