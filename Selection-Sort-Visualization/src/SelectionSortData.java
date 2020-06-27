/**
 * 数据
 */
public class SelectionSortData {

    private int[] numbers;
    // 有序的
    public int orderedIndex = -1;
    // 最小元素索引
    public int currentMinIndex = -1;
    // 当前正在比较的元素索引
    public int currentCompareIndex = -1;

    public SelectionSortData(int N, int randomBound) {

        numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
        }
    }

    public int N() {
        return numbers.length;
    }

    public int get(int index) {
        if (index < 0 || index >= numbers.length) {
            throw new IndexOutOfBoundsException("Invalid index to access Sort Data.");
        }

        return numbers[index];
    }

    public void swap(int i, int j) {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
