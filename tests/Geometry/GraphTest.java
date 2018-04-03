package Geometry;

import org.junit.jupiter.api.Test;

import java.util.*;

class GraphTest {

    @Test
    public void testAlgorithm() {
        ArrayList<Node> inputSet = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 3; j++) {
                inputSet.add(new Node(new Point2D(i, j)));
            }
        }
        Graph testGraph = new Graph(inputSet);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(60, 2));

        ArrayList<Node> testResultRoute = testGraph.findShortestRoute(startNode, endNode);

        int i = 0;
        for (Node n : testResultRoute) {
            System.out.println(i + ". (" + n.getX() + ", " + n.getY() + ")");
            i++;
        }
    }

}