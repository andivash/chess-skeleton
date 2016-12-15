package chess.pieces;

import chess.Player;

/**
 * The Queen
 */
public class Queen extends Piece implements DiagonalMovement, StraightMovement {
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }
}
