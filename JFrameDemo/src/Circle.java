import lombok.Getter;

import java.awt.*;

@Getter
public class Circle {

    // 圆心位置
    public int x, y;
    public Color color = Color.blue;
    private int r;
    // 速度
    public int vx, vy;
    public boolean isFilled = true;

    public Circle(int x, int y, int r, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public void move(int minx, int miny, int maxx, int maxy) {
        x += vx;
        y += vy;
        checkCollision(minx, miny, maxx, maxy);
    }

    /**
     * 碰撞检测
     *
     * @param minx
     * @param miny
     * @param maxx
     * @param maxy
     */
    private void checkCollision(int minx, int miny, int maxx, int maxy) {

        if (x - r < minx) {
            x = r;
            vx = -vx;
        }
        if (x + r > maxx) {
            x = maxx - r;
            vx = -vx;
        }
        if (y - r < miny) {
            y = r;
            vy = -vy;
        }
        if (y + r > maxy) {
            y = maxy - r;
            vy = -vy;
        }
    }

    public boolean contains(Point p) {
        return Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2) <= Math.pow(r, 2);
    }
}
