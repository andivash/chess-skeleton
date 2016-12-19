package chess;

import java.util.HashSet;
import java.util.Set;

/**
 * Util class for preparing data for tests
 * Created by andi on 12/17/2016.
 */
public class TestUtils {

    /**
     * Finds initial moves for given player
     * @param player - given player
     * @return {@code Set} of possible  moves
     */
    public static Set<Move> getInitialMovesForPlayer(Player player){
        GameState gameState = new GameState();
        gameState.reset();
        Set<Move> resultSet = new HashSet();
        int start = (player == Player.White)?2:7;
        int inc = (player == Player.White)?1:-1;
        int pawnStartRow = start;
        int pawnEndRow = start +inc;
        int pawnLongEndRow = pawnEndRow+inc;
        int knightStartRow = start-inc;
        int knightEndRow = knightStartRow+2*inc;
        for(char c = 'a'; c<='h'; c++){
            resultSet.add(new Move(new Position(c+""+pawnStartRow), new Position(c+""+pawnEndRow)));
            resultSet.add(new Move(new Position(c+""+pawnStartRow), new Position(c+""+pawnLongEndRow)));
        }
        resultSet.add(new Move(new Position("b"+knightStartRow), new Position("a"+knightEndRow)));
        resultSet.add(new Move(new Position("b"+knightStartRow), new Position("c"+knightEndRow)));
        resultSet.add(new Move(new Position("g"+knightStartRow), new Position("h"+knightEndRow)));
        resultSet.add(new Move(new Position("g"+knightStartRow), new Position("f"+knightEndRow)));

        return resultSet;
    }

    /**
     * Returns surrounded positions for given start position
     * @param position
     * @return {@code Set} of surrounded positions
     */
    public static Set<Position> getSurroundedPositions(Position position){
        Set<Position> resultPositions = new HashSet<Position>();
        int[] columnChanges = new int[]{1, 0, -1};
        int[] rowChanges = new int[]{-1, 0, 1};
        for(int i=0; i< columnChanges.length; i++){
            for(int j=0; j< rowChanges.length; j++){
                char tmpColumnPos = (char) (position.getColumn()+columnChanges[i]);
                int tmpRowPos = position.getRow()+rowChanges[j];
                if(tmpColumnPos == position.getColumn() && tmpRowPos == position.getRow()) continue;

                if(Position.isValidPosition(tmpColumnPos, tmpRowPos)){
                    resultPositions.add(new Position(tmpColumnPos, tmpRowPos));
                }
            }
        }

        return resultPositions;
    }

    /**
     * Generates set of positions, that cover all chess board
     * @return {@code Set} of Positions for whole board
     */
    public static Set<Position> getFullBoardPositions(){
        Set<Position> positions = new HashSet<Position>();

        for(char c = 'a'; c<='h'; c++){
            for(int i=1; i<=8; i++){
                Position position = new Position(c+""+i);
                positions.add(position);
            }
        }
        return positions;
    }

    /**
     * Generates Set of Moves from start position to destination position
     * @param startPosition start position
     * @param destinations - string of destination positions separated by spaces
     * @return {@code Set} of Moves with given start position and destination positions
     */
    public static Set<Move> getMovesFromStartPosition(Position startPosition, String destinations){
        Set<Move> resultMoves = new HashSet<Move>();
        for(String str : destinations.split(" ")){
            resultMoves.add(new Move(startPosition, new Position(str)));
        }
        return resultMoves;
    }

}
