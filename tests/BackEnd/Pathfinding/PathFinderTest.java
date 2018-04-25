package BackEnd.Pathfinding;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.NodeType;
import BackEnd.Geometry.Point2D;
import BackEnd.Pathfinding.PathFinder;
import BackEnd.TempRoutePrinter;
import Exceptions.RouteNotPossibleException;
import BackEnd.Graph.BaseLayer;
import BackEnd.Graph.SpaceTimeGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {
    private final int MAX_TIME = 100;
    private final int GRID_SIZE = 10;
    private final int START_TIME = 0;
    private SpaceTimeGrid spaceTimeGrid;
    private List<Node> inputSet;
    private BaseLayer baseLayer;

    //TODO: Få lavet tests til de nye checks af værdier og lign.

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
        baseLayer = new BaseLayer(inputSet);
        spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);
    }

    // Testing if the path calculated by the algorithm is the actual shortest path
    @Test
    void testAlgorithm() {
        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_SIZE-1,GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);
        PickingRoute testResultRoute = new PickingRoute();
        try {
            testResultRoute = testPathFinder.findShortestRoute(startNode, endNode, START_TIME);
        }
        catch (RouteNotPossibleException e) {
            System.out.println(e.toString());
        }

        ArrayList<Node> bestResultRoute = new ArrayList<>();
        bestResultRoute.add(spaceTimeGrid.getNodePointer(0,0,0));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(1,0,1));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(2,0,2));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(3,0,3));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(4,0,4));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(5,0,5));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(5,1,6));

        bestResultRoute.add(spaceTimeGrid.getNodePointer(6,1,7));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(6,2,8));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(6,3,9));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(6,4,10));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(6,5,11));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(6,6,12));

        bestResultRoute.add(spaceTimeGrid.getNodePointer(5,6,13));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(4,6,14));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(3,6,15));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(2,6,16));

        bestResultRoute.add(spaceTimeGrid.getNodePointer(2,5,17));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(2,4,18));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(2,3,19));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(2,2,20));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(1,2,21));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(0,2,22));

        bestResultRoute.add(spaceTimeGrid.getNodePointer(0,3,23));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(0,4,24));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(0,5,25));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(0,6,26));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(0,7,27));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(0,8,28));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(0,9,29));

        bestResultRoute.add(spaceTimeGrid.getNodePointer(1,9,30));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(2,9,31));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(3,9,32));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(4,9,33));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(5,9,34));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(6,9,35));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(7,9,36));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(8,9,37));
        bestResultRoute.add(spaceTimeGrid.getNodePointer(9,9,38));

        // Testing to see if the route computed is the actual shortest route
        //TODO: lave en equals i en PickingRoute
        assertEquals(bestResultRoute, testResultRoute.getRoute());
    }

    // An obstruction is added so another route will be taken
    @Test
    void testRemoveRoute() {
        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_SIZE-1,GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        List<Node> testNonPermanentObstruction = new ArrayList<>();

        // Adds an obstruction at a specific field until time reaches a specific number
        for(int i = 0; i < MAX_TIME/2; i++) {
            testNonPermanentObstruction.add(testPathFinder.getSpaceTimeGrid().getNodePointer(1, 2, i));
        }
        testPathFinder.removeRoute(testNonPermanentObstruction);

        PickingRoute testResultRoute = new PickingRoute();
        try {
            testResultRoute = testPathFinder.findShortestRoute(startNode, endNode, START_TIME);
        }
        catch (RouteNotPossibleException e) {
            System.out.println(e.toString());
        }

        TempRoutePrinter printer = new TempRoutePrinter(testResultRoute.getRoute(), baseLayer);
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
        baseLayer = new BaseLayer(inputSet);
        spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_SIZE-1, GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);
        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode, START_TIME));
    }

    @Test
    void testIllegalStartPoint(){
        //Adding some extra obstacles
        for(Node n : inputSet) {
            if (n.getX() == 0 && n.getY() == 0) {
                n.setNodeType(NodeType.OBSTACLE);
            }
        }
        baseLayer = new BaseLayer(inputSet);
        spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_SIZE-1, GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode, START_TIME));
    }

    @Test
    void testBoxedInPoint(){
        //Adding some extra obstacles
        for(Node n : inputSet) {
            if ((n.getX() == 1 && n.getY() == 0) || (n.getX() == 0 && n.getY() == 1) ){
                n.setNodeType(NodeType.OBSTACLE);
            }
        }
        baseLayer = new BaseLayer(inputSet);
        spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);

        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_SIZE-1, GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode, START_TIME));
    }

    @Test
    void testStartPointOutsideGrid1(){
        Node startNode = new Node(new Point2D(-1, -1));
        Node endNode = new Node(new Point2D(GRID_SIZE-1, GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode, START_TIME));
    }

    @Test
    void testStartPointOutsideGrid2(){
        Node startNode = new Node(new Point2D(GRID_SIZE, GRID_SIZE));
        Node endNode = new Node(new Point2D(GRID_SIZE-1, GRID_SIZE-1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode, START_TIME));
    }

    @Test
    void testEndPointOutsideGrid1(){
        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(-1, -1));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode, START_TIME));
    }

    @Test
    void testEndPointOutsideGrid2(){
        Node startNode = new Node(new Point2D(0, 0));
        Node endNode = new Node(new Point2D(GRID_SIZE, GRID_SIZE));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        assertThrows(RouteNotPossibleException.class, ()-> testPathFinder.findShortestRoute(startNode, endNode, START_TIME));
    }
}