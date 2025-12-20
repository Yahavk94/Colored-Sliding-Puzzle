package service;

import component.Color;
import component.Piece;
import constants.ComponentConstants;

public class ComponentService {

    public static final Piece emptyPiece = createEmptyPiece();

    private static Piece createEmptyPiece() {
        return new Piece(ComponentConstants.EMPTY_PIECE_RAW_DATA,
                         ComponentConstants.EMPTY_PIECE_DATA,
                         Color.NONE);
    }

    private ComponentService() {
    }
}
