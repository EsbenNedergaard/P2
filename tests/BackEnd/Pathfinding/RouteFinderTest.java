package BackEnd.Pathfinding;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.PathFinders.FastestPathFinder;
import BackEnd.Pathfinding.RouteFinders.RouteFinder;
import BackEnd.TempRoutePrinter;
import Warehouse.Product;
import Warehouse.Racks.Rack;
import Warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RouteFinderTest {
    private RouteFinder testRouteFinder;
    private SpaceTimeGrid grid;
    private PickingPoint pickPoint1;
    private PickingPoint pickPoint2;
    private int GRID_SIZE = 5;

    @BeforeEach
    void beforeEach(){
        int MAX_TIME = 100;
        Warehouse warehouse = new Warehouse.Simple7x7Warehouse();
        this.grid = new SpaceTimeGrid(warehouse.getBaseLayer(), MAX_TIME);

        this.testRouteFinder = new RouteFinder(new FastestPathFinder(grid), warehouse.getRouteStartPoint(), warehouse.getRouteEndPoint());
        this.createPickingPoints();
    }

    private void createPickingPoints(){
        //LAV EN RACK
        Rack rack = new Rack(2, new Point2D(-1, -1));
        Product product1 = new Product(1);
        Product product2 = new Product(2);
        rack.addProduct(product1);
        rack.addProduct(product2);

        pickPoint1 = new PickingPoint(new Point2D(3,0), product1);
        pickPoint2 = new PickingPoint(new Point2D(3,2), product2);
    }



    //TODO: få lavet disse tests til routeFinder
    @Test
    void calculateBestRoute() {
        List<PickingPoint> pickingList = new ArrayList<>();
        pickingList.add(pickPoint1);
        pickingList.add(pickPoint2);


        PickingRoute pickingRoute = testRouteFinder.calculateBestRoute(pickingList);

        TempRoutePrinter printer = new TempRoutePrinter(pickingRoute.getRoute(), grid.getBaseLayer());
        printer.printRoute(GRID_SIZE, GRID_SIZE);
    }
}