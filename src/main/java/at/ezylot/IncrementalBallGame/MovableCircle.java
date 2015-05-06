package at.ezylot.IncrementalBallGame;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.Vector;

public class MovableCircle extends Circle {

    public double getMoveDegree() {
        return moveDegree;
    }

    public void setMoveDegree(double moveDegree) {
        this.moveDegree = moveDegree;
    }

    private double moveDegree = 0;

    public MovableCircle(double x, double y, double radius, Paint p, double moveDegree) {
        super(x, y, radius, p);
        this.setMoveDegree(moveDegree);
    }

    public boolean collides(MovableCircle b) {
        double deltaX = this.getCenterX() - b.getCenterX();
        double deltaY = this.getCenterY() - b.getCenterY();

        deltaX = (deltaX < 0) ? deltaX*-1 : deltaX;
        deltaY = (deltaY < 0) ? deltaY*-1 : deltaY;

        double delta = Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY, 2));
        if(delta-this.getRadius()-b.getRadius() <= 0)
            return true;
        return false;
    }

    public void move(double speed) {
        this.setCenterY(this.getCenterY() + Math.sin(Math.toRadians(this.getMoveDegree()))*speed);
        this.setCenterX(this.getCenterX() + Math.cos(Math.toRadians(this.getMoveDegree())) * speed);
    }



}
