package Geometry;

import Exceptions.NodeDoesNotExistException;
import Exceptions.RouteNotPossibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTimeGridTest {
    private final int MAX_TIME = 3;
    private final int GRID_SIZE = 3;
    private SpaceTimeGrid spaceTimeGrid;
    private List<Node> inputSet;

    @BeforeEach
    void beforeEach() {
        inputSet = new ArrayList<>();
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                Node temp = new Node(new Point2D(x, y));
                inputSet.add(temp);
            }
        }
        BaseLayer baseLayer = new BaseLayer(inputSet);
        spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);
    }

    //We check that they actually point at the same object
    @Test
    void getNodePointer1(){
        Node tempNodePointer = spaceTimeGrid.getNodePointer(0, 0,0);

        assertTrue(tempNodePointer == spaceTimeGrid.getNodeLayerList().get(0).getNodeList().get(0));
    }

    //We check if we can change the x value inside the layer through the pointer
    @Test
    void getNodePointer2() {
        Node tempNodePointer = spaceTimeGrid.getNodePointer(0, 0, 0);
        tempNodePointer.setX(1);

        assertEquals(1, spaceTimeGrid.getNodeLayerList().get(0).getNodeList().get(0).getX());
    }

    //We do the same for the y value
    @Test
    void getNodePointer3() {
        Node tempNodePointer = spaceTimeGrid.getNodePointer(0, 0, 0);
        tempNodePointer.setY(1);

        assertEquals(1, spaceTimeGrid.getNodeLayerList().get(0).getNodeList().get(0).getY());
    }

    //Testing that we cant get a pointer to a node outside grid
    @Test
    void getNodePointer4() {
        assertThrows(NodeDoesNotExistException.class, ()-> spaceTimeGrid.getNodePointer(GRID_SIZE+1, GRID_SIZE+1, 0));
    }

    //Testing with a too high time
    @Test
    void getNodePointer5() {
        assertThrows(NodeDoesNotExistException.class, ()-> spaceTimeGrid.getNodePointer(1, 1, MAX_TIME+1));
    }
     
    @Test
    void getAllNodes() {
    }

    @Test
    void getMaxTime() {
        assertEquals(MAX_TIME, spaceTimeGrid.getMaxTime());
    }

    @Test
    void getBaseLayer() {

    }

    @Test
    void removeNode() {
        //We make a nodeList that does not contain (0,0)
        List<Node> nodeList = new ArrayList<>();
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                if (!(x == 0 && y == 0)) {
                    Node temp = new Node(new Point2D(x, y));
                    nodeList.add(temp);
                }
            }
        }
        NodeLayer temp = new NodeLayer(nodeList, 0);

        assertNotEquals(temp, spaceTimeGrid.getNodeLayerList().get(0));

        //We then remove (0,0), and check that the nodeLayers are equal, which means we removed the correct node.
        spaceTimeGrid.removeNode(spaceTimeGrid.getNodePointer(0,0,0));
        assertEquals(temp, spaceTimeGrid.getNodeLayerList().get(0));
    }

    //TODO: make better assertEquals in the end.
    @Test
    void removeNeighbourNode() {
        //We make a pointer to (0,0), (0, 1) and (1,0) in the first layer
        Node temp1 = spaceTimeGrid.getNodePointer(0,0,0);
        Node temp2 = spaceTimeGrid.getNodePointer(0,1,0);
        Node temp3 = spaceTimeGrid.getNodePointer(1,0,0);

        //We make a pointer to (0,0) in the second layer
        Node nodeToRemove = spaceTimeGrid.getNodePointer(0,0,1);

        assertEquals(3,  temp1.getNeighbourNodes().size());
        assertEquals(4,  temp2.getNeighbourNodes().size());
        assertEquals(4,  temp3.getNeighbourNodes().size());

        spaceTimeGrid.removeNode(nodeToRemove);
        assertEquals(2, temp1.getNeighbourNodes().size());
        assertEquals(3, temp2.getNeighbourNodes().size());
        assertEquals(3, temp3.getNeighbourNodes().size());
    }
}