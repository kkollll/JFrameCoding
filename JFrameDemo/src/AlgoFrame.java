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

    private class AlgoCanvas extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // 设置笔画宽度
            AlgoVisHelper.setStrokeWidth(g2d, 5);

            // 设置颜色
            AlgoVisHelper.setColor(g2d, Color.GREEN);
            // 设置一个圆
            AlgoVisHelper.fillCircle(g2d, cavasWidth / 2, cavasHeight / 2, 200);

            // 第二个圆
            AlgoVisHelper.setColor(g2d, Color.RED);
            AlgoVisHelper.strokeCircle(g2d, cavasWidth / 2, cavasHeight / 2, 200);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(cavasWidth, cavasHeight);
        }
    }
}
