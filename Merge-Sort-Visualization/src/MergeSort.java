public class MergeSort {

    private static Comparable[] aux;
    private static Comparable t = -1;

    private MergeSort() {
    }


    public static void mergeSort(Comparable[] arr) {

        aux = new Comparable[arr.length];
        mergeSort(arr, 0, arr.length - 1);


    }

    private static void mergeSort(Comparable[] arr, int l, int r) {

        if (l >= r) {
            return;
        }
        // 由于插入排序复杂度n^2, 归并排序复杂度nlogn前面都是有个系数的
        // 当n比较小的时候插入排序要快一点
//        if (r - l <= 50) {
//            insertSort(arr, l, r);
//            return;
//        }
        int mid = l + ((r - l) >> 1);
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        if (less(arr[mid + 1], arr[mid])) {
            merge(arr, l, mid, r);
        }
    }

    private static void insertSort(Comparable[] arr, int l, int r) {

        for (int i = l + 1; i <= r; i++) {

            Comparable e = arr[i];
            int j;
            for (j = i; j > l && less(e, arr[j - 1]); j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    private static void swap(Comparable[] arr, int i, int j) {

        if (t.compareTo(-1) == 0) {
            t = arr[j];
        }

        arr[j] = arr[i];
        arr[i] = t;
    }

    private static void merge(Comparable[] arr, int l, int mid, int r) {

        System.arraycopy(arr, l, aux, l, r - l + 1);

        int i = l, j = mid + 1;

        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > r) {
                arr[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }
    }

    private static boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    public static String toString(Comparable[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(',');
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        Comparable[] arr = new Comparable[1500];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        long t1 = System.nanoTime();
        MergeSort.mergeSort(arr);
        long t2 = System.nanoTime();
        System.out.println((double)(t2 - t1) / 1000000000);
    }
}
