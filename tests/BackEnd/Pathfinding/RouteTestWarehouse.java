package BackEnd.Pathfinding;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import BackEnd.Pathfinding.PathFinders.FastestPathFinder;
import BackEnd.TempRoutePrinter;
import Exceptions.RouteNotPossibleException;
import BackEnd.Graph.BaseLayer;
import BackEnd.Graph.SpaceTimeGrid;
import Warehouse.*;
import org.junit.jupiter.api.Test;

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

        FastestPathFinder testFastestPathFinder = new FastestPathFinder(spaceTimeGrid);

        PickingRoute testResultRoute = new PickingRoute();
        try {
            for (int i = 0; i < 100; i++) {
                testResultRoute = testFastestPathFinder.findFastestPath(startNode, endNode, 0);
            }
        }
        catch (RouteNotPossibleException e) {
            System.out.println(e.toString());
        }

        TempRoutePrinter printer = new TempRoutePrinter(testResultRoute.getRoute(), baseLayer);
        printer.printRoute(testWarehouse.getLength(), testWarehouse.getWidth());
    }

}
