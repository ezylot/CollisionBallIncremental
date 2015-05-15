package at.ezylot.ibg;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
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

    public int anzahlKuglen = 1500;


    private ArrayList<MovableCircle> collisions = new ArrayList<>();

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
            mc = MovableCircleFactory.createMovableCircle(r.nextDouble()*Main.RootStage.getScene().getWidth(), r.nextDouble()*Main.RootStage.getScene().getHeight(), 10);
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

        MovableCircle ballToMove;
        for(int i = 0; i < balls.size(); i++) {
            ballToMove = balls.get(i);
            if(!ballToMove.isExploded()) {
                ballToMove.move(2);
                if (ballToMove.touchesWall())
                    ballToMove.bounceOff();
            }
        }

        ArrayList<MovableCircle> ballsToBeRemoved = new ArrayList<>();

        for(MovableCircle ball : balls) {
            if(ball.isExploded()) {
                if (ball.tickExploded()) {
                    ballsToBeRemoved.add(ball);
                    playscreen.getChildren().remove(ball);
                }
            }
        }
        balls.removeAll(ballsToBeRemoved);

        Double Score = Double.parseDouble(lblScore.getText());

        for(MovableCircle ball : balls) {
            if(ball.isExploded()) {

                ArrayList<MovableCircle> cc = collidesWithOtherBalls(ball);
                MovableCircle c;
                for(int i = 0; i < cc.size(); i++) {
                    c = cc.get(i);
                    c.explode();
                    Score = Score + combo;
                }
            }
        }
        lblScore.setText(Score + "");
    }

    public ArrayList<MovableCircle> collidesWithOtherBalls(MovableCircle c) {
        collisions.clear();
        MovableCircle ball;
        for(int i = 0; i < balls.size();i++) {
            ball = balls.get(i);
            if(!ball.equals(c)) {
                if(!ball.isExploded()) {
                    if (c.collides(ball))
                        collisions.add(ball);
                }
            }
        }
        return collisions;
    }
}