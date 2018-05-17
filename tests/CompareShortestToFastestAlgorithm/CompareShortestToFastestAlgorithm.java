package CompareShortestToFastestAlgorithm;

import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.PickingRoute;
import BackEnd.Pathfinding.RouteFinders.RouteFinder;
import Exceptions.NodeLayerDoesNotExistException;
import Exceptions.PickerIsTrappedException;
import Exceptions.RouteNotPossibleException;
import Warehouse.Dexion;
import Warehouse.Warehouse;
import WarehouseSimulation.GraphicalObjects.Interaction.Handler.RandomProducts;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

class CompareShortestToFastestAlgorithm {
    private final int NUMBER_OF_EXAMPLES = 1000;
    private final int MAX_PRODUCTS_TO_PICK = 5;
    private final int TOTAL_PRODUCT_IDS = 2176;
    private Warehouse warehouse = new Dexion();
    private RandomProducts randomProducts = new RandomProducts();
    private List<Integer> randomIDs = new ArrayList<>();
    private PickingRoute route;

    @Test
    void testWith1Picker() {
        final int NUMBER_OF_PICKERS = 1;
        File file = new File("src\\outputFiles\\testExamples\\testWith1Picker.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith2Pickers() {
        final int NUMBER_OF_PICKERS = 2;
        File file = new File("src\\outputFiles\\testExamples\\testWith2Pickers.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith3Pickers() {
        final int NUMBER_OF_PICKERS = 3;
        File file = new File("src\\outputFiles\\testExamples\\testWith3Pickers.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith4Pickers() {
        final int NUMBER_OF_PICKERS = 4;
        File file = new File("src\\outputFiles\\testExamples\\testWith4Pickers.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith5Pickers() {
        final int NUMBER_OF_PICKERS = 5;
        File file = new File("src\\outputFiles\\testExamples\\testWith5Pickers.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith6Pickers() {
        final int NUMBER_OF_PICKERS = 6;
        File file = new File("src\\outputFiles\\testExamples\\testWith6Pickers.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith7Pickers() {
        final int NUMBER_OF_PICKERS = 7;
        File file = new File("src\\outputFiles\\testExamples\\testWith7Pickers.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith8Pickers() {
        final int NUMBER_OF_PICKERS = 8;
        File file = new File("src\\outputFiles\\testExamples\\testWith8Pickers.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith9Pickers() {
        final int NUMBER_OF_PICKERS = 9;
        File file = new File("src\\outputFiles\\testExamples\\testWith9Pickers.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith10Pickers() {
        final int NUMBER_OF_PICKERS = 10;
        File file = new File("src\\outputFiles\\testExamples\\testWith10Pickers.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }


    private void randomFastestRoutesToFile(int pickersPerTest, File file) {
        int productsVisited = 0;
        int routesCalculated[] = new int[5];
        int pathsCalculated = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Fastest\tShortest\tProductsPicked");
            writer.newLine();
            RouteFinder routeFinder;
            for (int i = 0; i < NUMBER_OF_EXAMPLES; i++) {
                System.out.println("Example number: " + i);
                routeFinder = new RouteFinder(new PathFinder(new SpaceTimeGrid(warehouse.getBaseLayer(), 300)), warehouse.getRouteStartPoint(), warehouse.getRouteEndPoint());
                for (int j = 0; j < pickersPerTest; j++) {
                    randomIDs.clear();
                    for (String ID : randomProducts.nextProductIDList(MAX_PRODUCTS_TO_PICK, TOTAL_PRODUCT_IDS)) {
                        randomIDs.add(Integer.parseInt(ID));
                    }
                    try {
                        route = routeFinder.calculateFastestRoute(warehouse.getPickingPoints(randomIDs));
                        routesCalculated[randomIDs.size() - 1]++;
                        productsVisited += randomIDs.size();
                        pathsCalculated += aStarCalculations(randomIDs.size());

                        //Write to file in the following format "FastestRouteLength     ShortestRouteLength     (ID list)"
                        writer.write(route.getRouteLength() + "\t" + route.getShortestRoute().getRouteLength() + "\t" + randomIDs.size());
                        writer.newLine();
                    } catch (PickerIsTrappedException | RouteNotPossibleException | NodeLayerDoesNotExistException ignore) { }
                }
                writer.flush();
            }
            writer.newLine();
            writer.write("Products visited\tPaths calculated\t1 product routes\t2 product routes\t3 product routes\t4 product routes\t5 product routes");
            writer.newLine();
            writer.write(productsVisited + "\t" + pathsCalculated + "\t" + routesCalculated[0] + "\t" + routesCalculated[1] + "\t" + routesCalculated[2] + "\t" + routesCalculated[3] + "\t" + routesCalculated[4]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int aStarCalculations(int numberOfProducts) {
        switch (numberOfProducts) {
            case 1: return 2;
            case 2: return 6;
            case 3: return 21;
            case 4: return 88;
            default: return 445;
        }
    }

    private void printIDlist(BufferedWriter writer, List<Integer> randomIDs) {
        try {
            writer.write("\t(");
            randomIDs.forEach(ID -> {
                try {
                    writer.write("" + ID);
                    if (ID != randomIDs.get(randomIDs.size() - 1)) {
                        writer.write(", ");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.write(")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}