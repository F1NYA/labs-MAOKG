import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.*;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AnimationCat implements ActionListener, KeyListener {
    private final Button go;
    private final TransformGroup wholeCat;
    private final Transform3D translateTransform;
    private final Transform3D rotateTransformX;
    private final Transform3D rotateTransformY;
    private final Transform3D rotateTransformZ;
    private final Timer timer;
    private final JFrame mainFrame;
    private float sign = 1.0f;
    private final float zoom = 0.3f;
    private final float xloc = -0.3f;
    private float yloc = -1.3f;
    private final float zloc = 0.0f;

    public AnimationCat(TransformGroup wholeCat, Transform3D trans, JFrame frame) {
        go = new Button("Go");

        this.wholeCat = wholeCat;
        this.translateTransform = trans;
        this.mainFrame = frame;

        rotateTransformX = new Transform3D();
        rotateTransformY = new Transform3D();
        rotateTransformZ = new Transform3D();

        Cat.canvas.addKeyListener(this);
        timer = new Timer(100, this);

        Panel p = new Panel();
        p.add(go);
        mainFrame.add("North", p);
        go.addActionListener(this);
        go.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // start timer when button is pressed
        if (e.getSource() == go) {
            if (!timer.isRunning()) {
                timer.start();
                go.setLabel("Stop");
            } else {
                timer.stop();
                go.setLabel("Go");
            }
        } else {
            Move();
            translateTransform.setScale(new Vector3d(zoom, zoom, zoom));
            translateTransform.setTranslation(new Vector3f(xloc, yloc, zloc));
            wholeCat.setTransform(translateTransform);
        }
    }

    private void Move() {
        yloc += 0.1 * sign;
        if (Math.abs(yloc * 2) >= 3.0) {
            sign = -1.0f * sign;
            rotateTransformY.rotX(Math.PI);
            translateTransform.mul(rotateTransformY);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Invoked when a key has been typed.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == '1') {
            rotateTransformX.rotX(Math.PI / 2);
            translateTransform.mul(rotateTransformX);
        }
        if (e.getKeyChar() == '2') {
            rotateTransformY.rotY(Math.PI / 2);
            translateTransform.mul(rotateTransformY);
        }
        if (e.getKeyChar() == '3') {
            rotateTransformZ.rotZ(Math.PI / 2);
            translateTransform.mul(rotateTransformZ);
        }
        if (e.getKeyChar() == '0') {
            rotateTransformY.rotY(Math.PI / 2.8);
            translateTransform.mul(rotateTransformY);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Invoked when a key has been released.
    }

}
