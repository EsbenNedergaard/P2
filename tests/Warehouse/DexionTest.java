package Warehouse;

import BackEnd.Geometry.Point2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DexionTest {
    private Dexion testWarehouse = new Dexion();

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
    void getBaseLayer() {
        /*List<Node> temp = testWarehouse.getBaseLayer();
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
        }*/
    }

    @Test
    void getPickingPoints() {
        Product product1 = new Product(1);
        Product product2 = new Product(2);

        List<Product> productPickList = new ArrayList<>();
        productPickList.add(product1);
        productPickList.add(product2);


        for (Point2D pickPoint : testWarehouse.getPickingPoints(productPickList)) {
            System.out.println(pickPoint.getX() + ", " + pickPoint.getY());
        }

    }

    @Test
    void getPickingPointsFromIDs() {
        List<Integer> productIDList = new ArrayList<>();
        productIDList.add(8);
        productIDList.add(80);

        for (Point2D pickPoint : testWarehouse.getPickingPointsFromIDs(productIDList)) {
            System.out.println(pickPoint.getX() + ", " + pickPoint.getY());
        }
    }

}