package Geometry;

import Warehouse.Warehouse;
import Warehouse.Warehouse22b;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GraphTest {
    private final int MAX_TIME = 100;

    //THIS PROB DOES NOT WORK ANYMORE
    @Test
    void testAlgorithm() {
        final int GRID_HEIGHT = 10;
        final int GRID_LENGTH = 10;
        ArrayList<Node> inputSet = new ArrayList<>();

        for (int x = 0; x < GRID_LENGTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                Node temp = new Node(new Point2D(x, y));
                if ((y == 1 && x < 5)|| (y == 8 && x > 1) || (x == 5 && (1 < y && y < 6)) || (x == 1 && (3 <= y && y < 8))) {
                    temp.setNodeType("Obstacle");
                }
                inputSet.add(temp);
            }
        }

        BaseLayer baseLayer = new BaseLayer(inputSet);

        Node startNode = new Node(new Point2D(0, 0), 0);
        Node endNode = new Node(new Point2D(GRID_HEIGHT - 1, GRID_LENGTH - 1));

        Graph testGraph = new Graph(baseLayer, MAX_TIME);

        ArrayList<Node> testResultRoute = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            System.out.println("Attempt: " + i);
            testResultRoute = testGraph.findShortestRoute(startNode, endNode);
        }
      

        Character[][] graphic = new Character[GRID_LENGTH][GRID_HEIGHT];
        for(int x = 0; x < GRID_LENGTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                graphic[x][y] = ' ';
            }
        }

        for(Node n : testResultRoute) {
            graphic[n.getX()][n.getY()] = 'o';
        }
        for(Node n : baseLayer.getStationaryObstacles()) {
            graphic[n.getX()][n.getY()] = 'x';
        }

        for(int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_LENGTH; x++) {
                System.out.print(graphic[x][y] + " ");
            }
            System.out.println();
        }
    }

    @Test
    void testOnWareHouse() {
        //TODO: something is very wrong right here need to add the layer stuff again the route is fucked for some reason
        //TJEK EVT OM LISTEN AF NODER ER ORDENTLIGa
        Warehouse testWarehouse = new Warehouse22b();
        final int GRID_HEIGHT = testWarehouse.getWidth();
        final int GRID_LENGTH = testWarehouse.getLength();

        List<Node> warehouseNodeList = testWarehouse.getNodeList();

        BaseLayer baseLayer = new BaseLayer(warehouseNodeList);

        Node startNode = new Node(new Point2D(0, 0), 0);
        //Node endNode = new Node(new Point2D((testWarehouse.getLength() - 1), (testWarehouse.getWidth() - 1)));
        Node endNode = new Node(new Point2D(15,4));

        Graph testGraph = new Graph(baseLayer, MAX_TIME);

        ArrayList<Node> testResultRoute = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            System.out.println("Attempt: " + i);
            testResultRoute = testGraph.findShortestRoute(startNode, endNode);
        }

        Character[][] graphic = new Character[GRID_LENGTH][GRID_HEIGHT];
        for(int x = 0; x < GRID_LENGTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                graphic[x][y] = ' ';
            }
        }

        for(Node n : testResultRoute) {
            graphic[n.getX()][n.getY()] = 'o';
        }
        for(Node n : baseLayer.getStationaryObstacles()) {
            graphic[n.getX()][n.getY()] = 'x';
        }

        for(int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_LENGTH; x++) {
                System.out.print(graphic[x][y] + " ");
            }
            System.out.println();
        }
    }
}
