package Warehouse.Racks;

import BackEnd.Geometry.Point2D;
import Warehouse.Exceptions.FullRackException;
import Warehouse.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RackTest {
    private Rack testRack;

    //TODO: FÅ SAT TEST OP FOR PUNKTET OG FOR EQUALITY

    @BeforeEach
    void beforeEach() {
        testRack = new Rack(2, new Point2D(1, 1));
    }

    @Test
    void addProduct() {
        testRack.addProduct(new Product(1));
        assertEquals(new Product(1), testRack.getProduct(0));
    }

    @Test
    void doesItContainProduct() {

    }

    @Test
    void checkIfFull() {
        testRack.addProduct(new Product(1));
        testRack.addProduct(new Product(2));

        assertThrows(FullRackException.class, () -> testRack.addProduct(new Product(3)));
        assertEquals(2, testRack.getProductList().size());
    }

    @Test
    void getProductList() {
        testRack.addProduct(new Product(1));
        testRack.addProduct(new Product(2));

        List<Product> tempProductList = testRack.getProductList();

        assertTrue(tempProductList.get(0).equals(new Product(1)));
        assertTrue(tempProductList.get(1).equals(new Product(2)));
    }

}