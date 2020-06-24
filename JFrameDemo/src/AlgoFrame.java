import lombok.Getter;
import javax.swing.*;
import java.awt.*;

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
        setContentPane(canvas);
        pack();

        setVisible(true);
        setSize(cavasWidth, cavasHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public AlgoFrame(String title) throws HeadlessException {
        this(title, screen.width, screen.height);
    }

    private class AlgoCanvas extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            g.drawOval(50, 75, 300, 300);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(cavasWidth, cavasHeight);
        }
    }
}
