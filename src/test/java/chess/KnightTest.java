package chess;

import chess.pieces.Knight;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Knight best tests
 * Created by andi on 12/17/2016.
 */
public class KnightTest {
    private Knight blackKnight = new Knight(Player.Black);
    private Knight whiteKnight = new Knight(Player.White);
    private Movement movement = new Movement();

    @Test
    public void correctIdentifyingCharacter(){
        assertEquals('N', blackKnight.getIdentifier());
        assertEquals('n', whiteKnight.getIdentifier());
    }

    @Test
    public void possibleMovesOnLastLine(){
        Position lastLinePosition = new Position("d8");
        Position firstLinePosition = new Position("d1");

        assertEquals("White knight can move on the last line", 4, movement.getPossibleMoves(whiteKnight, lastLinePosition).size());
        assertEquals("Black knight can move on the first line", 4, movement.getPossibleMoves(blackKnight, firstLinePosition).size());
    }

    @Test
    public void allMovesOnTheCenterOfBoard(){
        Position startPosition = new Position("d4");
        Set<Move> knightsMoves = TestUtils.getMovesFromStartPosition(startPosition, "e6 c6 e2 c2 b5 b3 f5 f3");

        assertEquals("8 possible moves", 8, movement.getPossibleMoves(whiteKnight, startPosition).size());
        assertEquals("Should contain all possible moves", movement.getPossibleMoves(whiteKnight, startPosition), knightsMoves);


        assertEquals("8 moves possible", 8, movement.getPossibleMoves(blackKnight, startPosition).size());
        assertEquals("Should contain all possible moves", movement.getPossibleMoves(blackKnight, startPosition), knightsMoves);
    }

    @Test
    public void noMovesIfAllPositionsAreOccupied(){
        Set<Position> fullBoardPositions = TestUtils.getFullBoardPositions();
        Position startPosition = new Position("b2");
        movement.setOwnerPositions(fullBoardPositions);
        movement.setOpponentPositions(Collections.<Position>emptySet());

        assertEquals("No moves if all positions are occupied by owner pieces", 0, movement.getPossibleMoves(blackKnight, startPosition).size());

        movement.setOpponentPositions(fullBoardPositions);
        movement.setOwnerPositions(Collections.<Position>emptySet());

        assertTrue("Moves should be if all positions are occupied by opponent pieces", movement.getPossibleMoves(blackKnight, startPosition).size() > 0);
    }


}
