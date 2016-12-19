package chess;

import chess.pieces.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for basic movement operations
 *
 * Created by andi on 12/15/2016.
 */
public class Movement {

    private Set<Position> ownerPositions;
    private Set<Position> opponentPositions;

    private final int MAX_ITERATION = 8;
    private final int ONE_ITERATION = 1;
    private final int WHITE_PAWN_START_ROW = 2;
    private final int BLACK_PAWN_START_ROW = 7;
    private final int PAWN_START_MOVE_RADIUS = 2;

    {
        this.ownerPositions = Collections.emptySet();
        this.opponentPositions = Collections.emptySet();
    }

    public Set<Move> getPossibleMoves(Piece piece, Position position){
        //add owner positions and opposit positions
        Set<Move> resultMovesSet = new HashSet<Move>();
        int radius = (piece instanceof OneStepMovable) ? ONE_ITERATION : MAX_ITERATION;

        if(piece instanceof StraightMovement){
            //move straight top
            resultMovesSet.addAll(move(position, 0, 1, radius));

            //move straight down
            resultMovesSet.addAll(move(position, 0, -1, radius));

            //move straight left
            resultMovesSet.addAll(move(position, -1, 0, radius));

            //move straight right
            resultMovesSet.addAll(move(position, 1, 0, radius));
        }

        if(piece instanceof DiagonalMovement){
            //move diagonal left top
            resultMovesSet.addAll(move(position, -1, 1, radius));

            //move diagonal right top
            resultMovesSet.addAll(move(position, 1, 1, radius));

            //move diagonal left down
            resultMovesSet.addAll(move(position, -1, -1, radius));

            //move diagonal right down
            resultMovesSet.addAll(move(position, 1, -1, radius));
        }

        if(piece instanceof KnightMovement){
            resultMovesSet.addAll(knightMoves(position));
        }

        //TODO looks ugly, need to redesign in more elegant way
        if(piece instanceof Pawn){
            int direction = (Player.White == piece.getOwner())? 1 : -1;

            //special case for Pawn from initial line
            if(Player.White == piece.getOwner() && position.getRow() == WHITE_PAWN_START_ROW) radius = PAWN_START_MOVE_RADIUS;
            if(Player.Black == piece.getOwner() && position.getRow() == BLACK_PAWN_START_ROW) radius = PAWN_START_MOVE_RADIUS;


            resultMovesSet.addAll(move(position, 0, direction, radius));
        }

        return resultMovesSet;
    }

    public Set<Move> move(Position position, int columnIncrement, int rowIncrement, int radius){

        Set<Move> resultMove = new HashSet<Move>();
        int row = position.getRow();
        char column = position.getColumn();
        int iteration = 1;

        row += rowIncrement;
        column += columnIncrement;
        boolean canContinue = true;

        while(Position.isValidPosition(column,row) && canContinue && iteration<= radius){

            Position possiblePosition = new Position(column,row);
            if(ownerPositions.contains(possiblePosition)){
                canContinue = false;
            }
            else{
                resultMove.add(new Move(position, possiblePosition));
            }

            if(opponentPositions.contains(possiblePosition)){
                canContinue = false;
            }

            row += rowIncrement;
            column += columnIncrement;
            iteration ++;
        }

        return resultMove;
    }

    //special case for Knight
    public Set<Move> knightMoves(Position position){

        Set<Move> resultMove = new HashSet<Move>();
        int[] rowInc = new int[]{1, 1, 2, 2, -1, -1, -2, -2};
        int[] colInc = new int[]{-2, 2, -1, 1, -2, 2, -1, 1};

        for(int i=0; i<rowInc.length; i++){
            resultMove.addAll(move(position, colInc[i], rowInc[i], ONE_ITERATION));
        }
        return resultMove;
    }

    public void setOpponentPositions(Set<Position> opponentPositions){
        this.opponentPositions = opponentPositions;
    }

    public void setOwnerPositions(Set<Position> ownerPosition){
        this.ownerPositions = ownerPosition;
    }
}
