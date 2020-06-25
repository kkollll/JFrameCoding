import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

@Getter
public class AlgoFrame extends JFrame {

    private static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private int cavasWidth;
    private int cavasHeight;

    public AlgoFrame(String title, int cavasWidth, int cavasHeight) throws HeadlessException {
        super(title);
        this.cavasWidth = cavasWidth;
        this.cavasHeight = cavasHeight;
        setSize(cavasWidth, cavasHeight);
        AlgoCanvas canvas = new AlgoCanvas();
        // 画布
        setContentPane(canvas);
        pack();

        setVisible(true);
        // 关闭退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // resize
        setResizable(false);
    }

    public AlgoFrame(String title) throws HeadlessException {
        this(title, screen.width, screen.height);
    }

    private Circle[] circles;
    public void render(Circle[] circles) {
        this.circles = circles;
        repaint();
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            // 双缓存
            super(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.addRenderingHints(hints);

            // 设置笔画宽度
            AlgoVisHelper.setStrokeWidth(g2d, 1);
            AlgoVisHelper.setColor(g2d, Color.BLUE);
            for (Circle circle : circles) {
                AlgoVisHelper.strokeCircle(g2d, circle.x, circle.y, circle.getR());
            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(cavasWidth, cavasHeight);
        }
    }
}
