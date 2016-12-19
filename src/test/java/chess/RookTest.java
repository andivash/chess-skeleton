package chess;

import chess.pieces.Rook;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Rock base tests
 * Created by andi on 12/17/2016.
 */
public class RookTest {
    private Rook blackRook = new Rook(Player.Black);
    private Rook whiteRook = new Rook(Player.White);
    private Movement movement = new Movement();

    @Test
    public void correctIdentifyingCharacter(){
        assertEquals('R', blackRook.getIdentifier());
        assertEquals('r', whiteRook.getIdentifier());
    }

    @Test
    public void possibleMovesOnLastLine(){
        Position lastLinePosition = new Position("d8");
        Position firstLinePosition = new Position("d1");

        assertEquals("White rook can move on the last line", 14, movement.getPossibleMoves(whiteRook, lastLinePosition).size());
        assertEquals("Black rook can move on the first line", 14, movement.getPossibleMoves(blackRook, firstLinePosition).size());
    }

    @Test
    public void allMovesOnTheCenterOfBoard(){
        Position startPosition = new Position("d4");
        Set<Move> rookMoves = TestUtils.getMovesFromStartPosition(startPosition, "d5 d6 d7 d8 d3 d2 d1 a4 b4 c4 e4 f4 g4 h4");

        assertEquals("14 possible moves", 14, movement.getPossibleMoves(whiteRook, startPosition).size());
        assertEquals("Should contain all possible moves", movement.getPossibleMoves(whiteRook, startPosition), rookMoves);


        assertEquals("14 moves possible", 14, movement.getPossibleMoves(blackRook, startPosition).size());
        assertEquals("Should contain all possible moves", movement.getPossibleMoves(blackRook, startPosition), rookMoves);
    }

    @Test
    public void noMovesIfAllPositionsAreOccupied(){
        Set<Position> fullBoardPositions = TestUtils.getFullBoardPositions();
        Position startPosition = new Position("b2");
        movement.setOwnerPositions(fullBoardPositions);
        movement.setOpponentPositions(Collections.<Position>emptySet());

        assertEquals("No moves if all positions are occupied by owner pieces", 0, movement.getPossibleMoves(blackRook, startPosition).size());

        movement.setOpponentPositions(fullBoardPositions);
        movement.setOwnerPositions(Collections.<Position>emptySet());

        assertTrue("Moves should be if all positions are occupied by opponent pieces", movement.getPossibleMoves(blackRook, startPosition).size() > 0);
    }


}
