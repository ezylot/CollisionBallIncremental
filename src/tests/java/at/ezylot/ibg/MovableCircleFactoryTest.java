package at.ezylot.ibg;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

/**
 *
 */
public class MovableCircleFactoryTest {

    @Test()
    public void testFactoryCannotBeConstructed() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<MovableCircleFactory> c = MovableCircleFactory.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(c.getModifiers()));
        c.setAccessible(true);
        c.newInstance();
    }

    @Test
    public void testCreateMovableCircle() throws Exception {
        MovableCircle mc = MovableCircleFactory.createMovableCircle(3, 5, 8, null);
        assertEquals(3, mc.getCenterX(), 0.1);
        assertEquals(5, mc.getCenterY(), 0.1);
        assertEquals(8, mc.getRadius(), 0.1);
        assertEquals(false, mc.isExploded());

    }
}