package at.ezylot.ibg;

import javafx.scene.paint.Paint;

import java.security.SecureRandom;
import java.util.Random;

public class MovableCircleFactory {
    public static MovableCircle createMovableCircle(double x, double y, double radius) {
        String[] colors = new String[] {
            "#AA5585",
            "#55AA55",
            "A5C663"
        };
        String color = colors[(new Random()).nextInt(colors.length)];
        SecureRandom random = new SecureRandom();

        return new MovableCircle(x, y, radius, Paint.valueOf(color), random.nextDouble()*359, Main.RootStage);
    }
}
