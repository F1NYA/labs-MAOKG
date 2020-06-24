package Drawer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class JFXApp extends JPanel  implements ActionListener {
    private static int maxWidth;
    private static int maxHeight;

    Timer timer;
 
    private double angle = 0;

    private double scale = 1;
    private double delta = 0.01;

    public JFXApp() {
        timer = new Timer(50, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setBackground(Color.GREEN);
        g2d.clearRect(0, 0, maxWidth, maxHeight);

        BasicStroke bs1 = new BasicStroke(15, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs1);
        g2d.drawRect(15, 15, maxWidth-30, maxHeight-30);

        g2d.translate( 150 , 150 );

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        
        bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs1);
        int[] xPoints = new int[] {100, 130, 220, 250};
        int[] yPoints = new int[] {100, 200, 200, 100};
 

        GradientPaint gp = new GradientPaint(1, 25,
                Color.PINK, 20, 2, Color.YELLOW, true);
     
        double[][] points =
        	{{ 140, 180 }, { 210, 180 },
        	{ 175, 105 }, { 140, 180 }};
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++)
            triangle.lineTo(points[k][0], points[k][1]);
        triangle.closePath();

        g2d.setColor(Color.BLUE);
      
        g2d.rotate(angle, 220, 200);

        g2d.scale(scale, scale);

        g2d.setColor(Color.RED);
        g2d.drawPolyline(xPoints, yPoints, xPoints.length);

        g2d.setPaint(gp);
        g2d.fill(triangle);

        g2d.setColor(Color.BLUE);
        g2d.drawLine(100, 60, 150, 60);
        g2d.drawLine(200, 60, 250, 60);


    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("LAB 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null); 
        frame.setResizable(false);
        frame.add(new JFXApp());
        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    public void actionPerformed(ActionEvent e) {
        if (scale < 0.1 || scale > 0.99) {
            delta = -delta;
        }

        scale += delta;
        angle += 0.05;

        repaint();
    }
}