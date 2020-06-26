import java.awt.*;

public class MonteCarloExperiment {

    private int squareSide;
    private int N;
    private int interval = 100;

    public MonteCarloExperiment(int squareSide, int N) {

        if (squareSide <= 0 || N <= 0) {
            throw new IllegalArgumentException("squareSide and N must larger than 0");
        }
        this.squareSide = squareSide;
        this.N = N;
    }

    public void setInterval(int interval) {
        if (interval <= 0) {
            throw new IllegalArgumentException("interval must larger than 0");
        }
        this.interval = interval;
    }

    public void run() {
        Circle circle = new Circle(squareSide / 2, squareSide / 2, squareSide / 2);
        MonteCarloPiData data = new MonteCarloPiData(circle);
        for (int i = 0; i < N; i++) {
            if (i % interval == 0) {
                System.out.println(data.estimastePi());
            }
            int x = (int) (Math.random() * squareSide);
            int y = (int) (Math.random() * squareSide);
            data.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args) {

        int squareSide = 800;
        int N = 1000000;
        MonteCarloExperiment experiment = new MonteCarloExperiment(squareSide, N);
        experiment.setInterval(10000);
        experiment.run();

    }
}
