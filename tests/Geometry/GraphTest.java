package Geometry;

import Warehouse.Warehouse;
import Warehouse.Warehouse22b;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GraphTest {
    private final int GRID_HEIGHT = 10;
    private final int GRID_LENGTH = 10;
    private final int MAX_TIME = 100;

    //THIS PROB DOES NOT WORK ANYMORE
    @Test
    void testAlgorithm() {
        ArrayList<Node> inputSet = new ArrayList<>();
        ArrayList<Node> obstacles = new ArrayList<>();

        for (int x = 0; x < GRID_LENGTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                if ((y == 1 && x < 5)|| (y == 8 && x > 1) || (x == 5 && (1 < y && y < 6)) || (x == 1 && (3 <= y && y < 8))) {
                    obstacles.add(new Node(new Point2D(x, y)));
                }
                else {
                    inputSet.add(new Node(new Point2D(x, y)));
                }
            }
        }

        NodeLayer baseLayer = new NodeLayer(inputSet, 0);

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
        for(Node n : obstacles) {
            graphic[n.getX()][n.getY()] = 'x';
        }

        for(int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_LENGTH; x++) {
                System.out.print(graphic[x][y] + " ");
            }
            System.out.println();
        }


    }

    //THIS PROB DOES NOT WORK ANYMORE
    //@Test
    void testOnWareHouse() {
        //TODO: something is very wrong right here need to add the layer stuff again the route is fucked for some reason
        //TJEK EVT OM LISTEN AF NODER ER ORDENTLIGa
        Warehouse testWarehouse = new Warehouse22b();
        List<Node> warehouseNodeList = testWarehouse.getNodeList();


        //Here we remove all the obstacles
        /*for (Node element : warehouseNodeList) {
            if (element.isObstacle()) {
                warehouseNodeList.remove(element);
            }
        }*/


        ArrayList<Node> inputSet = new ArrayList<>();

        //Then we add the time layers
        for(int i = 0; i < MAX_TIME; i++) {
                for(Node element : warehouseNodeList) {
                    inputSet.add(new Node(element, i));
                }
        }

        Node startNode = new Node(new Point2D(0, 0), 0);
        //Node endNode = new Node(new Point2D((testWarehouse.getLength() - 1), (testWarehouse.getWidth() - 1)));
        Node endNode = new Node(new Point2D(3,3));

        Graph testGraph = new Graph(inputSet);

        ArrayList<Node> testResultRoute = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            System.out.println("Attempt: " + i);
            testResultRoute = testGraph.findShortestRoute(startNode, endNode);
        }

        //Printing the lists of points
        int i = 0;
        for (Node n : testResultRoute) {
            System.out.println(i + ". (" + n.getX() + ", " + n.getY() + ") time: " + n.getTime());
            i++;
        }

    }
}
