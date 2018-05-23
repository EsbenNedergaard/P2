package Warehouse;

import BackEnd.Geometry.PickingPoint;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
    void getPickingPoints01() {
        List<Integer> productIdList = new ArrayList<>();
        productIdList.add(1);
        productIdList.add(273);

        List<PickingPoint> pickingPointList = testWarehouse.getPickingPoints(productIdList);

        // The two pickingPoints have equal Point2D
        assertEquals(pickingPointList.get(0).getX(), pickingPointList.get(1).getX());
        assertEquals(pickingPointList.get(0).getY(), pickingPointList.get(1).getY());
    }

    @Test
    void getPickingPoints02() {
        List<Integer> productIdList = new ArrayList<>();
        productIdList.add(1);
        productIdList.add(273);

        List<PickingPoint> pickingPointList = testWarehouse.getPickingPoints(productIdList);

        assertNotEquals(pickingPointList.get(0).getProduct(), pickingPointList.get(1).getProduct());
    }

}