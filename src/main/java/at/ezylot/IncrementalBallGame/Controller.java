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

    public int anzahlKuglen = 50;

    public void initialize() {
        runCycle = new Timeline(new KeyFrame(Duration.millis(5), e->tick()));
        runCycle.setCycleCount(Timeline.INDEFINITE);
        runCycle.play();

        for(int i = 0; i < anzahlKuglen; i++) {
            SecureRandom r = new SecureRandom();
            balls.add(MovableCircleFactory.createMovableCircle(r.nextDouble()*200, r.nextDouble()*300, 11));
        }
        for(MovableCircle c : balls) {
            c.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        }
        playscreen.getChildren().addAll(balls);


    }

    public void tick() {
        ArrayList<MovableCircle> ballsToBeRemoved = new ArrayList<>();
        for(MovableCircle ball : balls) {
            if(ball.isExploded()) {
                if(ball.tickExploded())
                    ballsToBeRemoved.add(ball);

                ArrayList<MovableCircle> cc = collidesWithOtherBalls(ball);
                if(cc.size() != 0) {
                    for(MovableCircle c : cc) {
                        if (c.isExploded() == false) {
                            c.explode();
                            Double Score = Double.parseDouble(lblScore.getText());
                            Score = Score + combo;
                            lblScore.setText(Score + "");
                        }
                    }
                }
            } else {
                ball.move(0.8);
                if(ball.touchesWall())
                    ball.bounceOff();
            }
        }


        for(MovableCircle ball: ballsToBeRemoved) {
            ball.setCenterX(-10000);
            ball.setCenterY(-10000);
        }
        balls.removeAll(ballsToBeRemoved);
    }

    public ArrayList<MovableCircle> collidesWithOtherBalls(MovableCircle c) {
        ArrayList<MovableCircle> collisions = new ArrayList<>();
        for(MovableCircle ball : balls) {
            if(!ball.equals(c)) {
                if(c.collides(ball))
                    collisions.add(ball);
            }
        }
        return collisions;
    }
}
