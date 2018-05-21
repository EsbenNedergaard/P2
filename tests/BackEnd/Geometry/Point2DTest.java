package BackEnd.Geometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static Warehouse.GUIWarehouse.TILE_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Point2DTest {
    private Point2D point1;
    private Point2D point2;
    private Point2D point3;

    @BeforeEach
    void beforeEach() {
        point1 = new Point2D(1, 1);
        point2 = new Point2D(7, 5);
        point3 = new Point2D();
    }

    @Test
    void getX01() {
        assertEquals(1, point1.getX());
    }

    @Test
    void getX02() {
        assertEquals(7, point2.getX());
    }

    @Test
    void getX03() {
        assertEquals(-1, point3.getX());
    }

    @Test
    void getY01() {
        assertEquals(1, point1.getY());
    }

    @Test
    void getY02() {
        assertEquals(5, point2.getY());
    }

    @Test
    void getY03() {
        assertEquals(-1, point3.getY());
    }

    @Test
    void getXPixels01() {
        assertEquals(1 * TILE_SIZE, point1.getXPixels());
    }

    @Test
    void getXPixels02() {
        assertEquals(7 * TILE_SIZE, point2.getXPixels());
    }

    @Test
    void getXPixels03() {
        assertEquals(-1 * TILE_SIZE, point3.getXPixels());
    }

    @Test
    void getYPixels01() {
        assertEquals(1 * TILE_SIZE, point1.getYPixels());
    }

    @Test
    void getYPixels02() {
        assertEquals(5 * TILE_SIZE, point2.getYPixels());
    }

    @Test
    void getYPixels03() {
        assertEquals(-1 * TILE_SIZE, point3.getYPixels());
    }

    @Test
    void equals01() {
        assertEquals(new Point2D(1, 1), point1);
    }

    @Test
    void equals02() {
        assertEquals(new Point2D(7, 5), point2);
    }

    @Test
    void equals03() {
        assertEquals(new Point2D(-1, -1), point3);
    }

    @Test
    void equals04() {
        assertNotEquals(new Point2D(100, 100), point1);
    }

    @Test
    void copyConstructor() {
        Point2D temp = new Point2D(point1);
        assertEquals(point1, temp);
    }
}
