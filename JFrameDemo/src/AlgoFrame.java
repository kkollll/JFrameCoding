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
        AlgoCanvas canvas = new AlgoCanvas();
        // 画布
        setContentPane(canvas);
        pack();

        setVisible(true);
        setSize(cavasWidth, cavasHeight);
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
            int strkeWidth = 10;
            g2d.setStroke(new BasicStroke(strkeWidth));
            g2d.setColor(Color.RED);
            // 设置一个圆
            Ellipse2D circle = new Ellipse2D.Double(50, 50, 300, 300);

            g2d.draw(circle);

            g2d.setColor(Color.GREEN);
            circle = new Ellipse2D.Double(50, 50, 300, 300);
            g2d.fill(circle);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(cavasWidth, cavasHeight);
        }
    }
}
