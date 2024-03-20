package io;

import exception.OutputFileCreationException;
import infrastructure.InformedDepthFirstNode;
import infrastructure.Node;
import service.OutputService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static constants.IOConstants.OUTPUT_FILE_NAME;

/**
 * This class represents an output printer responsible for printing search output data to a file.
 *
 * @author Yahav Karpel
 */
public class OutputPrinter {

    /**
     * This method prints the search output to a file.
     *
     * @throws OutputFileCreationException if an error occurs during output file creation.
     */
    public static void printSearchOutput() {
        try {
            long start = System.currentTimeMillis();
            FileWriter fileWriter = new FileWriter(OUTPUT_FILE_NAME);
            PrintWriter outputFile = new PrintWriter(fileWriter);
            InformedDepthFirstNode root = new InformedDepthFirstNode();
            Node targetNode = OutputService.executeSearch(root);
            OutputService.printShortestPath(outputFile, targetNode);
            OutputService.printNumOfNodesGenerated(outputFile);
            OutputService.printPathCost(outputFile, root, targetNode);
            OutputService.printExecutionTime(outputFile, start);
            outputFile.close();
        } catch (IOException e) {
            throw new OutputFileCreationException(e);
        }
    }
}
