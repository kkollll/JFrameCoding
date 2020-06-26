public class WinningPrize {

    private double chance = 0.2;
    private int playTime = 5;
    private int N = 1000000;

    public WinningPrize(double chance, int playTime, int N) {
        if (chance < 0.0 || chance > 1.0) {
            throw new IllegalArgumentException("chance must be between 0 and 1");
        }
        if (playTime <= 0 || N <= 0) {
            throw new IllegalArgumentException("playTime or N must be larger than 0");
        }
        this.chance = chance;
        this.playTime = playTime;
        this.N = N;
    }

    public void run() {
        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play()) {
                wins++;
            }
        }
        System.out.println("winning rate: " + (double) wins / N);
    }

    private boolean play() {
        for (int i = 0; i < playTime; i++) {
            if (Math.random() < chance) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        double chance = 0.2;
        int playTime = 5;
        int N = 1000000;

        WinningPrize winningPrize = new WinningPrize(chance, playTime, N);
        winningPrize.run();
        System.out.println(Math.pow(0.8, 5));
    }
}
