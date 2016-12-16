package chess.pieces;

import chess.Player;

/**
 * The King class
 */
public class King extends Piece implements DiagonalMovement, StraightMovement, OneStepMovable {
    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }
}
