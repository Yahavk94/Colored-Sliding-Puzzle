package service;

import constants.PunctuationConstants;
import exception.UnsupportedSearchNameException;
import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;
import io.InputParser;
import search.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a set of methods for assisting the OutputPrinter class.
 *
 * @author Yahav Karpel
 */
public class OutputService {

    /**
     * This method executes the search algorithm starting from the provided root node.
     * If the root node represents the target, no search is performed.
     *
     * @param root the root node from which the search begins.
     * @return The target node found by the search, or null if the root node represents the target.
     */
    public static Node executeSearch(InformedDepthFirstNode root) {
        if (root.isTarget()) {
            return null;
        }

        return initSearch().search(root);
    }

    /**
     * This method initializes the search algorithm based on user input.
     *
     * @return An instance of the selected search algorithm.
     * @throws UnsupportedSearchNameException if the search algorithm name is not supported.
     */
    private static Searchable initSearch() {
        String searchName = InputParser.getSearchName();
        return switch (searchName.toUpperCase()) {
            case "BFS" -> new BFS();
            case "IDDFS" -> new IDDFS();
            case "A*" -> new AStar();
            case "IDA*" -> new IDAStar();
            case "DFBNB" -> new DFBnB();
            default -> throw new UnsupportedSearchNameException(searchName);
        };
    }

    /**
     * This method prints the shortest path to the provided PrintWriter.
     *
     * @param outputFile PrintWriter to write the output.
     * @param targetNode the target node.
     */
    public static void printShortestPath(PrintWriter outputFile, Node targetNode) {
        String shortestPath = createShortestPath(targetNode);
        if (shortestPath.isEmpty()) {
            outputFile.println("No path");
        } else {
            outputFile.println(shortestPath);
        }
    }

    /**
     * This method creates the shortest path from the root to the target node.
     *
     * @param targetNode the target node.
     * @return The shortest path as a string.
     */
    private static String createShortestPath(Node targetNode) {
        if (targetNode == null || targetNode.isRoot()) {
            return PunctuationConstants.EMPTY;
        }

        List<String> shortestPath = new ArrayList<>();
        populateShortestPath(targetNode, shortestPath);
        return String.join(PunctuationConstants.DASH, shortestPath);
    }

    /**
     * This method recursively populates the shortest path with edge tags.
     *
     * @param node         the current node being processed.
     * @param shortestPath the edge tags representing the shortest path.
     */
    private static void populateShortestPath(Node node, List<String> shortestPath) {
        Node parent = node.getParent();
        if (parent != null) {
            populateShortestPath(parent, shortestPath);
            shortestPath.add(node.getEdgeTagFromParent());
        }
    }

    /**
     * This method prints the number of nodes generated to the provided PrintWriter.
     *
     * @param outputFile PrintWriter to write the output.
     */
    public static void printNumOfNodesGenerated(PrintWriter outputFile) {
        outputFile.println(String.format("Num of nodes generated: %d", Node.getNumNodes()));
    }

    /**
     * This method prints the path cost to the provided PrintWriter.
     *
     * @param outputFile PrintWriter to write the output.
     * @param root       the root node from which the search begins.
     * @param targetNode the target node.
     */
    public static void printPathCost(PrintWriter outputFile, Node root, Node targetNode) {
        if (targetNode != null && targetNode != root) {
            outputFile.println(String.format("Path cost: %d", targetNode.getWeight()));
        }
    }

    /**
     * This method prints the execution time to the provided PrintWriter.
     *
     * @param outputFile PrintWriter to write the output.
     * @param start      the start time in milliseconds.
     */
    public static void printExecutionTime(PrintWriter outputFile, long start) {
        double duration = (System.currentTimeMillis() - start) / 1000.0;
        outputFile.println(String.format("%.3f seconds", duration));
    }

    private OutputService() {
    }
}
