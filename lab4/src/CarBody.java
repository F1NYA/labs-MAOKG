import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import java.awt.*;

public class CarBody {
    private static final int primFlags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

    public static Box getBackPart() {
        return new Box(0.5f, 0.3f, 0.3f, CarBody.primFlags, getBackPartAppearence());
    }

    public static Box getFrontPart() {
        return new Box(0.3f, 0.15f, 0.27f, CarBody.primFlags, getFrontPartAppearence());
    }

    public static Cylinder getFrontCylinderPart() {
        return new Cylinder(.3f, 0.5f, CarBody.primFlags, getFrontPartAppearence());
    }

    public static Cylinder getFrontWindow() {
        return new Cylinder(.2f, 0.51f, CarBody.primFlags, getWindowAppearence());
    }

    public static Cylinder getWheel() {
        return new Cylinder(.15f, 0.2f, CarBody.primFlags, getWheelAppearence());
    }

    private static Appearance createTexturedAppearance(String texturePath) {
        Texture texture = new TextureLoader(texturePath, "LUMINANCE", new Container()).getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));

        TextureAttributes attributes = new TextureAttributes();
        attributes.setTextureMode(TextureAttributes.MODULATE);

        Appearance appearance = new Appearance();
        appearance.setTexture(texture);
        appearance.setTextureAttributes(attributes);

        return appearance;
    }

    private static Appearance getBackPartAppearence() {
        Color3f emissive = new Color3f(new Color(0, 0, 0));
        Color3f ambient = new Color3f(new Color(50, 50, 200));
        Color3f diffuse = new Color3f();
        Color3f specular = new Color3f(new Color(0, 0, 0));

        Appearance appearance = CarBody.createTexturedAppearance("resources\\car_back_part.jpg");
        appearance.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

        return appearance;
    }

    private static Appearance getWheelAppearence() {
        Color3f emissive = new Color3f(new Color(0, 0, 0));
        Color3f ambient = new Color3f(new Color(100, 100, 100));
        Color3f diffuse = new Color3f();
        Color3f specular = new Color3f(new Color(0, 0, 0));

        Appearance appearance = CarBody.createTexturedAppearance("resources\\wheel.jpg");
        appearance.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

        return appearance;
    }

    private static Appearance getFrontPartAppearence() {
        Color3f emissive = new Color3f(new Color(0, 0, 0));
        Color3f ambient = new Color3f(new Color(200, 200, 200));
        Color3f diffuse = new Color3f();
        Color3f specular = new Color3f(new Color(0, 0, 0));

        Appearance appearance = CarBody.createTexturedAppearance("resources\\metal.jpg");
        appearance.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

        return appearance;
    }

    private static Appearance getWindowAppearence() {
        Color3f emissive = new Color3f(new Color(0, 0, 0));
        Color3f ambient = new Color3f(new Color(200, 200, 200));
        Color3f diffuse = new Color3f(Color.blue);
        Color3f specular = new Color3f();

        Appearance appearance = CarBody.createTexturedAppearance("resources\\glass.jpg");
        appearance.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

        return appearance;
    }
}
