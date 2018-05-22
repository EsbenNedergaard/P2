package ManhattanVsTrueDistance;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.Heuristics.Manhattan;
import BackEnd.Pathfinding.Heuristics.TrueDistance;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.PickingRoute;
import BackEnd.Pathfinding.RouteFinders.RouteFinder;
import Warehouse.Dexion;
import Warehouse.Warehouse;
import WarehouseSimulation.GraphicalObjects.Interaction.Handler.RandomProducts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompareManhattanToTrueDistance {
    private String basePath = "ComparisionTests\\outputFiles\\Heurstics\\";
    private final int NUMBER_OF_EXAMPLES = 1500;
    private final int MAX_PRODUCTS_TO_PICK = 5;
    private final int TOTAL_PRODUCT_IDS = 2176;
    private Warehouse warehouse;
    private RandomProducts randomProducts = new RandomProducts();
    private List<Integer> randomIDs = new ArrayList<>();
    private RouteFinder manhattanRouteFinder;
    private RouteFinder trueDistanceRouteFinder;

    @BeforeEach
    void beforeEach(){
        warehouse = new Dexion();
        SpaceTimeGrid grid = new SpaceTimeGrid(warehouse.getBaseLayer(), 300);
        PathFinder manhattanPathFinder = new PathFinder(new SpaceTimeGrid(grid), new Manhattan());
        PathFinder trueDistancePathFinder = new PathFinder(new SpaceTimeGrid(grid), new TrueDistance());

        manhattanRouteFinder = new RouteFinder(manhattanPathFinder, warehouse.getRouteStartPoint(), warehouse.getRouteEndPoint());
        trueDistanceRouteFinder = new RouteFinder(trueDistancePathFinder, warehouse.getRouteStartPoint(), warehouse.getRouteEndPoint());
    }

    @Test
    void testWith10Pickers(){
        final int NUMBER_OF_PICKERS = 10;
        File file = new File(basePath + "testWith10Pickers.xls");
        this.runtimesForRandomRoutesToFile(file, NUMBER_OF_PICKERS);
    }

    void runtimesForRandomRoutesToFile(File file, int numberOfPickers){
        int productsVisited = 0;
        int routesCalculated[] = new int[5];
        long startTime;
        long stopTime;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Manhattan\tTrueDistance\tProductsPicked\tNumberOfPicker");
            writer.newLine();
            for (int i = 0; i < NUMBER_OF_EXAMPLES; i++) {
                System.out.println("Example number: " + i);
                trueDistanceRouteFinder.reset();
                manhattanRouteFinder.reset();
                for (int j = 0; j < numberOfPickers; j++) {
                    randomIDs.clear();
                    for (String ID : randomProducts.nextProductIDList(MAX_PRODUCTS_TO_PICK, TOTAL_PRODUCT_IDS)) {
                        randomIDs.add(Integer.parseInt(ID));
                    }
                    try {
                        List<PickingPoint> pickingPoints = warehouse.getPickingPoints(randomIDs);

                        //Calculating elapsed time for manhattan.
                        startTime = System.currentTimeMillis();
                        manhattanRouteFinder.calculateFastestRoute(pickingPoints);
                        stopTime = System.currentTimeMillis();
                        long elapsedTimeManhattan = stopTime - startTime;

                        //Same for true distance
                        startTime = System.currentTimeMillis();
                        trueDistanceRouteFinder.calculateFastestRoute(pickingPoints);
                        stopTime = System.currentTimeMillis();
                        long elapsedTimeTrueDistance = stopTime - startTime;

                        routesCalculated[randomIDs.size() - 1]++;
                        productsVisited += randomIDs.size();

                        //Write to file in the following format "Mahattan     TrueDistance     (ID list)       Picker ID"
                        writer.write(elapsedTimeManhattan + "\t" + elapsedTimeTrueDistance + "\t" + randomIDs.size() + "\t" + (j + 1));
                        writer.newLine();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                writer.flush();
            }
            writer.write("\n\n\n\n\n");
            writer.write("Products visited\t1 product routes\t2 product routes\t3 product routes\t4 product routes\t5 product routes");
            writer.newLine();
            writer.write(productsVisited + "\t" + routesCalculated[0] + "\t" + routesCalculated[1] + "\t" + routesCalculated[2] + "\t" + routesCalculated[3] + "\t" + routesCalculated[4]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
