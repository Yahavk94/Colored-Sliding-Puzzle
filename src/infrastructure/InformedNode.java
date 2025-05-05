package infrastructure;

import component.GeneratingOperator;
import service.InfrastructureService;

/**
 * This class represents a node in the search tree with additional information for informed search algorithms.
 *
 * @author Yahav Karpel
 */
public abstract class InformedNode extends Node implements Comparable<InformedNode> {

    private int f;

    /**
     * This method constructs the input node.
     */
    protected InformedNode() {
        super();
    }

    /**
     * This method constructs a new node based on the specified attributes.
     *
     * @param parent             the parent of the new node.
     * @param generatingOperator the generating operator used to reach the new node from the parent.
     */
    protected InformedNode(Node parent, GeneratingOperator generatingOperator) {
        super(parent, generatingOperator);
    }

    /**
     * This method returns the f value of the node.
     *
     * @return The f value.
     */
    public int getF() {
        return f;
    }

    /**
     * This method sets the f value of the node.
     *
     * @param f the new f value.
     */
    private void setF(int f) {
        this.f = f;
    }

    /**
     * This method calculates the f value of the node.
     *
     * @return The calculated f value.
     */
    public int calcF() {
        return getWeight() + InfrastructureService.h(getBoard());
    }

    /**
     * This method updates the f value of the node.
     */
    public void updateF() {
        setF(calcF());
    }

    /**
     * This method compares the node with another based on the f value.
     * If the values are equal, nodes are further compared based on their unique identifiers.
     *
     * @param informedNode the node to compare with.
     * @return A negative integer, zero, or a positive integer as the node is less than, equal to,
     * or greater than the provided node, respectively.
     */
    @Override
    public int compareTo(InformedNode informedNode) {
        int fComp = Integer.compare(f, informedNode.f);
        return fComp != 0 ? fComp : Long.compare(id, informedNode.id);
    }
}
