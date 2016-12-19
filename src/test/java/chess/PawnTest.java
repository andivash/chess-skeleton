package chess;

import chess.pieces.Pawn;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Pawn base tests
 * Created by andi on 12/17/2016.
 */
public class PawnTest {

    private Pawn blackPawn = new Pawn(Player.Black);
    private Pawn whitePawn = new Pawn(Player.White);
    private Movement movement = new Movement();

    @Test
    public void correctIdentifyingCharacter(){
        assertEquals('P', blackPawn.getIdentifier());
        assertEquals('p', whitePawn.getIdentifier());
    }

    @Test
    public void noMovesOnLastLine(){
        Position lastLinePosition = new Position("d8");
        Position firstLinePosition = new Position("d1");

        assertEquals("No moves for white pawn on the last line", 0, movement.getPossibleMoves(whitePawn, lastLinePosition).size());
        assertEquals("No moves for black pawn on the first line", 0, movement.getPossibleMoves(blackPawn, firstLinePosition).size());
        assertTrue("Are moves for black pawn on the last line", movement.getPossibleMoves(blackPawn, lastLinePosition).size() > 0);
        assertTrue("Are moves for black pawn on the last line", movement.getPossibleMoves(whitePawn, firstLinePosition).size() > 0);
    }

    @Test
    public void allMovesOnTheCenterOfBoard(){
        Position startPosition = new Position("d4");

        assertEquals("Only one move possible", 1, movement.getPossibleMoves(whitePawn, startPosition).size());
        assertEquals("Should contain d4 d5 move", movement.getPossibleMoves(whitePawn, startPosition), new HashSet<Move>(Arrays.asList(new Move(startPosition, new Position("d5") ))));


        assertEquals("Only one move possible", 1, movement.getPossibleMoves(blackPawn, startPosition).size());
        assertEquals("Should contain d4 d3 move", movement.getPossibleMoves(blackPawn, startPosition), new HashSet<Move>(Arrays.asList(new Move(startPosition, new Position("d3") ))));
    }

    @Test
    public void noMovesIfAllPositionsAreOccupied(){
        Set<Position> fullBoardPositions = TestUtils.getFullBoardPositions();
        Position startPosition = new Position("b2");
        movement.setOwnerPositions(fullBoardPositions);
        movement.setOpponentPositions(Collections.<Position>emptySet());

        assertEquals("No moves if all positions are occupied by owner pieces", 0, movement.getPossibleMoves(blackPawn, startPosition).size());

        movement.setOpponentPositions(fullBoardPositions);
        movement.setOwnerPositions(Collections.<Position>emptySet());

        assertTrue("Moves should be if all positions are occupied by opponent pieces", movement.getPossibleMoves(blackPawn, startPosition).size() > 0);
    }

}
