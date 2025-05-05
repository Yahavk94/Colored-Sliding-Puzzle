package infrastructure;

import component.GeneratingOperator;

/**
 * This class represents a node in the search tree.
 *
 * @author Yahav Karpel
 */
public abstract class Node extends State {

    // static counter to keep track of the number of nodes created
    private static long numNodes = 0;

    // instance variables
    private int weight = 0;
    private Node parent;
    private GeneratingOperator generatingOperator;
    private String edgeTagFromParent;

    // unique identifier for each node
    public final long id = numNodes++;

    /**
     * This method constructs the input node.
     */
    protected Node() {
        super();
        setEdgeTagFromParent("");
    }

    /**
     * This method constructs a new node based on the specified attributes.
     *
     * @param parent             the parent of the new node.
     * @param generatingOperator the generating operator used to reach the new node from the parent.
     */
    protected Node(Node parent, GeneratingOperator generatingOperator) {
        super(parent);
        setWeight(parent.weight);
        setParent(parent);
        setGeneratingOperator(generatingOperator);
    }

    /**
     * This method returns the number of nodes created.
     *
     * @return The number of nodes created.
     */
    public static long getNumNodes() {
        return numNodes;
    }

    /**
     * This method returns the weight of the node.
     *
     * @return The weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * This method sets the weight of the node.
     *
     * @param weight the new weight.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * This method returns the parent of the node.
     *
     * @return The parent.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * This method sets the parent of the node.
     *
     * @param parent the new parent.
     */
    private void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * This method returns the generating operator used to reach the node from the parent.
     *
     * @return The generating operator.
     */
    public GeneratingOperator getGeneratingOperator() {
        return generatingOperator;
    }

    /**
     * This method sets the generating operator used to reach the node from the parent.
     *
     * @param generatingOperator the new generating operator.
     */
    private void setGeneratingOperator(GeneratingOperator generatingOperator) {
        this.generatingOperator = generatingOperator;
    }

    /**
     * This method returns the edge tag from the parent.
     *
     * @return The edge tag.
     */
    public String getEdgeTagFromParent() {
        return edgeTagFromParent;
    }

    /**
     * This method sets the edge tag from the parent.
     *
     * @param edgeTagFromParent the new edge tag.
     */
    public void setEdgeTagFromParent(String edgeTagFromParent) {
        this.edgeTagFromParent = edgeTagFromParent;
    }

    /**
     * This method checks if the node is the root.
     *
     * @return true if the node is the root, false otherwise.
     */
    public boolean isRoot() {
        return parent == null;
    }
}
