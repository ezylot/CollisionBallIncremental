package at.ezylot.IncrementalBallGame;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    public Pane scorescreen;
    public Pane playscreen;

    List<MovableCircle> balls = new ArrayList<>();

    public Label lblMultiplier;
    public Label lblScore;

    public String[] colors = new String[] {
            "red", "blue", "green"
    };


    public int speed = 1;
    public int explosionSize = 10;


    public void initialize() {
        Timeline runCycle = new Timeline(new KeyFrame(Duration.millis(20), e->moveBalls()));
        runCycle.setCycleCount(Timeline.INDEFINITE);
        runCycle.play();

        String color = colors[(new Random()).nextInt(colors.length)];

        MovableCircle l = new MovableCircle(40,50,15, Paint.valueOf(color), 45);
        l.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    EventTarget a = mouseEvent.getTarget();
                    ((MovableCircle)a).setRadius(((MovableCircle) a).getRadius()+ explosionSize);
                }
            }
        });

        playscreen.getChildren().add(l);

    }

    public void moveBalls() {
        for(Object o : playscreen.getChildren()) {
            if(o instanceof MovableCircle) {
                ((MovableCircle) o).move(1);
            }
        }
    }
}
