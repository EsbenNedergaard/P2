package Geometry;

import Exceptions.RouteNotPossibleException;
import Warehouse.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RouteTestWarehouse {
    private final int MAX_TIME = 100;

    @Test
    void testOnWareHouse() {
        Warehouse testWarehouse = new Warehouse22b();

        List<Node> warehouseNodeList = testWarehouse.getNodeList();

        BaseLayer baseLayer = new BaseLayer(warehouseNodeList);
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);


        Node startNode = new Node(new Point2D(0, 0));
        //Node endNode = new Node(new Point2D((testWarehouse.getLength() - 1), (testWarehouse.getWidth() - 1)));
        Node endNode = new Node(new Point2D(15,4));
        //Node endNode = new Node(new Point2D(15,5));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        List<Node> testResultRoute = new ArrayList<>();
        try {
            for (int i = 0; i < 100; i++) {
                testResultRoute = testPathFinder.findShortestRoute(startNode, endNode);
            }
        }
        catch (RouteNotPossibleException e) {
            System.out.println(e.toString());
        }

        TempRoutePrinter printer = new TempRoutePrinter(testResultRoute, baseLayer);
        printer.printRoute(testWarehouse.getLength(), testWarehouse.getWidth());
    }

}
