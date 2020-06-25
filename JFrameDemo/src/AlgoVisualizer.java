import java.awt.*;
import java.awt.event.*;

public class AlgoVisualizer {

    private Circle[] circles;
    private AlgoFrame frame;
    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        circles = new Circle[N];
        int R = 50;
        for (int i = 0; i < N; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int) (Math.random() * (sceneHeight - 2 * R)) + R;
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> {
                frame.addKeyListener(new algoKeyListener());
                frame.addMouseListener(new algoMouseListener());
                run();
            }).start();
        });
    }

    private void run() {
        for (; ; ) {
            // 绘制数据
            frame.render(circles);
            AlgoVisHelper.sleep(20);
            if (isAnimated) {
                for (Circle circle : circles) {
                    circle.move(0, 0, frame.getCavasWidth(), frame.getCavasHeight());
                }
            }
        }
    }

    /**
     * 键盘事件
     */
    private class algoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }

    /**
     * 键盘事件
     */
    private class algoMouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            e.translatePoint(-3, -(frame.getBounds().height - frame.getCavasHeight()) + 3);
            System.out.println(e.getPoint());
            for (Circle circle : circles) {
                if (circle.contains(e.getPoint())) {
                    circle.isFilled = !circle.isFilled;
                }
            }
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }

}
