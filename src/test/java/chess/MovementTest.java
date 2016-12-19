package chess;

import chess.pieces.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Movement unit tests
 */
public class MovementTest {

    /**
     * Test for possible moves for initial chess positions
     */
    @Test
    public void testPossibleMovementForInitialGame(){
        GameState gameState = new GameState();
        gameState.reset();

        Movement movement = new Movement();
        movement.setOwnerPositions(gameState.getPlayerPositions(Player.White));
        movement.setOpponentPositions(gameState.getPlayerPositions(Player.Black));

        Set<Move> possibleMoves = new HashSet<Move>();
        for(Position position : gameState.getPlayerPositions(Player.White)){
            Piece piece = gameState.getPieceAt(position);
            possibleMoves.addAll(movement.getPossibleMoves(piece, position));
        }

        assertEquals("", 20, possibleMoves.size());

        //check for white
        for(Move move : TestUtils.getInitialMovesForPlayer(Player.White)){
            assertTrue("Possible moves should contain this move", possibleMoves.contains(move));
        }

        movement.setOpponentPositions(gameState.getPlayerPositions(Player.White));
        movement.setOwnerPositions(gameState.getPlayerPositions(Player.Black));
        possibleMoves = new HashSet<Move>();
        for(Position position : gameState.getPlayerPositions(Player.Black)){
            Piece piece = gameState.getPieceAt(position);
            possibleMoves.addAll(movement.getPossibleMoves(piece, position));
        }

        assertEquals("", 20, possibleMoves.size());

        //check for white
        for(Move move : TestUtils.getInitialMovesForPlayer(Player.Black)){
            assertTrue("Possible moves should contain this move", possibleMoves.contains(move));
        }

    }

    /**
     * Tests for check possible moves if surrounded oner or opponent pieces
     */
    @Test
    public void noMoveIfSurroundedByOwnPieces(){
        List<Piece> pieces = new ArrayList<Piece>(Arrays.asList(new Pawn(Player.White), new King(Player.White), new Queen(Player.White),
                new Rook(Player.White), new Bishop(Player.White)));

        Position startPosition = new Position("d5");
        Movement movement = new Movement();
        movement.setOpponentPositions(Collections.<Position>emptySet());
        movement.setOwnerPositions(TestUtils.getSurroundedPositions(startPosition));
        for(Piece piece : pieces) {
            Assert.assertEquals("Should be no moves if surrounded by own pieces", 0, movement.getPossibleMoves(piece, startPosition).size());
        }

        //knight can jump over all these pieces, so add 8 additional moves
        Assert.assertEquals("Knight should jump over own pieces", 8, movement.getPossibleMoves(new Knight(Player.White), startPosition).size());
        movement.setOpponentPositions(TestUtils.getSurroundedPositions(startPosition));
        movement.setOwnerPositions(Collections.<Position>emptySet());
        for(Piece piece : pieces) {
            Assert.assertTrue("Possible moves if surrounded by opponent pieces", movement.getPossibleMoves(piece, startPosition).size() > 0);
        }

        Assert.assertEquals("Knight should jump over opponent pieces", 8, movement.getPossibleMoves(new Knight(Player.White), startPosition).size());
    }


}