package Warehouse;

import Geometry.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

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

}