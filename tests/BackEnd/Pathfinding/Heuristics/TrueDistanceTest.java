package BackEnd.Pathfinding.Heuristics;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.SpaceTimeGrid;
import Warehouse.SimpleNxNWarehouse;
import Warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrueDistanceTest {
    private SpaceTimeGrid grid;
    private Heuristic heuristic;
    private int maxTime;

    @BeforeEach
    void beforeEach() {
        Warehouse warehouse = new SimpleNxNWarehouse(5);
        this.maxTime = 100;
        this.grid = new SpaceTimeGrid(warehouse.getBaseLayer(), maxTime);


        heuristic = new TrueDistance();
        heuristic.findDistanceToEndForAllNodes(grid, new Node(new Point2D(3, 2)));
    }

    private void checkForAllLayers(int x, int y, int expectedDistance) {
        for(int i = 0; i < maxTime; i++) {
            assertEquals(expectedDistance, grid.getNodePointer(x, y, i).getDistanceToEnd());
        }
    }

    @Test
    void findDistanceToEndForPoint00() {
        checkForAllLayers(0, 0,5);
    }

    @Test
    void findDistanceToEndForPoint10() {
        checkForAllLayers(1, 0, 6);
    }

    @Test
    void findDistanceToEndForPoint20() {
        checkForAllLayers(2, 0, 5);
    }

    @Test
    void findDistanceToEndForPoint30() {
        checkForAllLayers(3, 0, 4);
    }

    @Test
    void findDistanceToEndForPoint40() {
        checkForAllLayers(4, 0, 3);
    }

    @Test
    void findDistanceToEndForPoint01() {
        checkForAllLayers(0, 1, 4);
    }

    @Test
    void findDistanceToEndForPoint41() {
        checkForAllLayers(4, 1, 2);
    }

    @Test
    void findDistanceToEndForPoint02() {
        checkForAllLayers(0, 2, 3);
    }

    @Test
    void findDistanceToEndForPoint12() {
        checkForAllLayers(1, 2, 2);
    }

    @Test
    void findDistanceToEndForPoint22() {
        checkForAllLayers(2, 2, 1);
    }

    @Test
    void findDistanceToEndForPoint32() {
        checkForAllLayers(3, 2, 0);
    }

    @Test
    void findDistanceToEndForPoint42() {
        checkForAllLayers(4, 2, 1);
    }

    @Test
    void findDistanceToEndForPoint03() {
        checkForAllLayers(0, 3, 4);
    }

    @Test
    void findDistanceToEndForPoint43() {
        checkForAllLayers(4, 3, 2);
    }

    @Test
    void findDistanceToEndForPoint04() {
        checkForAllLayers(0, 4, 5);
    }

    @Test
    void findDistanceToEndForPoint14() {
        checkForAllLayers(1, 4, 6);
    }

    @Test
    void findDistanceToEndForPoint24() {
        checkForAllLayers(2, 4, 5);
    }

    @Test
    void findDistanceToEndForPoint34() {
        checkForAllLayers(3, 4, 4);
    }

    @Test
    void findDistanceToEndForPoint44() {
        checkForAllLayers(4, 4, 3);
    }
}