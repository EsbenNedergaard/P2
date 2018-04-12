package Geometry;

import Exceptions.RouteNotPossibleException;
import Warehouse.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RouteTestWarehouse {
    private final int MAX_TIME = 100;

    @Test
    void testOnWareHouse() {
        Warehouse testWarehouse = new Warehouse22b();
        final int GRID_HEIGHT = testWarehouse.getWidth();
        final int GRID_LENGTH = testWarehouse.getLength();

        List<Node> warehouseNodeList = testWarehouse.getNodeList();

        BaseLayer baseLayer = new BaseLayer(warehouseNodeList);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);


        Node startNode = new Node(new Point2D(0, 0));
        //Node endNode = new Node(new Point2D((testWarehouse.getLength() - 1), (testWarehouse.getWidth() - 1)));
        Node endNode = new Node(new Point2D(15,4));
        //Node endNode = new Node(new Point2D(15,5));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        ArrayList<Node> testResultRoute = new ArrayList<>();
        try {
            testResultRoute = testPathFinder.findShortestRoute(startNode, endNode);
        }
        catch (RouteNotPossibleException e) {
            System.out.println(e.toString());
        }

        Character[][] graphic = new Character[GRID_LENGTH][GRID_HEIGHT];
        for(int x = 0; x < GRID_LENGTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                graphic[x][y] = ' ';
            }
        }

        for(Node n : testResultRoute) {
            graphic[n.getX()][n.getY()] = 'o';
        }
        for(Node n : baseLayer.getStationaryObstacles()) {
            graphic[n.getX()][n.getY()] = 'x';
        }

        for(int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_LENGTH; x++) {
                System.out.print(graphic[x][y] + " ");
            }
            System.out.println();
        }
    }

}
