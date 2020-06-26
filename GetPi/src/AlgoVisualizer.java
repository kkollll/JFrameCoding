import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class AlgoVisualizer {

    private int N;
    private static int DELAY = 40;
    // 数据
    private MonteCarloPiData data;
    // 视图
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        if (sceneHeight != sceneWidth) {
            throw new IllegalArgumentException("This demo must run in a square!");
        }

        // 初始化数据
        Circle circle = new Circle(sceneWidth / 2, sceneHeight / 2, sceneHeight / 2);
        data = new MonteCarloPiData(circle);
        this.N = N;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Get Pi with Monte Carlo", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        for (int i = 0; i < N; i++) {
            if (i % 100 == 0) {
                frame.render(data);
                AlgoVisHelper.pause(DELAY);
                System.out.println(data.estimastePi());
            }
            int x = (int) (Math.random() * frame.getCanvasWidth());
            int y = (int) (Math.random() * frame.getCanvasHeight());

            data.addPoint(new Point(x, y));
        }
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100000;

//        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
