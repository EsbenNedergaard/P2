package Warehouse.RackTest;

import Warehouse.Product;
import Warehouse.RackSome;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddProductTest {
    // Tests for adding a product to rack
    // When rack is vertical
    // Create a new vertical test rack located in (x, y) = (2, 2)
    private RackSome verticalTestRack = new RackSome("A", 1, 15, 2, 2);

    @Test
    void testAddProduct01() {
        verticalTestRack.addProduct(new Product("Product", 1));
        assertTrue(verticalTestRack.containsProduct(2, 2));
    }

}