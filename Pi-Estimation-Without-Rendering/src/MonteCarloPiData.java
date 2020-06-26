import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;

public class MonteCarloPiData {

    private ArrayList<Point> points;
    @Getter
    private Circle circle;
    private int insideCircle = 0;

    public MonteCarloPiData(Circle circle) {
        this.circle = circle;
        points = new ArrayList<>();
    }

    public Point getPoint(int i) {
        if (i < 0 || i >= points.size()) {
            throw new IllegalArgumentException("out of bound in getPoind!");
        }
        return points.get(i);
    }
    public int getPointNum() {
        return points.size();
    }
    public void addPoint(Point p) {
        points.add(p);
        if (circle.contains(p)) {
            insideCircle++;
        }
    }

    public double estimastePi() {
        if (points.size() == 0) {
            return 0;
        }
        int circleArea = insideCircle;
        int squareArea = points.size();
        return  4 * (double) circleArea / squareArea;
    }
}
