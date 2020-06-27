import com.sun.xml.internal.ws.util.xml.CDATA;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // 数据
    private SelectionSortData data;
    private static int DELAY = 1;
    // 视图
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, int randomBound) {

        // 初始化数据
        data = new SelectionSortData(N, randomBound);
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Select-Sort", sceneWidth, sceneHeight);
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

        int index = 0;
        int minIndex = 0;
        setData(0, -1, -1);
        while (minIndex < data.N()) {
            int p = selectSort(data, index, minIndex, minIndex);
            if (p != minIndex) {
                data.swap(p, minIndex);
            }
            setData(index, -1, -1);
            minIndex++;
            index++;
        }
        setData(data.N(), -1, -1);
    }

    private void setData(int orderedIndex, int currentMinIndex, int currentCompareIndex) {
        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private int selectSort(SelectionSortData data, int index, int minIndex, int ret) {

        if (index > data.N() - 1) {
            return ret;
        }
        setData(minIndex, index, ret);
        if (data.get(index) < data.get(ret)) {
            ret = index;
            setData(minIndex, index, ret);
        }

        return selectSort(data, index + 1, minIndex, ret);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 200;
        int randomBound = 500;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, randomBound);
    }
}
