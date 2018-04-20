package GraphicalWarehouse;

import Geometry.*;
import GraphicalWarehouse.GraphicalObjects.OrderPickerGraphics;
import Warehouse.*;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphicalWarehouseTest {
    private List<Point2D> pickPoints1 = new ArrayList<>();
    private FastestRoute routeFinder;

    @BeforeEach
    void beforeEach() {
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
    }

}