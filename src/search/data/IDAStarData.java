package search.data;

import static java.lang.Integer.MAX_VALUE;

/**
 * This class represents a data structure designed for IDA* algorithm.
 *
 * @author Yahav Karpel
 */
public class IDAStarData extends InformedDepthFirstData {

    private int minF;

    /**
     * This method updates the search limit to hold minF value.
     */
    public void updateSearchLimit() {
        setSearchLimit(minF);
    }

    /**
     * This method resets minF to Integer.MAX_VALUE.
     */
    public void resetMinF() {
        setMinF(MAX_VALUE);
    }

    /**
     * This method tries to reduce minF based on the provided f value.
     *
     * @param f the f value to consider for reducing minF.
     */
    public void tryReducingMinF(int f) {
        if (f < minF) {
            setMinF(f);
        }
    }

    /**
     * This method sets the minF value.
     *
     * @param minF the new minF value.
     */
    private void setMinF(int minF) {
        this.minF = minF;
    }
}
