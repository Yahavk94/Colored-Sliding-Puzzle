package service;

import component.Color;
import component.Piece;
import exception.UnexpectedFixedPieceException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static component.Color.*;
import static constants.IOConstants.NUMERIC_VALUES_DELIMITER;

/**
 * This class provides a set of methods for assisting the InputParser class.
 *
 * @author Yahav Karpel
 */
public class InputService {

    /**
     * This method extracts the numeric values from the provided input line.
     *
     * @param inputLine the input line containing numeric values.
     * @return A set of strings representing the extracted numeric values.
     */
    public static Set<String> extractNumericValues(String inputLine) {
        String afterColon = StringService.extractAfterColon(inputLine);
        List<String> numericValues = StringService.tokenizeIntoList(afterColon, NUMERIC_VALUES_DELIMITER);
        return new HashSet<>(numericValues);
    }

    /**
     * This method creates a piece based on the provided information.
     *
     * @param rawData    the raw data of the piece.
     * @param grayPieces a set of strings representing the gray pieces.
     * @param redPieces  a set of strings representing the red pieces.
     * @return The created piece.
     */
    public static Piece createPiece(String rawData, Set<String> grayPieces, Set<String> redPieces) {
        try {
            int data = parseRawData(rawData);
            Color color = determinePieceColor(rawData, grayPieces, redPieces);
            return new Piece(rawData, data, color);
        } catch (NumberFormatException e) {
            return new Piece();
        }
    }

    /**
     * This method parses the provided raw data to obtain the numeric value.
     *
     * @param rawData the raw data to be parsed.
     * @return The parsed numeric value.
     */
    private static int parseRawData(String rawData) {
        return Integer.parseInt(rawData) - 1;
    }

    /**
     * This method determines the color of the piece based on the provided information.
     *
     * @param rawData    the raw data of the piece.
     * @param grayPieces a set of strings representing the gray pieces.
     * @param redPieces  a set of strings representing the red pieces.
     * @return The color of the piece.
     */
    private static Color determinePieceColor(String rawData, Set<String> grayPieces, Set<String> redPieces) {
        if (grayPieces.contains(rawData)) {
            return GRAY;
        }

        if (redPieces.contains(rawData)) {
            return RED;
        }

        return GREEN;
    }

    /**
     * This method verifies the integrity of the provided board.
     *
     * @param board a configuration of the pieces.
     * @throws UnexpectedFixedPieceException if unexpected fixed piece is encountered.
     */
    public static void verifyBoard(List<Piece> board) {
        for (int i = 0; i < board.size(); i++) {
            Piece piece = board.get(i);
            if (piece.isFixedOnBoard() && piece.getData() != i) {
                throw new UnexpectedFixedPieceException();
            }
        }
    }
}
