package chess;

/**
 * Class for processing move by saving start and end positions
 * Created by andi on 12/15/2016.
 */
public class Move {

    private Position startPosition;
    private Position endPosition;

    public Move(Position start, Position end){
        this.startPosition = start;
        this.endPosition = end;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Position end) {
        this.endPosition = end;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Position start) {
        this.startPosition = start;
    }

    @Override
    public String toString(){
        return startPosition + " " + endPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (!startPosition.equals(move.getStartPosition())) return false;

        if (!endPosition.equals(move.getEndPosition())) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = 56;
        if(startPosition != null) result *= startPosition.hashCode();
        if(endPosition != null) result *= endPosition.hashCode();

        return result;
    }
}
