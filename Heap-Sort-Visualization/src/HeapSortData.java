import java.util.Arrays;

public class HeapSortData {

    public Comparable[] numbers;
    public int heapIndex;

    public HeapSortData(int N, int randomBound, Type dataType) {

        numbers = new Comparable[N];

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

    public Comparable get(int index) {
        if (index < 0 || index >= numbers.length){
            throw new IndexOutOfBoundsException("Invalid index to access Sort Data.");
        }

        return numbers[index];
    }
}
