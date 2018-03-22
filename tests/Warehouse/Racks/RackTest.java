package Warehouse.Racks;

import Exceptions.FullRackException;
import Warehouse.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RackTest {
    Rack testRack;

    @BeforeEach
    public void beforeEach() {
        testRack = new Rack(2);
    }

    @Test
    void addProduct1() {
        testRack.addProduct(new Product("a", 1));
        assertEquals(new Product("a", 1), testRack.getProduct(0));
    }

    @Test
    void addProduct2() {
        testRack.addProduct(new Product("b", 1));
        assertEquals(new Product("a", 1), testRack.getProduct(0));
    }

    @Test
    void doesItContainProduct() {

    }

    @Test
    void checkIfFull() {
        testRack.addProduct(new Product("a", 1));
        testRack.addProduct(new Product("b", 2));

        assertThrows(FullRackException.class, ()->{
            testRack.addProduct(new Product("b", 3));
        });
        assertEquals(2, testRack.getProductList().size());
    }

    @Test
    void getProductList() {
        testRack.addProduct(new Product("a", 1));
        testRack.addProduct(new Product("b", 2));

        List<Product> tempProductList = testRack.getProductList();

        assertTrue(tempProductList.get(0).equals(new Product("a", 1)));
        assertTrue(tempProductList.get(1).equals(new Product("b", 2)));
    }

}