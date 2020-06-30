import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // 数据
    private MergeSortData data;
    private static int DELAY = 20;
    private Comparable[] aux;
    // 视图
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, int randomBounds, MergeSortData.Type type) {

        // 初始化数据
        data = new MergeSortData(N, randomBounds, type);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Merge-Sort", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        setData(-1, -1, -1);
        aux = new Comparable[data.N()];
//        mergeSort(0, data.N() - 1);
        // 自底向上归并
        mergeSortBU(data.N() - 1);
        setData(0, data.N() - 1, data.N() - 1);
    }

    private void mergeSortBU(int n) {

        for (int sz = 1; sz <= n; sz = 2 * sz) {

            for (int i = 0; i + sz <= n; i += sz * 2) {
                // 对 arr[i...i + sz - 1] 和 arr[i + sz....i + 2 * sz - 1]进行归并
                merge(i, i + sz - 1, Math.min(i + 2 * sz - 1, n));
            }
        }

    }

    private void setData(int l, int r, int mergeIndex) {
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private void mergeSort(int l, int r) {

        if (l >= r) {
            return;
        }

        setData(l, r, -1);

        int mid = l + ((r - l) >> 1);
        mergeSort(l, mid);
        mergeSort(mid + 1, r);
        // 改进：有序的时候不用merge 但复杂度依然是nlogn, 对于有序的情况多的时候建议加上这层判断
        // mid左右都是有序的
        if (less(data.numbers[mid + 1], data.numbers[mid])) {
            merge(l, mid, r);
        }

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private void merge(int l, int mid, int r) {

        System.arraycopy(data.numbers, l, aux, l, r - l + 1);

        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {

            if (i > mid) {
                data.numbers[k] = aux[j++];
            } else if (j > r) {
                data.numbers[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                data.numbers[k] = aux[i++];
            } else {
                data.numbers[k] = aux[j++];
            }

            setData(l, r, k);
        }
    }

    private boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
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
        int randomBounds = 500;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, randomBounds, MergeSortData.Type.NearlyOrdered);
    }
}
