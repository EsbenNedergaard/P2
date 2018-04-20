package GraphicalWarehouse;

import Geometry.*;
import Warehouse.*;

import java.util.ArrayList;
import java.util.List;

/* THIS CLASS HAS NO FUNCTION IN THE PROGRAM       *
 * ITS ONLY JOB IS TO GENERATE SOME CUSTOM RANDOM  *
 * PICKING ROUTES FOR TESTS                        */

public class GenerateRandomPickingRoute {
    List<Node> route1;

    public GenerateRandomPickingRoute() {
        createRoute1();
    }

    public void createRoute1() {
        List<Point2D> pickPoints1 = new ArrayList<>();
        FastestRoute routeFinder;

        Warehouse testWarehouse = new Dexion();
        BaseLayer baseLayer = testWarehouse.getBaseLayer();
        SpaceTimeGrid spaceTimeGrid = new SpaceTimeGrid(baseLayer, 500);
        routeFinder = new FastestRoute(spaceTimeGrid);

        pickPoints1.add(new Point2D(0,11));
        pickPoints1.add(new Point2D(42,0));
        pickPoints1.add(new Point2D(42,11));
        pickPoints1.add(new Point2D(10,1));
        //pickPoints1.add(new Point2D(7, 7));
        //pickPoints1.add(new Point2D(30, 10));
        pickPoints1.add(new Point2D(15, 4));
        this.route1 = routeFinder.calculateBestRoute(pickPoints1);
    }


    public List<Node> getRoute1() {
        return this.route1;
    }

}
