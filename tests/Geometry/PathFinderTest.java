package Geometry;

import Exceptions.RouteNotPossibleException;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {
    private final int MAX_TIME = 250;
    private final int GRID_SIZE = 10;
    private SpaceTimeGrid spaceTimeGrid;
    private List<Node> inputSet;

    @BeforeEach
    void beforeEach() {
        inputSet = new ArrayList<>();
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                Node temp = new Node(new Point2D(x, y));
                inputSet.add(temp);
            }
        }

        //Setting in standard obstacles
        for (Node n : inputSet) {
            int x = n.getX();
            int y = n.getY();
            if ((y == 1 && x < 5)|| (y == 8 && x > 1) || (x == 5 && (1 < y && y < 6)) || (x == 1 && (3 <= y && y < 8))) {
                n.setNodeType(NodeType.OBSTACLE);
            }
        }
    }

    @Test
    void testAlgorithm() {
        BaseLayer baseLayer = new BaseLayer(inputSet);
        spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_SIZE-1,GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);
        List<Node> testResultRoute = new ArrayList<>();
        try {
            testResultRoute = testPathFinder.findShortestRoute(startNode, endNode);
        }
        catch (RouteNotPossibleException e) {
            System.out.println(e.toString());
        }
        TempRoutePrinter printer = new TempRoutePrinter(testResultRoute, baseLayer);
        printer.printRoute(GRID_SIZE, GRID_SIZE);
    }

    @Test
    void testRemoveRoute() {
        BaseLayer baseLayer = new BaseLayer(inputSet);
        spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_SIZE-1,GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        List<Node> testNonPermantObstruction = new ArrayList<>();
        for(int i = 0; i < MAX_TIME/2; i++) {
            testNonPermantObstruction.add(testPathFinder.getSpaceTimeGrid().getNodePointer(1, 2, i));
        }
        testPathFinder.removeRoute(testNonPermantObstruction);

        List<Node> testResultRoute = new ArrayList<>();
        try {
            testResultRoute = testPathFinder.findShortestRoute(startNode, endNode);
        }
        catch (RouteNotPossibleException e) {
            System.out.println(e.toString());
        }

        TempRoutePrinter printer = new TempRoutePrinter(testResultRoute, baseLayer);
        printer.printRoute(GRID_SIZE, GRID_SIZE);
        //TODO: make some asserts here
    }

    @Test
    void testIllegalEndPoint(){
        //Adding some extra obstacles
        for(Node n : inputSet) {
            if (n.getX() == 9 && n.getY() == 9) {
                n.setNodeType(NodeType.OBSTACLE);
            }
        }

        BaseLayer baseLayer = new BaseLayer(inputSet);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_SIZE-1, GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);
        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode));
    }

    @Test
    void testIllegalStartPoint(){
        //Adding some extra obstacles
        for(Node n : inputSet) {
            if (n.getX() == 0 && n.getY() == 0) {
                n.setNodeType(NodeType.OBSTACLE);
            }
        }
        BaseLayer baseLayer = new BaseLayer(inputSet);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_SIZE-1, GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode));
    }

    @Test
    void testBoxedInPoint(){
        //Adding some extra obstacles
        for(Node n : inputSet) {
            if ((n.getX() == 1 && n.getY() == 0) || (n.getX() == 0 && n.getY() == 1) ){
                n.setNodeType(NodeType.OBSTACLE);
            }
        }
        BaseLayer baseLayer = new BaseLayer(inputSet);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_SIZE-1, GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode));
    }

    @Test
    void testStartPointOutsideGrid1(){
        BaseLayer baseLayer = new BaseLayer(inputSet);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(-1, -1));
        Node endNode = new Node(new Point2D(GRID_SIZE-1, GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode));
    }

    @Test
    void testStartPointOutsideGrid2(){
        BaseLayer baseLayer = new BaseLayer(inputSet);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(GRID_SIZE, GRID_SIZE));
        Node endNode = new Node(new Point2D(GRID_SIZE-1, GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode));
    }

    @Test
    void testEndPointOutsideGrid1(){
        BaseLayer baseLayer = new BaseLayer(inputSet);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(-1, -1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode));
    }

    @Test
    void testEndPointOutsideGrid2(){
        BaseLayer baseLayer = new BaseLayer(inputSet);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_SIZE, GRID_SIZE));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode));
    }



}