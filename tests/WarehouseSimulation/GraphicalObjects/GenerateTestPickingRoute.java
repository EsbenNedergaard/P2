package WarehouseSimulation.GraphicalObjects;

import BackEnd.Geometry.*;
import BackEnd.Graph.BaseLayer;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.PickingRoute;
import BackEnd.Pathfinding.RouteFinders.FastestRouteFinder;
import BackEnd.Pathfinding.RouteFinders.RouteFinder;
import Warehouse.*;

import java.util.ArrayList;
import java.util.List;

/* THIS CLASS HAS NO FUNCTION IN THE PROGRAM       *
 * ITS ONLY JOB IS TO GENERATE SOME CUSTOM RANDOM  *
 * PICKING ROUTES FOR TESTS                        */

public class GenerateTestPickingRoute {
    PickingRoute route1;
    PickingRoute route2;
    private Point2D routeStartPoint = new Point2D(0, 5);
    private Point2D routeEndPoint = new Point2D(0, 6);

    public GenerateTestPickingRoute() {
        createRoute1();
    }

    public void createRoute1() {
        List<PickingPoint> pickPoints = new ArrayList<>();
        RouteFinder routeFinder;

        Warehouse testWarehouse = new Dexion();
        BaseLayer baseLayer = testWarehouse.getBaseLayer();
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, 500);
        routeFinder = new FastestRouteFinder(new PathFinder(spaceTimeGrid), routeStartPoint, routeEndPoint);

        pickPoints.add(new PickingPoint(new Point2D(6,1), new Product(1)));
        pickPoints.add(new PickingPoint(new Point2D(6,1), new Product(1)));
        pickPoints.add(new PickingPoint(new Point2D(6,1), new Product(1)));
        pickPoints.add(new PickingPoint(new Point2D(6,1), new Product(1)));
        this.route1 = routeFinder.calculateRoute(pickPoints);
    }

    public void createRoute2() {
        List<PickingPoint> pickPoints = new ArrayList<>();
        RouteFinder routeFinder;

        Warehouse testWarehouse = new Dexion();
        BaseLayer baseLayer = testWarehouse.getBaseLayer();
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, 500);
        routeFinder = new FastestRouteFinder(new PathFinder(spaceTimeGrid), routeStartPoint, routeEndPoint);

        pickPoints.add(new PickingPoint(new Point2D(8,1), new Product(1)));
        pickPoints.add(new PickingPoint(new Point2D(34,1), new Product(1)));
        this.route2 = routeFinder.calculateRoute(pickPoints);
    }

    public List<Node> getRoute1() {
        return this.route1.getRoute();
    }

    public List<Node> getRoute2() {
        return route2.getRoute();
    }
}
