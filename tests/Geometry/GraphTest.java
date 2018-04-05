package Geometry;

import org.junit.jupiter.api.Test;

import java.util.*;

class GraphTest {
    private final int GRID_HEIGHT = 42;
    private final int GRID_LENGTH = 16;


    @Test
    void testAlgorithm() {
        ArrayList<Node> inputSet = new ArrayList<>();
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_LENGTH; j++) {
                inputSet.add(new Node(new Point2D(i, j)));
            }
        }


        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(41, 15));

        Graph testGraph = new Graph(inputSet);

        ArrayList<Node> testResultRoute = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            System.out.println("Attempt: " + i);
            testResultRoute = testGraph.findShortestRoute(startNode, endNode);
        }
        int i = 0;
        for (Node n : testResultRoute) {
            System.out.println(i + ". (" + n.getX() + ", " + n.getY() + ")");
            i++;
        }
    }

}