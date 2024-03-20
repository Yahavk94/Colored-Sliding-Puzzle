package io;

import component.Piece;
import exception.InputFileProcessingException;
import service.InputService;
import service.StringService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import static constants.IOConstants.*;

/**
 * This class represents an input parser responsible for parsing input data.
 *
 * @author Yahav Karpel
 */
public class InputParser {

    private static final List<String> inputLines = readInputLines();

    /**
     * This method reads all lines from the input file and returns them as a list of strings.
     *
     * @return A list of strings representing the lines read from the input file.
     * @throws InputFileProcessingException if an IOException occurs during file reading.
     */
    private static List<String> readInputLines() {
        List<String> inputLines = new ArrayList<>();
        try {
            File file = new File(INPUT_FILE_NAME);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String inputLine = scanner.nextLine().trim();
                if (!inputLine.isEmpty()) {
                    inputLines.add(inputLine);
                }
            }
        } catch (FileNotFoundException e) {
            throw new InputFileProcessingException(e);
        }

        return inputLines;
    }

    /**
     * This method returns the search name from the input file.
     *
     * @return The search name.
     */
    public static String getSearchName() {
        return inputLines.get(0);
    }

    /**
     * This method parses the dimensions of the puzzle from the input file.
     *
     * @return The parsed dimensions of the puzzle.
     */
    public static List<Integer> parseDimensions() {
        return StringService.tokenizeIntoList(inputLines.get(1), DIMENSION_DELIMITER)
                            .stream()
                            .map(Integer::valueOf)
                            .collect(Collectors.toList());
    }

    /**
     * This method parses the board from the input file.
     *
     * @return The parsed input board.
     */
    public static List<Piece> parseInputBoard() {
        List<Piece> board = new ArrayList<>();
        Set<String> grayPieces = InputService.extractNumericValues(inputLines.get(2));
        Set<String> redPieces = InputService.extractNumericValues(inputLines.get(3));
        for (String rawData : parseInputRawBoard()) {
            Piece piece = InputService.createPiece(rawData, grayPieces, redPieces);
            board.add(piece);
        }

        InputService.verifyBoard(board);
        return board;
    }

    /**
     * This method parses the raw board from the input file.
     *
     * @return The parsed input raw board.
     */
    private static List<String> parseInputRawBoard() {
        return inputLines.subList(4, inputLines.size())
                         .stream()
                         .map(inputLine -> StringService.tokenizeIntoList(inputLine, NUMERIC_VALUES_DELIMITER))
                         .flatMap(List::stream)
                         .collect(Collectors.toList());
    }
}
