package chess;

import chess.pieces.King;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * King base tests
 * Created by andi on 12/17/2016.
 */
public class KingTest {
    private King blackKing = new King(Player.Black);
    private King whiteKing = new King(Player.White);
    private Movement movement = new Movement();

    @Test
    public void correctIdentifyingCharacter(){
        assertEquals('K', blackKing.getIdentifier());
        assertEquals('k', whiteKing.getIdentifier());
    }

    @Test
    public void possibleMovesOnLastLine(){
        Position lastLinePosition = new Position("d8");
        Position firstLinePosition = new Position("d1");

        assertEquals("White king can move on the last line", 5, movement.getPossibleMoves(whiteKing, lastLinePosition).size());
        assertEquals("Black king can move on the first line", 5, movement.getPossibleMoves(blackKing, firstLinePosition).size());
    }

    @Test
    public void allMovesOnTheCenterOfBoard(){
        Position startPosition = new Position("d4");
        Set<Move> kingsMoves = TestUtils.getMovesFromStartPosition(startPosition, "d5 d3 c5 c4 c3 e5 e4 e3");

        assertEquals("Eight moves possible", 8, movement.getPossibleMoves(whiteKing, startPosition).size());
        assertEquals("Should contain all possible moves", movement.getPossibleMoves(whiteKing, startPosition), kingsMoves);


        assertEquals("Eight moves possible", 8, movement.getPossibleMoves(blackKing, startPosition).size());
        assertEquals("Should contain all possible moves", movement.getPossibleMoves(blackKing, startPosition), kingsMoves);
    }

    @Test
    public void noMovesIfAllPositionsAreOccupied(){
        Set<Position> fullBoardPositions = TestUtils.getFullBoardPositions();
        Position startPosition = new Position("b2");
        movement.setOwnerPositions(fullBoardPositions);
        movement.setOpponentPositions(Collections.<Position>emptySet());

        assertEquals("No moves if all positions are occupied by owner pieces", 0, movement.getPossibleMoves(blackKing, startPosition).size());

        movement.setOpponentPositions(fullBoardPositions);
        movement.setOwnerPositions(Collections.<Position>emptySet());

        assertTrue("Moves should be if all positions are occupied by opponent pieces", movement.getPossibleMoves(blackKing, startPosition).size() > 0);
    }

}
