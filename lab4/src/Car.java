import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Car implements ActionListener {
    private final TransformGroup carTransformGroup = new TransformGroup();
    private final Transform3D carTransform3D = new Transform3D();

    private float angle = 0;

    private Car() throws IOException {
        Timer timer = new Timer(50, this);
        timer.start();

        BranchGroup scene = createSceneGraph();

        SimpleUniverse u = new SimpleUniverse();
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(scene);
    }

    public static void main(String[] args) throws IOException {
        new Car();
    }

    private BranchGroup createSceneGraph() throws IOException {
        BranchGroup root = new BranchGroup();

        carTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        emitCar();

        root.addChild(carTransformGroup);

        Background background = new Background(new Color3f(1.0f, 1.0f, 1.0f));
        BoundingSphere sphere = new BoundingSphere(new Point3d(0, 0, 0), 100000);
        background.setApplicationBounds(sphere);
        root.addChild(background);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        DirectionalLight light = new DirectionalLight(
                new Color3f(1.0f, 0.5f, 0.4f),
                new Vector3f(.8f, .8f, .0f)
        );

        light.setInfluencingBounds(bounds);
        root.addChild(light);

        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);

        root.addChild(ambientLightNode);

        return root;
    }

    private void emitCar() throws IOException {
        emitAnimatedBodyTransformer(CarBody.getFrontCylinderPart());
        emitAnimatedBodyTransformer(CarBody.getFrontWindow());

        Box backCarPartBody = CarBody.getBackPart();

        Transform3D backCarPartTransform = new Transform3D();
        backCarPartTransform.setTranslation(new Vector3f());

        TransformGroup backCarPartTransformGroup = new TransformGroup();
        backCarPartTransformGroup.setTransform(backCarPartTransform);
        backCarPartTransformGroup.addChild(backCarPartBody);

        carTransformGroup.addChild(backCarPartTransformGroup);

        Box frontPartBody = CarBody.getFrontPart();

        Transform3D frontCarPartTransform = new Transform3D();
        frontCarPartTransform.setTranslation(new Vector3f(.7f, -.15f, .0f));

        TransformGroup frontCarPartTransformGroup = new TransformGroup();
        frontCarPartTransformGroup.setTransform(frontCarPartTransform);
        frontCarPartTransformGroup.addChild(frontPartBody);

        carTransformGroup.addChild(frontCarPartTransformGroup);

        emitWheel(0.5f, -0.25f, -0.3f);
        emitWheel(0.5f, -0.25f, 0.3f);

        emitWheel(-0.2f, -0.25f, -0.3f);
        emitWheel(-0.2f, -0.25f, 0.3f);

        TransformGroup carWheelsTransformGroup = new TransformGroup();

        Transform3D transform = new Transform3D();
        transform.rotZ(3.14 / 2);
        transform.setTranslation(new Vector3f(-0.5f, .0f, -.0f));

        carWheelsTransformGroup.setTransform(transform);
        carWheelsTransformGroup.addChild(CarBody.getWheel());

        carTransformGroup.addChild(carWheelsTransformGroup);
    }

    private void emitWheel(float x, float y, float z) throws IOException {
        TransformGroup carWheelTransformGroup = new TransformGroup();

        Transform3D wheelTransform = new Transform3D();
        wheelTransform.rotX(3.14 / 2);
        wheelTransform.setTranslation(new Vector3f(x, y, z));

        carWheelTransformGroup.setTransform(wheelTransform);
        carWheelTransformGroup.addChild(CarBody.getWheel());

        carTransformGroup.addChild(carWheelTransformGroup);
    }

    private void emitAnimatedBodyTransformer(Cylinder body) {
        Transform3D bodyTransform = new Transform3D();
        bodyTransform.rotX(3.14 / 2);
        bodyTransform.setTranslation(new Vector3f(.55f, .0f, .0f));

        TransformGroup bodyTransformGroup = new TransformGroup();
        bodyTransformGroup.setTransform(bodyTransform);
        bodyTransformGroup.addChild(body);

        carTransformGroup.addChild(bodyTransformGroup);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        carTransform3D.rotY(angle);
        carTransformGroup.setTransform(carTransform3D);

        angle += 0.05;
    }
}
