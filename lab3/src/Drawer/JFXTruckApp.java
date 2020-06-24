package Drawer;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JFXTruckApp extends Application {
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene (root, 470, 310);

        Rectangle rect = new Rectangle(200, 30, 100, 200);
        rect.setFill(Color.DARKBLUE);

        Rotate rotate = new Rotate();
        rect.getTransforms().add(rotate);
        root.getChildren().add(rect);

        rect = new Rectangle(100, 150, 200, 100);
        rect.setFill(Color.YELLOW);
        rect.setStroke(Color.BLACK);
        rect.setStrokeType(StrokeType.OUTSIDE);
        root.getChildren().add(rect);

        Arc arc = new Arc();
        arc.setFill(Color.DARKBLUE);
        arc.setType(ArcType.OPEN);
        arc.setCenterX(100.0f);
        arc.setCenterY(230.0f);
        arc.setRadiusX(15.0f);
        arc.setRadiusY(10.0f);
        arc.setStartAngle(90.0f);
        arc.setLength(180.0f);
        root.getChildren().add(arc);

        Circle circle = new Circle(415,100,10);
        circle.setFill(Color.DARKBLUE);
        root.getChildren().add(circle);

        Polygon polygon = new Polygon(300.0, 50.0,
                300.0, 250.0,
                430.0, 250.0,
                430.0, 150.0,
                400.0, 50.0);
        polygon.setFill(Color.YELLOW);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeType(StrokeType.OUTSIDE);
        root.getChildren().add(polygon);

        polygon = new Polygon(310.0, 60.0,
                310.0, 150.0,
                420.0, 150.0,
                390.0, 60.0);
        polygon.setFill(Color.BLUE);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeType(StrokeType.OUTSIDE);
        root.getChildren().add(polygon);

        circle = new Circle(150,250,30);
        root.getChildren().add(circle);

        circle = new Circle(370,250,30);
        root.getChildren().add(circle);

        circle = new Circle(150,250,5);
        root.getChildren().add(circle);
        circle.setFill(Color.YELLOW);

        circle = new Circle(370,250,5);
        root.getChildren().add(circle);
        circle.setFill(Color.YELLOW);

        Ellipse ellipse = new Ellipse(320,160,15, 5);
        ellipse.setFill(Color.YELLOW);
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeType(StrokeType.OUTSIDE);
        root.getChildren().add(ellipse);

        int cycleCount = 2;
        int time = 2000;

        ScaleTransition scalingTransition = new ScaleTransition(Duration.millis(time), root);
        scalingTransition.setToX(2);
        scalingTransition.setToY(2);
        scalingTransition.setAutoReverse(true);

        RotateTransition rotationTransition = new RotateTransition(Duration.millis(time), root);
        rotationTransition.setByAngle(360f);
        rotationTransition.setCycleCount(cycleCount);
        rotationTransition.setAutoReverse(true);

        TranslateTransition translationTransition = new TranslateTransition(Duration.millis(time), root);
        translationTransition.setFromX(150);
        translationTransition.setToX(50);
        translationTransition.setCycleCount(cycleCount + 1);
        translationTransition.setAutoReverse(true);

        ScaleTransition extraScalingTransition = new ScaleTransition(Duration.millis(time), root);
        extraScalingTransition.setToX(0.1);
        extraScalingTransition.setToY(0.1);
        extraScalingTransition.setCycleCount(cycleCount);
        extraScalingTransition.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                rotationTransition,
                scalingTransition,
                extraScalingTransition,
                translationTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();


        primaryStage.setTitle("Flying truck");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
