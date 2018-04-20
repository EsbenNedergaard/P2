package Geometry;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


class NodeComparatorTest {


    // Ved ikke helt hvad der skal testes.
    @Test
    void testMethod_compare_01(){
        Node firstNode = new Node(new Point2D(1, 1));
        Node secondNode = new Node(new Point2D(2, 2));


        Node endNode = new Node(new Point2D(4,4));

        firstNode.setDistanceToEnd(endNode);
        secondNode.setDistanceToEnd(endNode);


        // Let starting node be (0,0)
        firstNode.setDistanceFromStart(2);
        secondNode.setDistanceFromStart(4);


        NodeComparator compareElement = new NodeComparator();

        assertTrue(compareElement.compare(firstNode, secondNode) > 0);

    }

    @Test
    void testMethod_compare_02(){
        Node firstNode = new Node(new Point2D(1, 3));
        Node secondNode = new Node(new Point2D(1,2));

        Node endNode = new Node(new Point2D(4,4));

        firstNode.setDistanceToEnd(endNode);
        secondNode.setDistanceToEnd(endNode);

        // Let starting node be (0,0)
        firstNode.setDistanceFromStart(3);
        secondNode.setDistanceFromStart(2);

        NodeComparator compareElement = new NodeComparator();

        assertTrue(compareElement.compare(firstNode, secondNode) < 0);
    }

    @Test
    void testMethod_compare_03(){
        Node firstNode = new Node(new Point2D(1,2));
        Node secondNode = new Node(new Point2D(4, 0));

        Node endNode = new Node(new Point2D(4, 4));

        firstNode.setDistanceToEnd(endNode);
        secondNode.setDistanceToEnd(endNode);

        // Let starting node be (0,0)
        firstNode.setDistanceFromStart(2);
        secondNode.setDistanceFromStart(4);

        NodeComparator compareElement = new NodeComparator();

        assertTrue(compareElement.compare(firstNode, secondNode) < 0);

    }

    @Test
    void testMethod_compare_04(){
        Node firstNode = new Node(new Point2D(1,2));
        Node secondNode = new Node(new Point2D(4, 0));

        Node endNode = new Node(new Point2D(4, 4));

        firstNode.setDistanceToEnd(endNode);
        secondNode.setDistanceToEnd(endNode);

        // Let starting node be (0,0)
        firstNode.setDistanceFromStart(3);
        secondNode.setDistanceFromStart(2);

        NodeComparator compareElement = new NodeComparator();

        assertTrue(compareElement.compare(firstNode, secondNode) > 0);

    }


    @Test
    void testMethod_compare_05() {
        Node firstNode = new Node(new Point2D(3,2));
        Node secondNode = new Node(new Point2D(2, 2));

        Node endNode = new Node(new Point2D(4, 4));

        firstNode.setDistanceToEnd(endNode);
        secondNode.setDistanceToEnd(endNode);

        // Let starting node be (0,0)
        firstNode.setDistanceFromStart(3);
        secondNode.setDistanceFromStart(2);

        NodeComparator compareElement = new NodeComparator();

        assertTrue(compareElement.compare(firstNode, secondNode) == 0);
    }
}