package Geometry;

import Exceptions.RouteNotPossibleException;
import Warehouse.Warehouse;
import Warehouse.Warehouse22b;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SpaceTimeGridTest {
    private final int MAX_TIME = 100;
    private final int GRID_HEIGHT = 10;
    private final int GRID_LENGTH = 10;
    private List<Node> inputSet;

    @BeforeEach
    void beforeEach() {
        inputSet = new ArrayList<>();
        for (int x = 0; x < GRID_LENGTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                Node temp = new Node(new Point2D(x, y));
                inputSet.add(temp);
            }
        }
    }

    @Test
    void testAlgorithm() {
        //Setting in obstacles
        for (Node n : inputSet) {
            int x = n.getX();
            int y = n.getY();
            if ((y == 1 && x < 5)|| (y == 8 && x > 1) || (x == 5 && (1 < y && y < 6)) || (x == 1 && (3 <= y && y < 8))) {
                n.setNodeType("Obstacle");
            }
        }
        BaseLayer baseLayer = new BaseLayer(inputSet);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_HEIGHT - 1, GRID_LENGTH - 1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        List<Node> testResultRoute = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            System.out.println("Attempt: " + i);
            try {
                testResultRoute = testPathFinder.findShortestRoute(startNode, endNode);
            }
            catch (RouteNotPossibleException e) {
                System.out.println(e.toString());
            }
        }

        TempRoutePrinter printer = new TempRoutePrinter(testResultRoute, baseLayer);
        printer.printRoute(GRID_LENGTH, GRID_HEIGHT);
    }

    @Test
    void testIllegalEndPoint(){
        for(Node n : inputSet) {
            if (n.getX() == 9 && n.getY() == 9) {
                n.setNodeType("Obstacle");
            }
        }

        BaseLayer baseLayer = new BaseLayer(inputSet);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(9, 9));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode));
    }

    @Test
    void testIllegalStartPoint(){
        for(Node n : inputSet) {
            if (n.getX() == 0 && n.getY() == 0) {
                n.setNodeType("Obstacle");
            }
        }
        BaseLayer baseLayer = new BaseLayer(inputSet);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(9, 9));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode));
    }

    @Test
    void testBoxedInPoint(){
        for(Node n : inputSet) {
            if ((n.getX() == 1 && n.getY() == 0) || (n.getX() == 0 && n.getY() == 1) ){
                n.setNodeType("Obstacle");
            }
        }
        BaseLayer baseLayer = new BaseLayer(inputSet);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(9, 9));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode));
    }
}

