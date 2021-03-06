package chess.pieces;

import chess.Player;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece implements DiagonalMovement {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }
}
