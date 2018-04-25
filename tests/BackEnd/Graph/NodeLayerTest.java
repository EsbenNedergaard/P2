package BackEnd.Graph;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import Exceptions.NodeDoesNotExistException;
import BackEnd.Graph.NodeLayer;
import Exceptions.UnplacedNodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NodeLayerTest {
    private NodeLayer testLayer1;
    private NodeLayer testLayer2;
    private List<Node> nodeList;
    private final int GRID_SIZE = 3;

    @BeforeEach
    void beforeEach(){
        nodeList = new ArrayList<>();
        for(int x = 0; x < GRID_SIZE; x++){
            for(int y = 0; y < GRID_SIZE; y++) {
                Node node = new Node(new Point2D(x, y));
                nodeList.add(node);
            }
        }

        testLayer1 = new NodeLayer(nodeList, 0);
        testLayer2 = new NodeLayer(nodeList, 1);
    }


    //Testing that we setup our reference correctly
    @Test
    void testSetupReferences() {
        Node temp = new Node(new Point2D(1,1));
        assertThrows(UnplacedNodeException.class, ()-> temp.getTime());

        temp.setNodeLayer(testLayer1);
        assertEquals(0, temp.getTime());
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

    //Testing that we cant get a pointer to a node outside layer
    @Test
    void getNodePointer4() {
        assertThrows(NodeDoesNotExistException.class, ()-> testLayer1.getNodePointer(GRID_SIZE+1, GRID_SIZE+1));
    }


    //We check that the correct neighbours are set
    @Test
    void setAllNeighbourNodesForLayer1() {
        testLayer1.setAllNeighbourNodesForLayer(testLayer2);

        List<Node> expectedNeighbours = new ArrayList<>();

        expectedNeighbours.add(testLayer2.getNodePointer(0,1));
        expectedNeighbours.add(testLayer2.getNodePointer(1,0));
        expectedNeighbours.add(testLayer2.getNodePointer(1,1));
        expectedNeighbours.add(testLayer2.getNodePointer(1,2));
        expectedNeighbours.add(testLayer2.getNodePointer(2,1));

        Node tempNodePointer = testLayer1.getNodePointer(1, 1);
        assertEquals(expectedNeighbours, tempNodePointer.getNeighbourNodes());
    }


    //We check that a layer cant get neighbours from a layer above it
    @Test
    void setAllNeighbourNodesForLayer2() {
        testLayer2.setAllNeighbourNodesForLayer(testLayer1);

        Node tempNodePointer = testLayer2.getNodePointer(0,0);
        assertEquals(0 , tempNodePointer.getNeighbourNodes().size());
    }

    @Test
    void getTime() {
        assertEquals(0, testLayer1.getTime());
    }

    @Test
    void getNodeList() {
        for(int i = 0; i < nodeList.size(); i++) {
            assertEquals(nodeList.get(i).getX(), testLayer1.getNodeList().get(i).getX());
            assertEquals(nodeList.get(i).getY(), testLayer1.getNodeList().get(i).getY());
        }
    }

    //Testing that the node gets removed from the layer
    @Test
    void removeNode1() {
        testLayer1.removeNode(testLayer1.getNodePointer(1,1));
        assertThrows(NodeDoesNotExistException.class, ()-> testLayer1.getNodePointer(1, 1));
    }


    /*Checking that not gets removed if you try to remove a node that does not belong to the layer
      even if there is a node with the same coordinates*/
    @Test
    void removeNode2() {
        //We do this so we dont get a pointer to the nodeList in the layer
        List<Node> tempNodeList = new ArrayList<>(testLayer1.getNodeList());

        testLayer1.removeNode(testLayer2.getNodePointer(1,1));
        assertEquals(tempNodeList, testLayer1.getNodeList());
    }


    @Test
    void equals1() {
        //We create a nodeLayer in the same way as for testLayer1 so they should be equal
        NodeLayer temp = new NodeLayer(nodeList, 0);

        assertEquals(temp, testLayer1);
    }

    @Test
    void equals2() {
        NodeLayer temp = new NodeLayer(nodeList, 0);
        temp.removeNode(temp.getNodePointer(0,0));

        assertNotEquals(temp, testLayer1);
    }

    @Test
    void equals3() {
        NodeLayer temp = new NodeLayer(nodeList, 0);
        testLayer1.removeNode(temp.getNodePointer(0,0));

        assertNotEquals(temp, testLayer1);
    }

    //We test that our 2 test layers should not be equal because they have different times*/
    @Test
    void equals4() {
        assertNotEquals(testLayer1, testLayer2);
    }



}