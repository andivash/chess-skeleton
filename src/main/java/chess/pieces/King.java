package chess.pieces;

import chess.MoveSettings;
import chess.Player;

/**
 * The King class
 */
public class King extends Piece implements DiagonalMovement, StraightMovement {
    public King(Player owner) {
        super(owner);
        setMoveSettings(new MoveSettings(1));
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }
}
