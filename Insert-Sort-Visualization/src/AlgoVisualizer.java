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

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, int randomBounds,InsertSortData.Type type) {

        // 初始化数据
        data = new InsertSortData(N, randomBounds, type);

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
            setData(index - 1, index);
//            insertSort(data, index++, index);
            //二分法
            rank(data, index++);
            data.t = -1;
        }
        setData(data.N(), -1);
    }

    private void rank(InsertSortData data, int index) {
        int l = 0;
        int h = index - 1;
        while (l <= h) {
            int mid = (l + h) / 2;
            if (data.get(index) <= data.get(mid)) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        for (int i = index; i > l; i--) {
            data.swap(i, i - 1);
            setData(index + 1, i);
        }
    }

    public void setData(int orderedIndex, int currentIndex) {
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private InsertSortData insertSort(InsertSortData data, int index, int orderedIndex) {
        if (index == 0) {
            return data;
        }
        if (data.get(index) < data.get(index - 1)) {
            data.swap(index, index - 1);
            setData(orderedIndex, index - 1);
        }
        return insertSort(data, index - 1, orderedIndex);
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
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, randomBounds, InsertSortData.Type.NearlyOrdered);
    }
}
