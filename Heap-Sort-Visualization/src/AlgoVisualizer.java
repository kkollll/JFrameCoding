import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // 数据
    private HeapSortData data;
    private static final int DELAY = 20;
    // 视图
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, int randomBound, Type type) {

        // 初始化数据
        data = new HeapSortData(N, randomBound, type);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        setData(data.N());

        for (int i = (data.N() - 1 - 1) >> 1; i >= 0; i--) {
            siftDown(data.N(), i);
        }

        for (int i = data.N() - 1; i > 0; i--) {
            swap(0, i);
            siftDown(i, 0);
            setData(i);
        }

        setData(0);
    }

    private void siftDown(int n, int k) {

        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && less(data.get(j), data.get(j + 1))) {
                j++;
            }
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }

            swap(k, j);
            setData(data.heapIndex);

            k = j;
        }
    }

    private void swap(int k, int j) {
        Comparable t = data.get(k);
        data.numbers[k] = data.numbers[j];
        data.numbers[j] = t;
    }

    private boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    private void setData(int heapIndex) {

        data.heapIndex = heapIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 50;
        int randomBound = 800;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, randomBound, Type.Default);
    }

}
