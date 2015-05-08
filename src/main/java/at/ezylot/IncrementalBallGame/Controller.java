package at.ezylot.IncrementalBallGame;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    public Pane scorescreen;
    public Pane playscreen;

    List<MovableCircle> balls = new ArrayList<>();

    public Label lblMultiplier;
    public Label lblScore;

    public int speed = 1;
    public int explosionSize = 10;

    public double combo = 1.0;

    public Timeline runCycle;

    public int anzahlKuglen = 200;

    public void initialize() {
        runCycle = new Timeline(new KeyFrame(Duration.millis(33), e->tick()));
        runCycle.setCycleCount(Timeline.INDEFINITE);
        runCycle.play();
        scorescreen.toFront();
    }

    public void tick() {
        long start = System.nanoTime();
        MovableCircle mc;
        for(int i = balls.size(); i < anzahlKuglen; i++) {
            Random r = new Random();
            mc = MovableCircleFactory.createMovableCircle(r.nextDouble()*Main.RootStage.getScene().getWidth(), r.nextDouble()*Main.RootStage.getScene().getHeight(), 11);
            mc.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        EventTarget a = mouseEvent.getTarget();
                        if (!((MovableCircle) a).isExploded()) {
                            ((MovableCircle) a).explode();
                            double score = Double.parseDouble(lblScore.getText());
                            double multiplier = Double.parseDouble(lblMultiplier.getText());
                            lblScore.setText((score + 1 * multiplier) + "");
                        }
                    }
                }
            });
            playscreen.getChildren().add(mc);
            balls.add(mc);
            mc.toBack();
        }

        long delta = System.nanoTime() - start;
        if(delta > 1000) System.out.println("Balls created: \t\t\t" + delta);
        start = System.nanoTime();

        for(MovableCircle ball : balls) {
            if(!ball.isExploded()) {
                ball.move(2);
                if (ball.touchesWall())
                    ball.bounceOff();
            }
        }

        delta = System.nanoTime() - start;
        if(delta > 1000) System.out.println("Balls moved: \t\t\t" + delta);
        start = System.nanoTime();

        ArrayList<MovableCircle> ballsToBeRemoved = new ArrayList<>();

        for(MovableCircle ball : balls) {
            if(ball.isExploded()) {
                if (ball.tickExploded()) {
                    ballsToBeRemoved.add(ball);
                    continue;
                }
            }
        }

        for(MovableCircle ball: ballsToBeRemoved) {
            playscreen.getChildren().remove(ball);
        }
        balls.removeAll(ballsToBeRemoved);

        delta = System.nanoTime() - start;
        if(delta > 1000) System.out.println("Timed out balls clean: \t" + delta);
        start = System.nanoTime();


        for(MovableCircle ball : balls) {
            if(ball.isExploded()) {

                ArrayList<MovableCircle> cc = collidesWithOtherBalls(ball);
                for(MovableCircle c : cc) {
                    c.explode();
                    Double Score = Double.parseDouble(lblScore.getText());
                    Score = Score + combo;
                    lblScore.setText(Score + "");
                }
            }
        }
        delta = System.nanoTime() - start;
        if(delta > 1000) System.out.println("Explosion processed: \t" + delta);
    }

    public ArrayList<MovableCircle> collidesWithOtherBalls(MovableCircle c) {
        ArrayList<MovableCircle> collisions = new ArrayList<>();
        for(MovableCircle ball : balls) {
            if(!ball.equals(c)) {
                if(!ball.isExploded()) {
                    if (c.collides(ball))
                        collisions.add(ball);
                } else {
                    continue;
                }
            }
        }
        return collisions;
    }
}
