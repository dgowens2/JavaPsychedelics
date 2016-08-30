package com.tiy.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/**
 * Created by DTG2 on 08/29/16.
 */
public class SampleCanvas extends Application {

    final double DEFAULT_SCENE_HEIGHT = 800;
    final double DEFAULT_SCENE_WIDTH = 1000;
    double strokeSize = 2;

    @Override
    public void start(Stage primaryStage) {
        Group rootGroup = new Group();

//        Scene mainScene = new Scene(rootGroup, 800, 600, Color.BLACK);


        Canvas canvas = new Canvas(DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);/*Creates a canvas*/
        canvas.setFocusTraversable(true);/*events that move beyond the canvas*/

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        graphicsContext.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        graphicsContext.setLineWidth(2);

        drawShapes(graphicsContext);/*Call before you set the stage*/

        canvas.setOnKeyPressed((new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getText().equals("a")) {
                    graphicsContext.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
                    System.out.println("Key pressed was: " + event.getCode().getName());
                } else if (event.getText().equalsIgnoreCase("c")) {
                    start(primaryStage);
                } else if (event.getCode() == KeyCode.UP) {
                    strokeSize += 1;
                    if (strokeSize == 21) {
                        strokeSize -= 1;
                    }
                } else if (event.getCode() == KeyCode.DOWN) {
                    strokeSize -= 1;
                    if (strokeSize == 1) {
                        strokeSize += 1;
                    }
                }
            }
        }));

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                System.out.println("x: " + e.getX() + ", y: " + e.getY());
                graphicsContext.strokeOval(e.getX(), e.getY(), strokeSize, strokeSize);
            }
            /*now we draw. Stroke oval sets the shape of the dot. Sysout prints the coordinates to the console.
            Adding +20 to the X& Y coordinates offsets the dot from the mouse (i.e. e.getX() = 20)*/
        });

        rootGroup.getChildren().add(canvas);
        Scene scene = new Scene(rootGroup, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.PLUM);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }
}

