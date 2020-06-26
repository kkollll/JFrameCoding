import lombok.Getter;

import java.awt.*;

@Getter
public class Circle {

    private int x;
    private int y;
    private int r;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public boolean contains(Point p) {
        return Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2) <= Math.pow(r, 2);
    }
}
