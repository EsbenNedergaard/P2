package ManhattanVsTrueDistance;

import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.Heuristics.Manhattan;
import BackEnd.Pathfinding.Heuristics.TrueDistance;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.RouteFinders.RouteFinder;
import Warehouse.Dexion;
import Warehouse.Warehouse;
import WarehouseSimulation.GraphicalObjects.Interaction.Handler.RandomProducts;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class CompareManhattanToTrueDistance {
    private final int NUMBER_OF_EXAMPLES = 0;
    private final int MAX_PRODUCTS_TO_PICK = 5;
    private final int TOTAL_PRODUCT_IDS = 2176;
    private RandomProducts randomProducts = new RandomProducts();
    private List<Integer> randomIDs = new ArrayList<>();
    private RouteFinder manhattan;
    private RouteFinder trueDistance;

    @BeforeEach
    void beforeEach(){
        Warehouse warehouse = new Dexion();
        SpaceTimeGrid grid = new SpaceTimeGrid(warehouse.getBaseLayer(), 300);
        PathFinder manhattanPathFinder = new PathFinder(new SpaceTimeGrid(grid), new Manhattan());
        PathFinder trueDistancePathFinder = new PathFinder(new SpaceTimeGrid(grid), new TrueDistance());

        manhattan = new RouteFinder(manhattanPathFinder, warehouse.getRouteStartPoint(), warehouse.getRouteEndPoint());
        trueDistance = new RouteFinder(trueDistancePathFinder, warehouse.getRouteStartPoint(), warehouse.getRouteEndPoint());
    }
}
