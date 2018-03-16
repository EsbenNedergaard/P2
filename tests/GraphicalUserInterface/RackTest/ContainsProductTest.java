package GraphicalUserInterface.RackTest;

import GraphicalUserInterface.Product;
import GraphicalUserInterface.Rack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsProductTest {

    // Contains product Test
    @Test
    void testContainsProduct01() {
        Rack testRack = new Rack("A", 1, 4, 0, 0);

        assertFalse(testRack.containsProduct(0, 0));
        assertFalse(testRack.containsProduct(0, 1));
        assertFalse(testRack.containsProduct(0, 2));
        assertFalse(testRack.containsProduct(0, 3));
    }

    @Test
    void testContainsProduct02() {
        Rack testRack = new Rack("A", 1, 4, 0, 0);
        // Adding some products to the rack
        testRack.addProduct(new Product("product", 1));
        testRack.addProduct(new Product("product", 1));
        testRack.addProduct(new Product("product", 1));
        testRack.addProduct(new Product("product", 1));

        // Should contain products
        assertTrue(testRack.containsProduct(0, 0));
        assertTrue(testRack.containsProduct(0, 1));
        assertTrue(testRack.containsProduct(0, 2));
        assertTrue(testRack.containsProduct(0, 3));
    }

    @Test
    void testContainsProduct03() {
        Rack testRack = new Rack("A", 1, 4, 0, 0);

        testRack.addProduct(new Product("product", 1));
        testRack.addProduct(new Product("product", 1));

        // Two added products
        assertTrue(testRack.containsProduct(0, 0));
        assertTrue(testRack.containsProduct(0, 1));
        // Two empty spaces
        assertFalse(testRack.containsProduct(0, 2));
        assertFalse(testRack.containsProduct(0, 3));
    }

    // TODO: make tests for horisontal rack

}
