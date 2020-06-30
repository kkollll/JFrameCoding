import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // 数据
    private QuickSortData data;
    private static final int DELAY = 20;
    // 视图
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, int randomBound, Type type) {

        // 初始化数据
        data = new QuickSortData(N, randomBound, type);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Simple-Quick-Sort", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        frame.render(data);
        setData(-1, -1, -1, -1, -1, -1);
        quickSort(0, data.N() - 1);
        setData(-1, -1, -1, -1, -1, -1);
    }

    private void quickSort(int l, int r) {

        if (l > r) {
            return;
        }
        if (l == r) {
            setData(l, r, l, -1, -1, -1);
            return;
        }
        setData(l, r, -1, -1, -1, -1);
        int p = partition(l, r);
        quickSort(l, p - 1);
        quickSort(p + 1, r);
    }

    private int partition(int l, int r) {

        int p = (int) (Math.random() * (r - l + 1)) + l;
        setData(l, r, -1, p, -1, -1);
        swap(l, p);
        Comparable v = data.get(l);
        setData(l, r, -1, l, -1, -1);

        int i = l + 1, j = r;
        setData(l, r, -1, l, i, j);
        while (true) {
            while (i <= r && less(data.get(i), v)) {
                i++;
                setData(l, r, -1, l, i, j);
            }

            while (j >= l + 1 && less(v, data.get(j))) {
                j--;
                setData(l, r, -1, l, i, j);
            }

            if (i > j) {
                break;
            }
            if (equal(data.get(i),data.get(j))) {
                swap(i, j);
            }
            i++;
            j--;
            setData(l, r, -1, l, i, j);
        }
        if (equal(data.get(l),data.get(j))) {
            swap(l, j);
        }
        setData(l, r, j, l, i, j);
        return j;
    }

    private boolean equal(Comparable i, Comparable j) {
        return i.compareTo(j) != 0;
    }


    private void swap(int i, int j) {
        Comparable t = data.get(i);
        data.numbers[i] = data.numbers[j];
        data.numbers[j] = t;
    }

    private boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR) {

        data.l = l;
        data.r = r;
        if (fixedPivot != -1) {
            data.fixedPivot[fixedPivot] = true;
        }
        data.curL = curL;
        data.curR = curR;
        data.curPivot = curPivot;

        frame.repaint();
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;
        int randomBound = 800;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, randomBound, Type.Default);
    }
}
