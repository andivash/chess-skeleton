package chess.pieces;

import chess.Player;

/**
 * The 'Rook' class
 */
public class Rook extends Piece implements StraightMovement{

    public Rook(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }
}
