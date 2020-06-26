import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        int N = 1000000;
        int success = 0;
        for (int i = 0; i < N; i++) {
            if (N % 100 == 0) {
                System.out.println(success / 1000000.0);
            }
            ArrayList<Integer> arr = new ArrayList<>(3);
            for (int k = 0; k < 3; k++) {
                arr.add(0);
            }
            arr.set((int) (Math.random() * 3), 1);
            int bb = (int) (Math.random() * 3);
            int p = arr.get(bb);

            for (int k = 0; k < 3; k++) {
                if (k != p && arr.get(k) == 0) {
                    arr.remove(k);
                    arr.remove(p);
                    break;
                }
            }

            if (arr.get(0) == 1) {

                success++;
            }
        }
    }
}
