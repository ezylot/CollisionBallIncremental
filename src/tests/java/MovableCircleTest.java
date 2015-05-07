import at.ezylot.IncrementalBallGame.MovableCircle;
import at.ezylot.IncrementalBallGame.MovableCircleFactory;
import javafx.scene.paint.Paint;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class MovableCircleTest {
    @Test()
    public void TestCollision() {

        MovableCircle a = MovableCircleFactory.createMovableCircle(0, 0, 0.5);
        MovableCircle b = MovableCircleFactory.createMovableCircle(1, 0, 0.5);

        Assert.assertTrue(a.collides(b));

        b.setCenterX(1.1);
        Assert.assertFalse(a.collides(b));

        b.setCenterX(0.9);
        Assert.assertTrue(a.collides(b));

        b.setRadius(0.1);
        Assert.assertFalse(a.collides(b));
    }

    @Test()
    public void TestMoveCircle() {
        MovableCircle a = MovableCircleFactory.createMovableCircle(0, 0, 0.5);

        a.move(1);
        Assert.assertEquals(1.0, a.getCenterX(), 0.0001);


        a.setMoveDegree(45);
        a.move(1);
        Assert.assertEquals(1.707, a.getCenterX(), 0.001);
        Assert.assertEquals(0.707, a.getCenterY(), 0.001);

        a.setMoveDegree(-90);
        a.move(0.707);
        Assert.assertEquals(0.0, a.getCenterY(), 0.1);
    }

}