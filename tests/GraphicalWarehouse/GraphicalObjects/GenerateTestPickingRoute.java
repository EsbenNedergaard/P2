package GraphicalWarehouse.GraphicalObjects;

import BackEnd.Geometry.*;
import BackEnd.Graph.BaseLayer;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.OptimalRouteFinder;
import Warehouse.*;

import java.util.ArrayList;
import java.util.List;

/* THIS CLASS HAS NO FUNCTION IN THE PROGRAM       *
 * ITS ONLY JOB IS TO GENERATE SOME CUSTOM RANDOM  *
 * PICKING ROUTES FOR TESTS                        */

public class GenerateTestPickingRoute {
    List<Node> route1;
    List<Node> route2;

    public GenerateTestPickingRoute() {
        createRoute1();
    }

    public void createRoute1() {
        List<PickingPoint> pickPoints = new ArrayList<>();
        OptimalRouteFinder routeFinder;

        Warehouse testWarehouse = new Dexion();
        BaseLayer baseLayer = testWarehouse.getBaseLayer();
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, 500);
        routeFinder = new OptimalRouteFinder(spaceTimeGrid);

        pickPoints.add(new PickingPoint(new Point2D(6,1), new Product(1)));
        pickPoints.add(new PickingPoint(new Point2D(6,1), new Product(1)));
        pickPoints.add(new PickingPoint(new Point2D(6,1), new Product(1)));
        pickPoints.add(new PickingPoint(new Point2D(6,1), new Product(1)));
        this.route1 = routeFinder.calculateBestRoute(pickPoints);
    }

    public void createRoute2() {
        List<PickingPoint> pickPoints = new ArrayList<>();
        OptimalRouteFinder routeFinder;

        Warehouse testWarehouse = new Dexion();
        BaseLayer baseLayer = testWarehouse.getBaseLayer();
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, 500);
        routeFinder = new OptimalRouteFinder(spaceTimeGrid);

        pickPoints.add(new PickingPoint(new Point2D(8,1), new Product(1)));
        pickPoints.add(new PickingPoint(new Point2D(34,1), new Product(1)));
        this.route2 = routeFinder.calculateBestRoute(pickPoints);
    }

    public List<Node> getRoute1() {
        return this.route1;
    }

    public List<Node> getRoute2() {
        return route2;
    }
}
