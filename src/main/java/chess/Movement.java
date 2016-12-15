package chess;

import chess.pieces.*;

import java.util.LinkedList;
import java.util.List;
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

    public void setOwnerPositions(Set<Position> ownerPositions) {
        this.ownerPositions = ownerPositions;
    }

    public void setOpponentPositions(Set<Position> opponentPositions) {
        this.opponentPositions = opponentPositions;
    }


    public List<Move> getPossibleMoves(Piece piece, Position position){
        //add owner positions and opposit positions
        List<Move> resultMovesList = new LinkedList<Move>();
        int radius = (piece.getMoveSettings() == null) ? MAX_ITERATION : piece.getMoveSettings().getRadius();

        if(piece instanceof StraightMovement){
            //move straight top
            resultMovesList = move(position, resultMovesList, 0, 1, radius);

            //move straight down
            resultMovesList = move(position, resultMovesList, 0, -1, radius);

            //move straight left
            resultMovesList = move(position, resultMovesList, -1, 0, radius);

            //move straight right
            resultMovesList = move(position, resultMovesList, 1, 0, radius);
        }

        if(piece instanceof DiagonalMovement){
            //move diagonal left top
            resultMovesList = move(position, resultMovesList, -1, 1, radius);

            //move diagonal right top
            resultMovesList = move(position, resultMovesList, 1, 1, radius);

            //move diagonal left down
            resultMovesList = move(position, resultMovesList, -1, -1, radius);

            //move diagonal right down
            resultMovesList = move(position, resultMovesList, 1, -1, radius);
        }

        if(piece instanceof KnightMovement){
            resultMovesList = knightMoves(position, resultMovesList);
        }

        //TODO looks ugly, need to redesign in more elegant way
        if(piece instanceof Pawn){
            int direction = (Player.White == piece.getOwner())? 1 : -1;
            radius = 1;
            //special case for Pawn from initial line
            if(Player.White == piece.getOwner() && position.getRow() == 2) radius = 2;
            if(Player.Black == piece.getOwner() && position.getRow() == 7) radius = 2;


            resultMovesList = move(position, resultMovesList, 0, direction, radius);
        }

        return resultMovesList;
    }

    public List<Move> move(Position position, List<Move> resultMove, int columnIncrement, int rowIncrement, int radius){

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
    public List<Move> knightMoves(Position position, List<Move> resultMove){

        int[] rowInc = new int[]{1, 1, 2, 2, -1, -1, -2, -2};
        int[] colInc = new int[]{-2, 2, -1, 1, -2, 2, -1, 1};

        for(int i=0; i<rowInc.length; i++){
            resultMove = move(position, resultMove, colInc[i], rowInc[i], ONE_ITERATION);
        }
        return resultMove;
    }


}
