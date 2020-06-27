import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // 数据
    private InsertSortData data;
    private static int DELAY = 20;
    // 视图
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, int randomBounds) {

        // 初始化数据
        data = new InsertSortData(N, randomBounds);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Insert-Sort", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        int index = 1;
        setData(0, -1);
        while (index < data.N()) {
            setData(index, index);
            InsertSort(data, index++, index);
        }
        setData(data.N(), -1);
    }

    public void setData(int orderedIndex, int currentIndex) {
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private InsertSortData InsertSort(InsertSortData data, int index, int orderedIndex) {
        if (index == 0) {
            return data;
        }
        if (data.get(index) < data.get(index - 1)) {
            data.swap(index, index - 1);
            setData(orderedIndex + 1, index - 1);
        }
        return InsertSort(data, index - 1, orderedIndex);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;
        int randomBounds = 500;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, randomBounds);
    }
}
