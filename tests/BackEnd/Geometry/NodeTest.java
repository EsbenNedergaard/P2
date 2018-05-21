package BackEnd.Geometry;

import BackEnd.Geometry.Node.Node;
import BackEnd.Graph.NodeLayer;
import BackEnd.Exceptions.UnplacedNodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static BackEnd.Geometry.Node.NodeType.WALKABLE;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    Node node1;
    Node node2;

    @BeforeEach
    void setupObjects() {
        node1 = new Node(new Point2D(1, 1));
        node2 = new Node(node1);
    }

    @Test
    void testSetter_setTimeLayer() {
        Node nodeToTest1 = new Node(new Point2D(1, 1));
        Node nodeToTest2 = new Node(new Point2D(2, 2));


        List<Node> nodeList = new ArrayList<>();

        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(nodeToTest1);
        nodeList.add(nodeToTest2);


        NodeLayer nodeLayer = new NodeLayer(nodeList, 0);


        nodeToTest1.setNodeLayer(nodeLayer);
        nodeToTest2.setNodeLayer(nodeLayer);

        node1.setNodeLayer(nodeLayer);
        node2.setNodeLayer(nodeLayer);

        assertTrue(nodeLayer.equals(node1.getNodeLayerPointer()));
    }

    @Test
    void testEquality() {
        Node nodeToTest1 = new Node(new Point2D(1, 1));
        Node nodeToTest2 = new Node(new Point2D(2, 2));


        List<Node> nodeList = new ArrayList<>();
        NodeLayer nodeLayer = new NodeLayer(nodeList, 0);


        nodeToTest1.setNodeLayer(nodeLayer);
        nodeToTest2.setNodeLayer(nodeLayer);

        node1.setNodeLayer(nodeLayer);
        node2.setNodeLayer(nodeLayer);


        assertEquals(node1, nodeToTest1);
        assertNotEquals(node1, nodeToTest2);

        assertEquals(node2, nodeToTest1);
        assertNotEquals(node2, nodeToTest2);
    }

    @Test
    void testGetter_getTime() {
        assertThrows(UnplacedNodeException.class, () -> node1.getTime());
        assertThrows(UnplacedNodeException.class, () -> node2.getTime());

        Node node = new Node(new Point2D(3, 3));

        List<Node> nodeList = new ArrayList<>();

        NodeLayer nodeLayer = new NodeLayer(nodeList, 2);

        node.setNodeLayer(nodeLayer);

        assertEquals(2, node.getTime());
    }

    @Test
    void testMethod_isNeighbour() {

        List<Node> nodeList = new ArrayList<>();

        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++) {
                Node node = new Node(new Point2D(x, y));
                nodeList.add(node);
            }
        }

        NodeLayer nodeLayer1 = new NodeLayer(nodeList, 1);
        NodeLayer nodeLayer2 = new NodeLayer(nodeList, 2);

        nodeLayer1.setAllNeighbourNodesForLayer(nodeLayer2);

        //This should be element (0,0)
        node1 = nodeLayer1.getNodeList().get(0);

        //Layer2 element 1 is (0,1)
        assertEquals(nodeLayer2.getNodeList().get(1), node1.getNeighbourNodes().get(1));


        //Layer2 element 2 is (0,2)
        assertNotEquals(nodeLayer2.getNodeList().get(2), node1.getNeighbourNodes().get(1));
    }

    @Test
    void testMethod_setDistanceToEnd() {
        Node endNode = new Node(new Point2D(3, 3));
        node1.setDistanceToEnd(4);

        // Expect the sum of the difference between the two point coordinates: (1,1), (3,3), which is 2 + 2 = 4
        assertEquals(4, node1.getDistanceToEnd());

    }

    @Test
    void testConstructor() {
        assertEquals(WALKABLE, node1.getNodeType());
    }
}