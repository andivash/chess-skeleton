package chess;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class MoveTest {

    /**
     * Make sure that Move.equals method works correct
     */
    @Test
    public void testEqualityOfMoveObjects() {
        Position posFirst = new Position('a', 1);
        Position posSecond = new Position('a', 2);

        Move one = new Move(posFirst, posSecond);
        Move two = new Move(posSecond, posFirst);
        assertFalse("Different moves should be not equal", one.equals(two));

        Move third = new Move(new Position('a', 1), new Position('a', 2));
        assertEquals("Same moves should equal each other", one, third);

    }

    /**
     * Make sure that Move.hashcode method works correct
     */
    @Test
    public void testHashCode(){
        Position posFirst = new Position('a', 1);
        Position posSecond = new Position('a', 2);

        Move one = new Move(posFirst, posSecond);
        Move two = new Move(posSecond, posFirst);
        Move third = new Move(posFirst, posSecond);

        assertEquals("One object should return same hashcode each time", one.hashCode(), one.hashCode());
        assertEquals("Same objects should return same hashcode ", one.hashCode(), third.hashCode());

    }
}