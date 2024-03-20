package infrastructure;

import component.GeneratingOperator;

/**
 * This class represents a node in the search tree with additional information for informed DFS algorithms.
 *
 * @author Yahav Karpel
 */
public class InformedDepthFirstNode extends InformedNode {

    private boolean marked;

    /**
     * This method constructs the input node.
     */
    public InformedDepthFirstNode() {
        super();
    }

    /**
     * This method constructs a new node based on the specified attributes.
     *
     * @param parent             the parent of the new node.
     * @param generatingOperator the generating operator used to reach the new node from the parent.
     */
    public InformedDepthFirstNode(Node parent, GeneratingOperator generatingOperator) {
        super(parent, generatingOperator);
    }

    /**
     * This method checks if the node is marked.
     *
     * @return true if the node is marked, false otherwise.
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     * This method checks if the node is not marked.
     *
     * @return true if the node is not marked, false otherwise.
     */
    public boolean isNotMarked() {
        return !isMarked();
    }

    /**
     * This method sets the marked status of the node.
     *
     * @param marked the new marked status.
     */
    private void setMarked(boolean marked) {
        this.marked = marked;
    }

    /**
     * This method marks the node.
     */
    public void mark() {
        setMarked(true);
    }

    /**
     * This method unmarks the node.
     */
    public void unmark() {
        setMarked(false);
    }
}
