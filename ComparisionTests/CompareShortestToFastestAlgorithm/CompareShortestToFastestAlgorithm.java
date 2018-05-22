package CompareShortestToFastestAlgorithm;

import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.FastestAndShortestRoute;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.RouteFinders.RouteFinder;
import Warehouse.Dexion;
import Warehouse.Warehouse;
import WarehouseSimulation.GraphicalObjects.Interaction.Handler.RandomProducts;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CompareShortestToFastestAlgorithm {
    private final int NUMBER_OF_EXAMPLES = 0;
    private final int MAX_PRODUCTS_TO_PICK = 5;
    private final int TOTAL_PRODUCT_IDS = 2176;
    private Warehouse warehouse = new Dexion();
    private RandomProducts randomProducts = new RandomProducts();
    private List<Integer> randomIDs = new ArrayList<>();
    private FastestAndShortestRoute bothRoutes;
    String basePath = "ComparisionTests\\outputFiles\\ShortVsFast\\";

    @Test
    void testWith1Picker() {
        final int NUMBER_OF_PICKERS = 1;
        File file = new File(basePath + "testWith1Picker.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith2Pickers() {
        final int NUMBER_OF_PICKERS = 2;
        File file = new File(basePath + "testWith2Picker.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith3Pickers() {
        final int NUMBER_OF_PICKERS = 3;
        File file = new File(basePath + "testWith3Picker.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith4Pickers() {
        final int NUMBER_OF_PICKERS = 4;
        File file = new File(basePath + "testWith4Picker.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith5Pickers() {
        final int NUMBER_OF_PICKERS = 5;
        File file = new File(basePath + "testWith5Picker.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith6Pickers() {
        final int NUMBER_OF_PICKERS = 6;
        File file = new File(basePath + "testWith6Picker.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith7Pickers() {
        final int NUMBER_OF_PICKERS = 7;
        File file = new File(basePath + "testWith7Picker.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith8Pickers() {
        final int NUMBER_OF_PICKERS = 8;
        File file = new File(basePath + "testWith8Picker.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith9Pickers() {
        final int NUMBER_OF_PICKERS = 9;
        File file = new File(basePath + "testWith9Picker.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }

    @Test
    void testWith10Pickers() {
        final int NUMBER_OF_PICKERS = 10;
        File file = new File(basePath + "testWith10Picker.xls");
        randomFastestRoutesToFile(NUMBER_OF_PICKERS, file);
    }


    private void randomFastestRoutesToFile(int pickersPerTest, File file) {
        int productsVisited = 0;
        int routesCalculated[] = new int[5];
        int pathsCalculated = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Fastest\tShortest\tProductsPicked\tNumberOfPicker");
            writer.newLine();
            RouteFinder routeFinder;
            for (int i = 0; i < NUMBER_OF_EXAMPLES; i++) {
                System.out.println("Example number: " + i);
                routeFinder = new RouteFinder(new PathFinder(new SpaceTimeGrid(warehouse.getBaseLayer(), 500)), warehouse.getRouteStartPoint(), warehouse.getRouteEndPoint());
                for (int j = 0; j < pickersPerTest; j++) {
                    randomIDs.clear();
                    for (String ID : randomProducts.nextProductIDList(MAX_PRODUCTS_TO_PICK, TOTAL_PRODUCT_IDS)) {
                        randomIDs.add(Integer.parseInt(ID));
                    }
                    try {
                        bothRoutes = routeFinder.calculateBothRoutes(warehouse.getPickingPoints(randomIDs));
                        routesCalculated[randomIDs.size() - 1]++;
                        productsVisited += randomIDs.size();
                        pathsCalculated += aStarCalculations(randomIDs.size());

                        //Write to file in the following format "FastestRouteLength     ShortestRouteLength     (ID list)"
                        writer.write(bothRoutes.getFastestRoute().getTravelTime() + "\t" + bothRoutes.getShortestRoute().getTravelTime() + "\t" + randomIDs.size() + "\t" + (j + 1));
                        writer.newLine();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                writer.flush();
            }
            writer.write("\n\n\n\n\n");
            writer.write("Products visited\tPaths calculated\t1 product routes\t2 product routes\t3 product routes\t4 product routes\t5 product routes");
            writer.newLine();
            writer.write(productsVisited + "\t" + pathsCalculated + "\t" + routesCalculated[0] + "\t" + routesCalculated[1] + "\t" + routesCalculated[2] + "\t" + routesCalculated[3] + "\t" + routesCalculated[4]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int aStarCalculations(int numberOfProducts) {
        switch (numberOfProducts) {
            case 1:
                return 2;
            case 2:
                return 6;
            case 3:
                return 21;
            case 4:
                return 88;
            default:
                return 445;
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