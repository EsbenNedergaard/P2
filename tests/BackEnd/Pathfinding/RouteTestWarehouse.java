package BackEnd.Pathfinding;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import BackEnd.Pathfinding.PathFinder;
import BackEnd.TempRoutePrinter;
import Exceptions.RouteNotPossibleException;
import BackEnd.Graph.BaseLayer;
import BackEnd.Graph.SpaceTimeGrid;
import Warehouse.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RouteTestWarehouse {
    private final int MAX_TIME = 100;

    @Test
    void testOnWareHouse() {
        Warehouse testWarehouse = new Dexion();
        BaseLayer baseLayer = testWarehouse.getBaseLayer();
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);


        Node startNode = new Node(new Point2D(0, 0));
        //Node endNode = new Node(new Point2D((testWarehouse.getLength() - 1), (testWarehouse.getWidth() - 1)));
        Node endNode = new Node(new Point2D(42,3));
        //Node endNode = new Node(new Point2D(15,5));

        PathFinder testPathFinder = new PathFinder(spaceTimeGrid);

        List<Node> testResultRoute = new ArrayList<>();
        try {
            for (int i = 0; i < 100; i++) {
                testResultRoute = testPathFinder.findShortestRoute(startNode, endNode, 0);
            }
        }
        catch (RouteNotPossibleException e) {
            System.out.println(e.toString());
        }

        TempRoutePrinter printer = new TempRoutePrinter(testResultRoute, baseLayer);
        printer.printRoute(testWarehouse.getLength(), testWarehouse.getWidth());
    }

}
