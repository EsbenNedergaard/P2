package Geometry;

import Warehouse.Warehouse;
import Warehouse.Warehouse22b;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GraphTest {
    private final int GRID_HEIGHT = 10;
    private final int GRID_LENGTH = 10;
    private final int MAX_TIME = 30;


    @Test
    void testAlgorithm() {
        ArrayList<Node> inputSet = new ArrayList<>();
        for (int k = 0; k < MAX_TIME; k++) {
            for (int i = 0; i < GRID_HEIGHT; i++) {
                for (int j = 0; j < GRID_LENGTH; j++) {
                    //if (!(i == 8 && j > 4) && !(i == 1 && j < 5) && !(i == 3 && j > 1) && !(i == 1 && j == 5  && k == 6) && !(i == 0 && j == 3  && k == 4)) {
                        inputSet.add(new Node(new Point2D(j, i), k));
                    //}
                }
            }
        }

        Node startNode = new Node(new Point2D(0, 0), 0);
        //Node endNode = new Node(new Point2D(GRID_HEIGHT - 1, GRID_LENGTH - 1));
        Node endNode = new Node(new Point2D(3, 3));

        Graph testGraph = new Graph(inputSet);

        ArrayList<Node> testResultRoute = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            System.out.println("Attempt: " + i);
            testResultRoute = testGraph.findShortestRoute(startNode, endNode);
        }
      
        int i = 0;
        for (Node n : testResultRoute) {
            System.out.println(i + ". (" + n.getX() + ", " + n.getY() + ") time: " + n.getTime());
            i++;
        }
    }

    @Test
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