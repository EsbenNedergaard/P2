package Geometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FastestRouteTest {

    private final int MAX_TIME = 250;
    private final int GRID_SIZE = 10;
    private final int START_TIME = 0;
    private SpaceTimeGrid spaceTimeGrid;
    private List<Node> inputSet;
    private BaseLayer baseLayer;
    private List<Point2D> pickingList;

    @BeforeEach
    void beforeEach() {
        inputSet = new ArrayList<>();
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                Node temp = new Node(new Point2D(x, y));
                inputSet.add(temp);
            }
        }

        //Setting in standard obstacles
        /*for (Node n : inputSet) {
            int x = n.getX();
            int y = n.getY();
            if ((y == 1 && x < 5)|| (y == 8 && x > 1) || (x == 5 && (1 < y && y < 6)) || (x == 1 && (3 <= y && y < 8))) {
                n.setNodeType(NodeType.OBSTACLE);
            }
        }*/
        baseLayer = new BaseLayer(inputSet);
        spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);
        pickingList = new ArrayList<>();
        pickingList.add(new Point2D(0, 9));
        pickingList.add(new Point2D(9, 9));
        pickingList.add(new Point2D(9, 0));
    }

    @Test
    void calculateBestRoute(){
        List<Node> fastestRoute = new ArrayList<>();
        FastestRoute routeFinder = new FastestRoute(spaceTimeGrid);
        fastestRoute = routeFinder.calculateBestRoute(pickingList);

        TempRoutePrinter printer = new TempRoutePrinter(fastestRoute, baseLayer);
        printer.printRoute(GRID_SIZE, GRID_SIZE);

    }
}