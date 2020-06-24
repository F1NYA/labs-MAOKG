package Drawer;
 
import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.shape.*;

public class JFXApp extends Application {
       
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("LAB 1");
        
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        scene.setFill(Color.GREEN);
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(140.0, 30.0,
                220.0, 150.0,
                60.0, 150.0, 10.0);
        root.getChildren().add(polygon);
        polygon.setFill(Color.YELLOW);


        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(20.0, 30.0,
                50.0, 190.0,
                230.0, 190.0,
                270.0, 30.0);
        root.getChildren().add(polyline);
        polyline.setStroke(Color.RED);
        polyline.setStrokeWidth(4);

        Line eyebrow_left = new Line(30.0, 30.0, 110.0 , 30.0);
        Line eyebrow_right = new Line(160.0, 30.0, 240.0 , 30.0);

        root.getChildren().add(eyebrow_left);
        root.getChildren().add(eyebrow_right);
        eyebrow_left.setStroke(Color.BLUE);
        eyebrow_right.setStroke(Color.BLUE);
        eyebrow_left.setStrokeWidth(4);
        eyebrow_right.setStrokeWidth(4);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}