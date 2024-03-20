package component;

/**
 * This enum represents the possible colors of a piece on the board.
 *
 * @author Yahav Karpel
 */
public enum Color {

    NONE,
    GRAY,
    GREEN(1),
    RED(30);

    private int cost;

    /**
     * This method constructs a new color with a default cost.
     */
    Color() {
        setCost(0);
    }

    /**
     * This method constructs a new color with the specified cost.
     *
     * @param cost the cost associated with the new color.
     */
    Color(int cost) {
        setCost(cost);
    }

    /**
     * This method returns the cost of the color.
     *
     * @return The cost.
     */
    public int getCost() {
        return cost;
    }

    /**
     * This method sets the cost of the color.
     *
     * @param cost the new cost.
     */
    private void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * This method checks if the color has a positive cost.
     *
     * @return true if the color has a positive cost, false otherwise.
     */
    public boolean hasPositiveCost() {
        return cost > 0;
    }
}
