package at.ezylot.ibg;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MovableCircle extends Circle {

    private double explosionRate = 1.5;
    private int explosionTick = 0;
    private Stage rootStage;
    private boolean exploded = false;
    private double moveDegree = 0;

    public static int maxExplosionTicks = 16;

    public MovableCircle(double x, double y, double radius, Paint p, double moveDegree, Stage rootStage) {
        super(x, y, radius, p);
        this.setMoveDegree(moveDegree);
        this.rootStage = rootStage;
    }

    public double getExplosionRate() {
        return explosionRate;
    }

    public void setExplosionRate(double explosionRate) {
        this.explosionRate = explosionRate;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void explode() {
        this.exploded = true;
        this.setFill(Paint.valueOf("#AA3939"));
        this.setRadius(this.getRadius() * this.getExplosionRate());
    }

    public double getMoveDegree() {
        return moveDegree;
    }

    public void setMoveDegree(double moveDegree) {
        this.moveDegree = moveDegree;
    }

    public boolean tickExploded() {
        if (explosionTick > maxExplosionTicks)
            return true;
        explosionTick++;
        return false;
    }

    public boolean touchesWall() {
        if (this.getCenterX() <= 0)
            return true;
        if (this.getCenterX() >= rootStage.getScene().getWidth())
            return true;
        if (this.getCenterY() >= rootStage.getScene().getHeight())
            return true;
        if (this.getCenterY() <= 0)
            return true;

        return false;
    }

    public void bounceOff() {

        double newDegree = 0;
        if (this.getCenterX() <= 0 || this.getCenterX() >= rootStage.getScene().getWidth())
            newDegree = 90 + (90 - this.getMoveDegree()) % 360;
        if (this.getCenterY() + getRadius() >= rootStage.getScene().getHeight() || this.getCenterY() - getRadius() <= 0)
            newDegree = 180 + (180 - this.getMoveDegree()) % 360;

        this.setMoveDegree(newDegree);
    }

    public boolean collides(MovableCircle b) {
        double deltaX = this.getCenterX() - b.getCenterX();
        double deltaY = this.getCenterY() - b.getCenterY();

        deltaX = (deltaX < 0) ? deltaX * -1 : deltaX;
        deltaY = (deltaY < 0) ? deltaY * -1 : deltaY;

        double delta = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        if (delta - this.getRadius() - b.getRadius() <= 0)
            return true;
        return false;
    }

    public void move(double speed) {
        this.setCenterY(this.getCenterY() + Math.sin(Math.toRadians(this.getMoveDegree())) * speed);
        this.setCenterX(this.getCenterX() + Math.cos(Math.toRadians(this.getMoveDegree())) * speed);

        if(this.getCenterY() > rootStage.getScene().getHeight())
            this.setCenterY( rootStage.getScene().getHeight());
        if(this.getCenterY() < 0)
            this.setCenterY(0);
        if(this.getCenterX() >  rootStage.getScene().getWidth())
            this.setCenterX( rootStage.getScene().getWidth());
        if(this.getCenterX() < 0)
            this.setCenterX(0);
    }


}
