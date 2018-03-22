package Warehouse.Racks;

import Geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalRackRowTest {
    HorizontalRackRow testRow;

    @BeforeEach
    public void beforeEach() {
        Point2D startPoint = new Point2D(1,1);
        testRow = new HorizontalRackRow(startPoint, 10,2);
    }


    @Test
    void isHorizontal() {
        assertTrue(testRow.isHorizontal());
    }

    @Test
    void addProduct() {

    }

    @Test
    void addProductToRack() {
    }

    @Test
    void doesItContainProduct() {
    }

    @Test
    void getRackRowLength() {
        assertEquals(10, testRow.getRackRowLength());
    }

    @Test
    void getStartPoint() {
        assertTrue(testRow.getStartPoint().equals(new Point2D(1,1)));
    }

    @Test
    void getAllProductsInRackRow() {
    }

    @Test
    void getRackArray() {
    }
}