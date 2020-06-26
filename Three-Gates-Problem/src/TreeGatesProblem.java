public class TreeGatesProblem {

    private int N;

    public TreeGatesProblem(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be larger than 0");
        }
        this.N = N;
    }

    public void run(boolean changeDoor) {

        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play(changeDoor)) {
                wins++;
            }
        } 
        System.out.println(changeDoor ? "Change" : "Not Change");
        System.out.println();
        System.out.println("winning rate: " + (double) wins / N);
    }

    private boolean play(boolean changeDoor) {

        // Door 0,1,2
        int prizeDoor = (int) (Math.random() * 3);
        int playerChoice = (int) (Math.random() * 3);
        if (playerChoice == prizeDoor) {
            return changeDoor ? false : true;
        } else {
            return changeDoor ? true : false;
        }
    }

    public static void main(String[] args) {
        int N = 100000;
        TreeGatesProblem exp = new TreeGatesProblem(N);
        exp.run(false);
        exp.run(true);
    }
}
