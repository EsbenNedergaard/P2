package Warehouse;

import Geometry.Node;
import Geometry.Point2D;
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
        testWarehouse.getAisleList().get(0).getFirstRackRow().addProduct(new Product(1));
        testWarehouse.getAisleList().get(1).getSecondRackRow().getRackByIndex(5).addProduct(new Product(2));

        List<Product> productPickList = new ArrayList<>();
        productPickList.add(testWarehouse.getAisleList().get(0).getFirstRackRow().getRackByIndex(0).getProduct(0));
        productPickList.add(testWarehouse.getAisleList().get(1).getSecondRackRow().getRackByIndex(5).getProduct(0));


        for (Point2D pickPoint : testWarehouse.getPickingPoints(productPickList)) {
            System.out.println(pickPoint.getX() + ", " + pickPoint.getY());
        }
    }

}