package Warehouse;

import BackEnd.Geometry.Point2D;
import Warehouse.Racks.Rack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProductTest {
    private Product testProduct;
    private Product product1;
    private Product product2;
    private Rack rack;

    @BeforeEach
    void beforeEach() {
        testProduct = new Product(1);
        product1 = new Product(1);
        product2 = new Product(2);
        rack = new Rack(2, new Point2D(1, 1));
    }

    @Test
    void getId1() {
        assertEquals(1, testProduct.getId());
    }

    @Test
    void testEquals1() {
        assertEquals(testProduct, product1);
    }

    @Test
    void testEquals2() {
        assertNotEquals(testProduct, product2);
    }

}