package Geometry;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GraphTest {
    private final int GRID_HEIGHT = 10;
    private final int GRID_LENGTH = 10;
    private final int MAX_TIME = 100;


    @Test
    public void testAlgorithm() {
        ArrayList<Node> inputSet = new ArrayList<>();
        for (int k = 0; k < MAX_TIME; k++) {
            for (int i = 0; i < GRID_HEIGHT; i++) {
                for (int j = 0; j < GRID_LENGTH; j++) {
                    if (!(i == 8 && j > 4) && !(i == 1 && j < 5) && !(i == 3 && j > 1) && !(i == 1 && j == 5  && k == 6) && !(i == 0 && j == 3  && k == 4) && !(i == 1 && j == 6  && k == 7) && !(i == 1 && j == 7  && k == 8) && !(i == 1 && j == 8  && k == 9) && !(i == 1 && j == 9  && k == 10)) {
                        inputSet.add(new Node(new Point2D(j, i), k));
                    }
                }
            }
        }

        Node startNode = new Node(new Point2D(0, 0), 0);
        Node endNode = new Node(new Point2D(GRID_HEIGHT - 1, GRID_LENGTH - 1));

        Graph testGraph = new Graph(inputSet);

        ArrayList<Node> testResultRoute = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            System.out.println("Attempt: " + i);
            testResultRoute = testGraph.findShortestRoute(startNode, endNode);
        }
        int i = 0;
        for (Node n : testResultRoute) {
            System.out.println(i + ". (" + n.getX() + ", " + n.getY() + ", " + n.getTime() + ")");
            i++;
        }
    }

}