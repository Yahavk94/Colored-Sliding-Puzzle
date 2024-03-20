package search.data;

import infrastructure.InformedDepthFirstNode;

import java.util.Stack;

import static java.lang.Integer.MAX_VALUE;

/**
 * This class represents the common data structure used in informed DFS algorithms.
 *
 * @author Yahav Karpel
 */
public class InformedDepthFirstData extends DepthFirstData {

    private int searchLimit;
    private final Stack<InformedDepthFirstNode> stack = new Stack<>();

    /**
     * This method returns the current search limit.
     *
     * @return The current search limit.
     */
    public int getSearchLimit() {
        return searchLimit;
    }

    /**
     * This method sets the current search limit.
     *
     * @param searchLimit the new search limit.
     */
    public void setSearchLimit(int searchLimit) {
        this.searchLimit = searchLimit;
    }

    /**
     * This method checks if the search limit is less than Integer.MAX_VALUE.
     *
     * @return true if the search limit is less than Integer.MAX_VALUE, false otherwise.
     */
    public boolean hasFiniteSearchLimit() {
        return searchLimit < MAX_VALUE;
    }

    /**
     * This method checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    public boolean hasEmptyStack() {
        return stack.isEmpty();
    }

    /**
     * This method checks if the stack is not empty.
     *
     * @return true if the stack is not empty, false otherwise.
     */
    public boolean hasNotEmptyStack() {
        return !hasEmptyStack();
    }

    /**
     * This method pushes the provided node onto the stack.
     *
     * @param node The node to be pushed.
     */
    public void pushToStack(InformedDepthFirstNode node) {
        stack.push(node);
    }

    /**
     * This method removes the top node from the stack.
     *
     * @return The top node of the stack.
     */
    public InformedDepthFirstNode popFromStack() {
        return stack.pop();
    }

    /**
     * This method adds the provided node to both the stack and loop avoidance using the provided code.
     *
     * @param node     the node to be added.
     * @param nodeCode the unique code representing the node.
     */
    public void addInStackAndLoopAvoidance(InformedDepthFirstNode node, String nodeCode) {
        pushToStack(node);
        putInLoopAvoidance(nodeCode, node);
    }

    /**
     * This method adds the provided node to both the stack and loop avoidance.
     *
     * @param node the node to be added.
     */
    public void addInStackAndLoopAvoidance(InformedDepthFirstNode node) {
        addInStackAndLoopAvoidance(node, node.toString());
    }
}
