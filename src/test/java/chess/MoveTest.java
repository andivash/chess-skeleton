package chess;

import org.junit.Test;
import static junit.framework.Assert.*;

public class MoveTest {

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
}