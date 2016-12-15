package chess.pieces;

import chess.MoveSettings;
import chess.Player;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;
    private MoveSettings moveSettings;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    public void setMoveSettings(MoveSettings settings){
        this.moveSettings = settings;
    }

    public MoveSettings getMoveSettings(){
        return this.moveSettings;
    }

    protected abstract char getIdentifyingCharacter();
}
