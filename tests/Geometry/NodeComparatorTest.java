package Geometry;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


class NodeComparatorTest {


    // Ved ikke helt hvad der skal testes.
    @Test
    void testMethod_compare(){
        Node firstNode = new Node(new Point2D(1, 1));
        Node secondNode = new Node(new Point2D(2, 2));
        Node thirdNode = new Node(new Point2D(1, 1));
        Node fourthNode = new Node(new Point2D(1,2));

        Node endNode = new Node(new Point2D(4,4));

        firstNode.setDistanceToEnd(endNode);
        secondNode.setDistanceToEnd(endNode);
        thirdNode.setDistanceToEnd(endNode);
        fourthNode.setDistanceToEnd(endNode);

        // Let starting node be (0,0)
        firstNode.setDistanceFromStart(2);
        secondNode.setDistanceFromStart(4);
        thirdNode.setDistanceFromStart(2);
        fourthNode.setDistanceFromStart(3);

        NodeComparator compareElement = new NodeComparator();

        assertTrue(compareElement.compare(firstNode, secondNode) == 0);

    }
}