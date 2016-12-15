package chess;

/**
 * Class for containing additional movement settings, such as radius, direction, etc
 * Created by andi on 12/15/2016.
 */

enum Directions{
    All, FORWARD, BACK, LEFT, RIGHT
}

public class MoveSettings {
    private int radius;
    private Directions directions;

    public MoveSettings(int radius){
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
