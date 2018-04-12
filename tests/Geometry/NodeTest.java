package Geometry;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

class NodeTest {

    Node node1;
    Node node2;

    @BeforeEach
    void setupObjects(){
        node1 = new Node(new Point2D(1, 1));
        node2 = new Node(node1);
    }

    @Test
    void testSetter_setTimeLayer(){
        Node nodeToTest1 = new Node(new Point2D(1,1));
        Node nodeToTest2 = new Node(new Point2D(2, 2));


        List<Node> nodeList = new ArrayList<>();

        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(nodeToTest1);
        nodeList.add(nodeToTest2);


        NodeLayer nodeLayer = new NodeLayer(nodeList, 0);


        nodeToTest1.setTimeLayer(nodeLayer);
        nodeToTest2.setTimeLayer(nodeLayer);

        node1.setTimeLayer(nodeLayer);
        node2.setTimeLayer(nodeLayer);

        assertTrue(nodeLayer.equals(node1.getTimeLayer()));
    }

    @Test
    void testEquality(){
        Node nodeToTest1 = new Node(new Point2D(1,1));
        Node nodeToTest2 = new Node(new Point2D(2, 2));


        List<Node> nodeList = new ArrayList<>();
        NodeLayer nodeLayer = new NodeLayer(nodeList,0);


        nodeToTest1.setTimeLayer(nodeLayer);
        nodeToTest2.setTimeLayer(nodeLayer);

        node1.setTimeLayer(nodeLayer);
        node2.setTimeLayer(nodeLayer);


        assertEquals(node1, nodeToTest1);
        assertNotEquals(node1, nodeToTest2);

        assertEquals(node2, nodeToTest1);
        assertNotEquals(node2, nodeToTest2);
    }

    @Test
    void testGetter_getTime(){
        assertThrows(NullPointerException.class, ()-> node1.getTime());
        assertThrows(NullPointerException.class, ()-> node2.getTime());

        Node node = new Node(new Point2D(3, 3));

        List<Node> nodeList = new ArrayList<>();

        NodeLayer nodeLayer = new NodeLayer(nodeList, 2);

        node.setTimeLayer(nodeLayer);

        assertEquals(2, node.getTime());
    }

    @Test
    void testMethod_isNeighbour(){

        List<Node> nodeList = new ArrayList<>();

        for(int x = 0; x < 30; x++){
            for(int y = 0; y < 30; y++) {
                Node node = new Node(new Point2D(x, y));
                nodeList.add(node);
            }
        }

        NodeLayer nodeLayer1 = new NodeLayer(nodeList, 1);
        NodeLayer nodeLayer2 = new NodeLayer(nodeList, 2);

        nodeLayer1.setAllNeighbourNodesForLayer(nodeLayer2);


        assertTrue(node1.isNeighbour(node2));
    }
}