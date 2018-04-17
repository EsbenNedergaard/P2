package GraphicalWarehouse;

import Geometry.Node;
import Geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

/* THIS CLASS HAS NO FUNCTION IN THE PROGRAM
 * ITS ONLY JOB IS TO GENERATE SOME CUSTOM RANDOM
 * PICKING ROUTES FOR TESTS */

public class GenerateRandomPickingRoute {

    public List<Node> getRoute1() {

        List<Node> pickingRoute = new ArrayList<>();
        int i;

        for(i = 0; i <= 38; i++) {
            pickingRoute.add(new Node(new Point2D(i, 1)));
        }

        pickingRoute.add(new Node(new Point2D(i, 1)));
        pickingRoute.add(new Node(new Point2D(i, 2)));
        pickingRoute.add(new Node(new Point2D(i, 2)));
        pickingRoute.add(new Node(new Point2D(i, 3)));
        pickingRoute.add(new Node(new Point2D(i, 4)));
        pickingRoute.add(new Node(new Point2D(i, 4)));

        for(i = 38; i > 4; i--) {
            pickingRoute.add(new Node(new Point2D(i, 4)));
        }

        pickingRoute.add(new Node(new Point2D(i, 4)));
        pickingRoute.add(new Node(new Point2D(i, 5)));
        pickingRoute.add(new Node(new Point2D(i, 6)));

        for(i = 4; i <= 38; i++) {
            pickingRoute.add(new Node(new Point2D(i, 7)));
        }

        pickingRoute.add(new Node(new Point2D(i, 7)));
        pickingRoute.add(new Node(new Point2D(i, 8)));
        pickingRoute.add(new Node(new Point2D(i, 8)));
        pickingRoute.add(new Node(new Point2D(i, 9)));
        pickingRoute.add(new Node(new Point2D(i, 10)));
        pickingRoute.add(new Node(new Point2D(i, 10)));

        for(i = 38; i >= 4; i--) {
            pickingRoute.add(new Node(new Point2D(i, 10)));
        }

        return pickingRoute;
    }

    public List<Node> getRoute2() {

        int i;
        List<Node> pickingRoute = new ArrayList<>();

        pickingRoute.add(new Node(new Point2D(1, 1)));
        pickingRoute.add(new Node(new Point2D(1, 2)));
        pickingRoute.add(new Node(new Point2D(1, 3)));
        pickingRoute.add(new Node(new Point2D(1, 4)));
        pickingRoute.add(new Node(new Point2D(1, 5)));
        pickingRoute.add(new Node(new Point2D(1, 6)));
        pickingRoute.add(new Node(new Point2D(1, 7)));

        for(i = 1; i <= 38; i++) {
            pickingRoute.add(new Node(new Point2D(i, 7)));
        }

        pickingRoute.add(new Node(new Point2D(i, 7)));
        pickingRoute.add(new Node(new Point2D(i, 8)));
        pickingRoute.add(new Node(new Point2D(i, 8)));
        pickingRoute.add(new Node(new Point2D(i, 9)));
        pickingRoute.add(new Node(new Point2D(i, 10)));
        pickingRoute.add(new Node(new Point2D(i, 10)));

        for(i = 38; i >= 4; i--) {
            pickingRoute.add(new Node(new Point2D(i, 10)));
        }

        return pickingRoute;
    }

    public List<Node> getRoute3() {

        int i;
        List<Node> pickingRoute = new ArrayList<>();

        pickingRoute.add(new Node(new Point2D(1, 1)));
        pickingRoute.add(new Node(new Point2D(1, 1)));
        pickingRoute.add(new Node(new Point2D(1, 1)));
        pickingRoute.add(new Node(new Point2D(1, 1)));
        pickingRoute.add(new Node(new Point2D(1, 1)));
        pickingRoute.add(new Node(new Point2D(1, 1)));
        pickingRoute.add(new Node(new Point2D(1, 2)));
        pickingRoute.add(new Node(new Point2D(1, 3)));
        pickingRoute.add(new Node(new Point2D(1, 4)));
        pickingRoute.add(new Node(new Point2D(1, 5)));
        pickingRoute.add(new Node(new Point2D(1, 6)));
        pickingRoute.add(new Node(new Point2D(1, 7)));

        for(i = 1; i <= 38; i++) {
            pickingRoute.add(new Node(new Point2D(i, 7)));
        }

        pickingRoute.add(new Node(new Point2D(i, 7)));
        pickingRoute.add(new Node(new Point2D(i, 8)));
        pickingRoute.add(new Node(new Point2D(i, 8)));
        pickingRoute.add(new Node(new Point2D(i, 9)));
        pickingRoute.add(new Node(new Point2D(i, 10)));
        pickingRoute.add(new Node(new Point2D(i, 10)));

        for(i = 38; i >= 4; i--) {
            pickingRoute.add(new Node(new Point2D(i, 10)));
        }

        return pickingRoute;
    }

}
