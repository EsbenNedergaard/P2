package Warehouse;

import Geometry.Node;
import Geometry.Point2D;
import Warehouse.Order.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Warehouse22bTest {
    private Warehouse22b testWarehouse = new Warehouse22b();

    @Test
    void createAisleList() {
    }

    @Test
    void getRackRowList() {
    }

    @Test
    void getAisleList() {
    }

    @Test
    void getWidth() {
    }

    @Test
    void getLength() {
    }

    @Test
    void getNodeList() {
        List<Node> temp = testWarehouse.getNodeList();
        int currY = 0;

        for (Node element : temp) {
            if (currY < element.getY()) {
                currY++;
                System.out.println();
            }

            if (element.isObstacle()) {
                System.out.print("+ ");
            } else
                System.out.print("- ");
        }
    }

    @Test
    void getPickingPoints() {
        Product product1 = new Product(1);
        Product product2 = new Product(2);

        testWarehouse.getAisleList().get(0).getFirstRackRow().getRackByIndex(5).addProduct(product1);
        testWarehouse.getAisleList().get(0).getSecondRackRow().getRackByIndex(5).addProduct(product2);

        List<Product> productPickList = new ArrayList<>();
        productPickList.add(product1);
        productPickList.add(product2);
        Order order1 = new Order(1, productPickList);


        for (Point2D pickPoint : testWarehouse.getPickingPoints(order1)) {
            System.out.println(pickPoint.getX() + ", " + pickPoint.getY());
        }

    }

}