import java.util.Arrays;

/**
 * 数据
 */
public class InsertSortData {

    public enum Type {
        Default,
        NearlyOrdered
    }


    private int[] numbers;
    public int orderedIndex = -1;
    public int currentIndex = -1;
    public int t = -1;

    public InsertSortData(int N, int randomBound, Type dataType) {

        numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
        }
        if (dataType == Type.Default) {
            Arrays.sort(numbers);
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
        if (i < 0 || i >= numbers.length || j < 0 || j >= numbers.length) {
            throw new IndexOutOfBoundsException("Invalid index to access Sort Data.");
        }
        if (t == -1) {
            t = numbers[i];
        }
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
