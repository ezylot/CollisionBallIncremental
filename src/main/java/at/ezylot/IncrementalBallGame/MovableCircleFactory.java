package at.ezylot.IncrementalBallGame;

import javafx.scene.paint.Paint;

import java.util.Random;

public class MovableCircleFactory {
    public static MovableCircle createMovableCircle(double x, double y, double radius) {
        String[] colors = new String[] {
            "green",
            "blue",
            "orange",
            "grey",
            "brown"
        };
        String color = colors[(new Random()).nextInt(colors.length)];

        return new MovableCircle(x, y, radius, Paint.valueOf(color), (new Random()).nextInt(359), Main.RootStage);
    }
}
