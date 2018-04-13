package Geometry;

import Exceptions.NodeDoesNotExistException;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NodeLayerTest {
    NodeLayer testLayer1;
    NodeLayer testLayer2;
    final int GRID_SIZE = 3;

    @BeforeEach
    void beforeEach(){
        List<Node> nodeList = new ArrayList<>();
        for(int x = 0; x < GRID_SIZE; x++){
            for(int y = 0; y < GRID_SIZE; y++) {
                Node node = new Node(new Point2D(x, y));
                nodeList.add(node);
            }
        }

        testLayer1 = new NodeLayer(nodeList, 1);
        testLayer2 = new NodeLayer(nodeList, 2);
    }

    //We check that they actually point at the same object
    @Test
    void getNodePointer1() {
        Node tempNodePointer = testLayer1.getNodePointer(0, 0);

        assertTrue(tempNodePointer == testLayer1.getNodeList().get(0));
    }

    //We check if we can change the x value inside the layer through the pointer
    @Test
    void getNodePointer2() {
        Node tempNodePointer = testLayer1.getNodePointer(0, 0);
        tempNodePointer.setX(1);

        assertEquals(1, testLayer1.getNodeList().get(0).getX());
    }

    //We do the same for the y value
    @Test
    void getNodePointer3() {
        Node tempNodePointer = testLayer1.getNodePointer(0, 0);
        tempNodePointer.setY(1);

        assertEquals(1, testLayer1.getNodeList().get(0).getY());
    }


    @Test
    void getNodePointer4() {
        assertThrows(NodeDoesNotExistException.class, ()-> testLayer1.getNodePointer(GRID_SIZE+1, GRID_SIZE+1));
    }


    @Test
    void setAllNeighbourNodesForLayer1() {
        testLayer1.setAllNeighbourNodesForLayer(testLayer2);


    }

    @Test
    void setAllNeighbourNodesForLayer2() {
        testLayer2.setAllNeighbourNodesForLayer(testLayer1);

        Node tempNodePointer = testLayer2.getNodePointer(0,0);

        assertEquals(0 , tempNodePointer.getNeighbourNodes().size());
    }

    @Test
    void getTime() {
    }

    @Test
    void getNodeList() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void removeNodeFromNeighbourLists() {
    }

    @Test
    void equals() {
    }
}