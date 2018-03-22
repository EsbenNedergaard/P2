package Warehouse.Racks;

import Exceptions.FullRackException;
import Geometry.Point2D;
import Warehouse.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalRackRowTest {
    HorizontalRackRow testRow;

    @BeforeEach
    public void beforeEach() {
        Point2D startPoint = new Point2D(1,1);
        testRow = new HorizontalRackRow(startPoint, 2,2);
    }

    @Test
    void isHorizontal() {
        assertTrue(testRow.isHorizontal());
    }

    @Test
    void getRackArray() {
        Rack[] tempArray = testRow.getRackArray();
        Rack tempRack = new Rack(2);

        assertTrue(tempRack.equals(tempArray[0]));
        assertTrue(tempRack.equals(tempArray[1]));
    }

    @Test
    void getRackByIndex() {
        Rack tempRack = new Rack(2);
        assertTrue(tempRack.equals(testRow.getRackByIndex(0)));
        assertTrue(tempRack.equals(testRow.getRackByIndex(1)));
    }

    @Test
    void addProduct1() {
        testRow.addProduct(new Product("a", 1));

        Rack tempRack = new Rack(2);
        tempRack.addProduct(new Product("a", 1));

        assertTrue(tempRack.equals(testRow.getRackByIndex(0)));
    }

    @Test
    void addProduct2() {
        /*We test more than one product*/
        testRow.addProduct(new Product("a", 1));
        testRow.addProduct(new Product("b", 2));

        Rack tempRack = new Rack(2);
        tempRack.addProduct(new Product("a", 1));
        tempRack.addProduct(new Product("b", 2));

        assertTrue(tempRack.equals(testRow.getRackByIndex(0)));
    }

    @Test
    void addProduct3() {
        /*We test if it goes into the next rack in the rackArray, when the first is filled up*/
        testRow.addProduct(new Product("a", 1));
        testRow.addProduct(new Product("b", 2));
        testRow.addProduct(new Product("c", 3));

        Rack tempRack = new Rack(2);
        tempRack.addProduct(new Product("c", 3));

        assertTrue(tempRack.equals(testRow.getRackByIndex(1)));
    }

    @Test
    void addProduct4() {
        /*We test for an filled rack*/
        testRow.addProduct(new Product("a", 1));
        testRow.addProduct(new Product("b", 2));
        testRow.addProduct(new Product("c", 3));
        testRow.addProduct(new Product("d", 4));

        assertThrows(FullRackException.class, ()->{
            testRow.addProduct(new Product("e", 5));
        });
    }

    @Test
    void addProductToRack1() {
        testRow.addProductToRack(new Product("a", 1), 1);

        Rack tempRack = new Rack(2);
        tempRack.addProduct(new Product("a", 1));

        assertTrue(testRow.getRackByIndex(1).equals(tempRack));
    }

    @Test
    void addProductToRack2() {
        /*We see if we can get it to throw an exception*/
        testRow.addProductToRack(new Product("a", 1), 1);
        testRow.addProductToRack(new Product("b", 2), 1);

        assertThrows(FullRackException.class, ()->{
            testRow.addProductToRack(new Product("c", 3), 1);
        });
    }


    @Test
    void doesItContainProduct() {
    }

    @Test
    void getRackRowLength() {
        assertEquals(2, testRow.getRackRowLength());
    }

    @Test
    void getStartPoint() {
        assertTrue(testRow.getStartPoint().equals(new Point2D(1,1)));
    }

    @Test
    void getAllProductsInRackRow() {
        testRow.addProduct(new Product("a", 1));
        testRow.addProduct(new Product("b", 2));
        testRow.addProduct(new Product("c", 3));
        testRow.addProduct(new Product("d", 4));

        List<Product> tempProductList = testRow.getAllProductsInRackRow();

        assertTrue(tempProductList.get(0).equals(new Product("a", 1)));
        assertTrue(tempProductList.get(1).equals(new Product("b", 2)));
        assertTrue(tempProductList.get(2).equals(new Product("c", 3)));
        assertTrue(tempProductList.get(3).equals(new Product("d", 4)));
    }
}