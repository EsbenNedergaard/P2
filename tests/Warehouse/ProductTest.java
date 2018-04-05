package Warehouse;

import Exceptions.FullRackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product testProduct;
    Product product1;
    Product product2;

    @BeforeEach
    void beforeEach() {
        testProduct = new Product(1);
        product1 = new Product(1);
        product2 = new Product(2);
    }



    @Test
    void setRack() {
    }

    @Test
    void getId() {
    }

    @Test
    void getRack() {
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