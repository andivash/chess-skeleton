package chess;

import chess.pieces.Queen;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Queen base tests
 * Created by andi on 12/19/2016.
 */
public class QueenTest {
    private Queen blackQueen = new Queen(Player.Black);
    private Queen whiteQueen = new Queen(Player.White);
    private Movement movement = new Movement();

    @Test
    public void correctIdentifyingCharacter(){
        assertEquals('Q', blackQueen.getIdentifier());
        assertEquals('q', whiteQueen.getIdentifier());
    }

    @Test
    public void possibleMovesOnLastLine(){
        Position lastLinePosition = new Position("d8");
        Position firstLinePosition = new Position("d1");

        assertEquals("White queen can move on the last line", 21, movement.getPossibleMoves(whiteQueen, lastLinePosition).size());
        assertEquals("Black queen can move on the first line", 21, movement.getPossibleMoves(blackQueen, firstLinePosition).size());
    }

    @Test
    public void allMovesOnTheCenterOfBoard(){
        Position startPosition = new Position("d4");
        Set<Move> queensMoves = TestUtils.getMovesFromStartPosition(startPosition, "d5 d6 d7 d8 d3 d2 d1 a4 b4 c4 e4 f4 g4 h4 c5 c3 b6 b2 a7 a1 e5 e3 f6 f2 g7 g1 h8");

        assertEquals("27 possible moves", 27, movement.getPossibleMoves(whiteQueen, startPosition).size());
        assertEquals("Should contain all possible moves", movement.getPossibleMoves(whiteQueen, startPosition), queensMoves);


        assertEquals("27 moves possible", 27, movement.getPossibleMoves(blackQueen, startPosition).size());
        assertEquals("Should contain all possible moves", movement.getPossibleMoves(blackQueen, startPosition), queensMoves);
    }

    @Test
    public void noMovesIfAllPositionsAreOccupied(){
        Set<Position> fullBoardPositions = TestUtils.getFullBoardPositions();
        Position startPosition = new Position("b2");
        movement.setOwnerPositions(fullBoardPositions);
        movement.setOpponentPositions(Collections.<Position>emptySet());

        assertEquals("No moves if all positions are occupied by owner pieces", 0, movement.getPossibleMoves(blackQueen, startPosition).size());

        movement.setOpponentPositions(fullBoardPositions);
        movement.setOwnerPositions(Collections.<Position>emptySet());

        assertTrue("Moves should be if all positions are occupied by opponent pieces", movement.getPossibleMoves(blackQueen, startPosition).size() > 0);
    }

}
