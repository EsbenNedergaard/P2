package Warehouse.Racks;

import Exceptions.FullRackException;
import Geometry.Point2D;
import Warehouse.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalRackRowTest {
    private HorizontalRackRow testRow;
    private Rack rack0;
    private Rack rack1;
    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;
    private Product product5;

    //TODO: SKAL HAVE SAT TESTS OP FOR
    //TODO: Flyttes lavning af produkter herop og kalde dem p1, p2 osv. samt lave vores getters om til package private, da de ikke skal bruges andre steder
    @BeforeEach
    void beforeEach() {
        Point2D startPoint = new Point2D(1,1);
        testRow = new HorizontalRackRow(startPoint, 2,2);

        rack0 = new Rack(2, new Point2D(1,1));
        rack1 = new Rack(2, new Point2D(2,1));

        product1 = new Product(1);
        product2 = new Product(2);
        product3 = new Product(3);
        product4 = new Product(4);
        product5 = new Product(5);
    }

    @Test
    void isHorizontal() {
        assertTrue(testRow.isHorizontal());
    }

    @Test
    void getRackArray() {
        Rack[] tempArray = testRow.getRackArray();

        assertEquals(rack0, tempArray[0]);
        assertEquals(rack1, tempArray[1]);
    }

    @Test
    void getRackByIndex() {
        assertEquals(rack0, testRow.getRackByIndex(0));
        assertEquals(rack1, testRow.getRackByIndex(1));
    }

    @Test
    void addProduct1() {
        testRow.addProduct(product1);

        rack0.addProduct(product1);

        assertEquals(rack0, testRow.getRackByIndex(0));
    }

    @Test
    void addProduct2() {
        /*We test more than one product*/
        testRow.addProduct(product1);
        testRow.addProduct(product2);

        rack0.addProduct(product1);
        rack0.addProduct(product2);

        assertEquals(rack0, testRow.getRackByIndex(0));
    }

    @Test
    void addProduct3() {
        /*We test if it goes into the next rack in the rackArray, when the first is filled up*/
        testRow.addProduct(product1);
        testRow.addProduct(product2);
        testRow.addProduct(product3);

        rack1.addProduct(product3);

        assertEquals(rack1, testRow.getRackByIndex(1));
    }

    @Test
    void addProduct4() {
        /*We test for an filled rack*/
        testRow.addProduct(product1);
        testRow.addProduct(product2);
        testRow.addProduct(product3);
        testRow.addProduct(product4);

        assertThrows(FullRackException.class, ()-> testRow.addProduct(product5));
    }

    @Test
    void addProductToRack1() {
        testRow.addProductToRack(product1, 1);

        rack1.addProduct(product1);

        assertEquals(rack1, testRow.getRackByIndex(1));
    }

    @Test
    void addProductToRack2() {
        /*We see if we can get it to throw an exception*/
        testRow.addProductToRack(product1, 1);
        testRow.addProductToRack(product2, 1);

        assertThrows(FullRackException.class, ()-> testRow.addProductToRack(product3, 1));
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
        assertEquals(testRow.getStartPoint(), new Point2D(1, 1));
    }

    @Test
    void getAllProductsInRackRow() {
        testRow.addProduct(product1);
        testRow.addProduct(product2);
        testRow.addProduct(product3);
        testRow.addProduct(product4);

        List<Product> tempProductList = testRow.getAllProductsInRackRow();

        assertEquals(tempProductList.get(0), product1);
        assertEquals(tempProductList.get(1), product2);
        assertEquals(tempProductList.get(2), product3);
        assertEquals(tempProductList.get(3), product4);
    }
}