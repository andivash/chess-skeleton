package chess;

import chess.pieces.Bishop;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Bishop base tests
 * Created by andi on 12/17/2016.
 */
public class BishopTest {
    private Bishop blackBishop = new Bishop(Player.Black);
    private Bishop whiteBishop = new Bishop(Player.White);
    private Movement movement = new Movement();

    @Test
    public void correctIdentifyingCharacter(){
        assertEquals('B', blackBishop.getIdentifier());
        assertEquals('b', whiteBishop.getIdentifier());
    }

    @Test
    public void possibleMovesOnLastLine(){
        Position lastLinePosition = new Position("d8");
        Position firstLinePosition = new Position("d1");

        assertEquals("White bishop can move on the last line", 7, movement.getPossibleMoves(whiteBishop, lastLinePosition).size());
        assertEquals("Black bishop can move on the first line", 7, movement.getPossibleMoves(blackBishop, firstLinePosition).size());
    }

    @Test
    public void allMovesOnTheCenterOfBoard(){
        Position startPosition = new Position("d4");
        Set<Move> kingsMoves = TestUtils.getMovesFromStartPosition(startPosition, "c5 c3 b6 b2 a7 a1 e5 e3 f6 f2 g7 g1 h8");

        assertEquals("13 possible moves", 13, movement.getPossibleMoves(whiteBishop, startPosition).size());
        assertEquals("Should contain all possible moves", movement.getPossibleMoves(whiteBishop, startPosition), kingsMoves);


        assertEquals("13 moves possible", 13, movement.getPossibleMoves(blackBishop, startPosition).size());
        assertEquals("Should contain all possible moves", movement.getPossibleMoves(blackBishop, startPosition), kingsMoves);
    }

    @Test
    public void noMovesIfAllPositionsAreOccupied(){
        Set<Position> fullBoardPositions = TestUtils.getFullBoardPositions();
        Position startPosition = new Position("b2");
        movement.setOwnerPositions(fullBoardPositions);
        movement.setOpponentPositions(Collections.<Position>emptySet());

        assertEquals("No moves if all positions are occupied by owner pieces", 0, movement.getPossibleMoves(blackBishop, startPosition).size());

        movement.setOpponentPositions(fullBoardPositions);
        movement.setOwnerPositions(Collections.<Position>emptySet());

        assertTrue("Moves should be if all positions are occupied by opponent pieces", movement.getPossibleMoves(blackBishop, startPosition).size() > 0);
    }
}
