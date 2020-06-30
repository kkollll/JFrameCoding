import java.util.Arrays;

public class QuickSortData {


    public Comparable[] numbers;
    public int l, r, curPivot, curElement;
    public boolean[] fixedPivot;

    public QuickSortData(int N, int randomBound, Type dataType) {

        numbers = new Comparable[N];
        fixedPivot = new boolean[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
            fixedPivot[i] = false;
        }
        if (dataType == Type.Default) {
            Arrays.sort(numbers);
        }
    }

    public int N() {
        return numbers.length;
    }

    public Comparable get(int index) {
        if (index < 0 || index >= numbers.length) {
            throw new IndexOutOfBoundsException("Invalid index to access Sort Data.");
        }

        return numbers[index];
    }
}
