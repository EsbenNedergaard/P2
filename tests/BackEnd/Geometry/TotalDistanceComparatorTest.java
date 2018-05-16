package BackEnd.Geometry;

import BackEnd.Geometry.Node.Comparators.TotalDistanceComparator;
import BackEnd.Geometry.Node.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TotalDistanceComparatorTest {
    // Same total distance, different distances to end and different y values. Should prioritize node with shortest distance to end.
    @Test
    void testMethod_compare_01(){
        Node firstNode = new Node(new Point2D(1, 1));
        Node secondNode = new Node(new Point2D(2, 2));

        firstNode.setDistanceToEnd(6);
        secondNode.setDistanceToEnd(4);


        // Let starting node be (0,0)
        firstNode.setDistanceFromStart(2);
        secondNode.setDistanceFromStart(4);


        TotalDistanceComparator compareElement = new TotalDistanceComparator();

        assertTrue(compareElement.compare(firstNode, secondNode) > 0);
        assertTrue(compareElement.compare(secondNode, firstNode) < 0);

    }

    // Same total distance, same distance to end and different y values. Should prioritize. Should prioritize node with highest y value
    @Test
    void testMethod_compare_02(){
        Node firstNode = new Node(new Point2D(2,2));
        Node secondNode = new Node(new Point2D(4, 0));

        firstNode.setDistanceToEnd(4);
        secondNode.setDistanceToEnd(4);

        // Let starting node be (0,0)
        firstNode.setDistanceFromStart(4);
        secondNode.setDistanceFromStart(4);

        TotalDistanceComparator compareElement = new TotalDistanceComparator();

        assertTrue(compareElement.compare(firstNode, secondNode) < 0);
        assertTrue(compareElement.compare(secondNode, firstNode) > 0);
    }

    // Different total distances. Should prioritize node with lowest total distance
    @Test
    void testMethod_compare_03(){
        Node firstNode = new Node(new Point2D(3,3));
        Node secondNode = new Node(new Point2D(4, 0));

        firstNode.setDistanceToEnd(2);
        secondNode.setDistanceToEnd(4);

        // Let starting node be (0,0)
        firstNode.setDistanceFromStart(2);
        secondNode.setDistanceFromStart(2);

        TotalDistanceComparator compareElement = new TotalDistanceComparator();

        assertTrue(compareElement.compare(firstNode, secondNode) < 0);
        assertTrue(compareElement.compare(secondNode, firstNode) > 0);
    }

    // Same total distance, same distance to end and same y value. Both nodes should be prioritized equally
    @Test
    void testMethod_compare_04() {
        Node firstNode = new Node(new Point2D(3,3));
        Node secondNode = new Node(new Point2D(5, 3));

        firstNode.setDistanceToEnd(2);
        secondNode.setDistanceToEnd(2);

        // Let starting node be (0,0)
        firstNode.setDistanceFromStart(3);
        secondNode.setDistanceFromStart(3);

        TotalDistanceComparator compareElement = new TotalDistanceComparator();

        assertTrue(compareElement.compare(firstNode, secondNode) == 0);
        assertTrue(compareElement.compare(secondNode, firstNode) == 0);
    }
}