package BackEnd.Graph;

import BackEnd.Geometry.Node.Node;
import BackEnd.Geometry.Node.NodeType;
import BackEnd.Geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BaseLayerTest {

    BaseLayer bl;
    List<Node> nodeList;


    @BeforeEach
    void setUp() {
        nodeList = new ArrayList<>();
        // Create nodes in a layer 36x12
        for (int x = 0; x < 36; x++) {
            for (int y = 0; y < 12; y++) {
                Node node = new Node(new Point2D(x, y));
                nodeList.add(node);
            }
        }
        bl = new BaseLayer(nodeList);
    }

    @Test
    void testMethod_getNodeList() {
        List<Node> nodeListTest = new ArrayList<>();
        for (int x = 0; x < 36; x++) {
            for (int y = 0; y < 12; y++) {
                Node node = new Node(new Point2D(x, y));
                nodeListTest.add(node);
            }
        }

        for (int i = 0; i < 36 * 12; i++) {
            // BaseLayer does not contain 'time'. Therefore we test equality among the x and y.
            assertEquals(nodeListTest.get(i).getX(), nodeList.get(i).getX());
            assertEquals(nodeListTest.get(i).getY(), nodeList.get(i).getY());
        }
    }

    @Test
    void testMethod_getStationaryObstacles_01() {
        assertTrue(bl.getStationaryObstacles().isEmpty());
    }

    @Test
    void testMethod_getStationaryObstacles_02() {
        List<Node> nodeListTest = new ArrayList<>();
        for (int x = 0; x < 36; x++) {
            for (int y = 0; y < 12; y++) {
                Node node = new Node(new Point2D(x, y));
                if(x == 20 && y == 10)
                    node.setNodeType(NodeType.OBSTACLE);
                nodeListTest.add(node);
            }
        }
        BaseLayer baseLayer = new BaseLayer(nodeListTest);

        List<Node> obstacleArray = new ArrayList<>();

        Node node = new Node(new Point2D(20, 10));
        node.setNodeType(NodeType.OBSTACLE);

        obstacleArray.add(node);

        assertTrue(!baseLayer.getStationaryObstacles().isEmpty());
        assertEquals(1, baseLayer.getStationaryObstacles().size());

        assertEquals(obstacleArray.get(0).getX(), baseLayer.getStationaryObstacles().get(0).getX());
    }
}