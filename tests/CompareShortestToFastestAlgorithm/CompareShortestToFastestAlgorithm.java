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
import java.util.ArrayList;
import java.util.List;

class CompareShortestToFastestAlgorithm {
    private final int NUMBER_OF_EXAMPLES = 5;
    private final int MAX_PRODUCTS_TO_PICK = 5;
    private final int TOTAL_PRODUCT_IDS = 2176;
    private Warehouse warehouse = new Dexion();
    private RandomProducts randomProducts = new RandomProducts();
    private List<Integer> randomIDs = new ArrayList<>();
    private PickingRoute route;
    private int aStarCounter;

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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Fastest\tShortest");
            writer.newLine();
            RouteFinder routeFinder;
            for (int i = 0; i < NUMBER_OF_EXAMPLES; i++) {
                routeFinder = new RouteFinder(new PathFinder(new SpaceTimeGrid(warehouse.getBaseLayer(), 300)), warehouse.getRouteStartPoint(), warehouse.getRouteEndPoint());
                for (int j = 0; j < pickersPerTest; j++) {
                    randomIDs.clear();
                    for (String ID : randomProducts.nextProductIDList(MAX_PRODUCTS_TO_PICK, TOTAL_PRODUCT_IDS)) {
                        randomIDs.add(Integer.parseInt(ID));
                    }
                    try {
                        route = routeFinder.calculateFastestRoute(warehouse.getPickingPoints(randomIDs));
                    } catch (PickerIsTrappedException | RouteNotPossibleException | NodeLayerDoesNotExistException ignore) {
                    }
                    //Write to file in the following format "FastestRouteLength     ShortestRouteLength     (ID list)"
                    writer.write(route.getRouteLength() + "\t" + route.getShortestRoute().getRouteLength());
                    //printIDlist(writer, randomIDs);
                    writer.newLine();
                }
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
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