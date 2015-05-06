package at.ezylot.IncrementalBallGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import javax.swing.text.Position;
import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    List<Circle> positions = new ArrayList<>();

    public Label lblMultiplier;
    public Label lblScore;

    public void initialize() {
        Timeline runCycle = new Timeline(new KeyFrame(Duration.millis(20), e->moveBalls()));
        runCycle.setCycleCount(Timeline.INDEFINITE);
        runCycle.play();
    }

    public void moveBalls() {
    }
}
