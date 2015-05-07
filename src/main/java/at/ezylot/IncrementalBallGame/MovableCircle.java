package at.ezylot.IncrementalBallGame;

import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.Vector;

public class MovableCircle extends Circle {

    public double getExplosionRate() {
        return explosionRate;
    }

    public void setExplosionRate(double explosionRate) {
        this.explosionRate = explosionRate;
    }

    private double explosionRate = 1.5;

    public boolean isExploded() {
        return exploded;
    }

    private Scene rootScene;

    public void explode() {
        this.exploded = true;
        this.setFill(Paint.valueOf("red"));
        this.setRadius(this.getRadius()*this.getExplosionRate());
    }

    private boolean exploded = false;

    public double getMoveDegree() {
        return moveDegree;
    }

    public void setMoveDegree(double moveDegree) {
        this.moveDegree = moveDegree;
    }

    private double moveDegree = 0;

    public MovableCircle(double x, double y, double radius, Paint p, double moveDegree, Scene rootScene) {
        super(x, y, radius, p);
        this.setMoveDegree(moveDegree);
        this.rootScene = rootScene;
    }

    public boolean touchesWall() {
        if(this.getCenterX() - getRadius() <= 0)
            return true;
        if(this.getCenterX() + getRadius() >= rootScene.getWidth())
            return true;
        if(this.getCenterY() + getRadius() >= rootScene.getHeight())
            return true;
        if(this.getCenterY() - getRadius() <= 0)
            return true;

        return false;
    }

    public void bounceOff() {

        double newDegree = 0;
        if(this.getCenterX() - getRadius() <= 0 || this.getCenterX() + getRadius() >= rootScene.getWidth())
            newDegree = 90+(90-this.getMoveDegree())%360;
        if(this.getCenterY() + getRadius() >= rootScene.getHeight() || this.getCenterY() - getRadius() <= 0)
            newDegree = 180+(180-this.getMoveDegree())%360;

        this.setMoveDegree(newDegree);
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
        this.setCenterY(this.getCenterY() + Math.sin(Math.toRadians(this.getMoveDegree())) * speed);
        this.setCenterX(this.getCenterX() + Math.cos(Math.toRadians(this.getMoveDegree())) * speed);

        if(this.getCenterY() > rootScene.getHeight())
            this.setCenterY(rootScene.getHeight());
        if(this.getCenterY() < 0)
            this.setCenterY(0);
        if(this.getCenterX() > rootScene.getWidth())
            this.setCenterY(rootScene.getWidth());
        if(this.getCenterX() < 0)
            this.setCenterY(0);
    }


}
