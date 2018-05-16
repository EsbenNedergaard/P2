package BackEnd;

import BackEnd.Geometry.Node.Node;
import BackEnd.Graph.BaseLayer;

import java.util.List;

public class TempRoutePrinter {
    private List<Node> route;
    private BaseLayer baseLayer;

    public TempRoutePrinter(List<Node> route, BaseLayer baseLayer) {
        this.route = route;
        this.baseLayer = baseLayer;
    }


    public void printRoute(int gridLength, int gridHeight) {
        String[][] graphic = new String[gridLength][gridHeight];
        for(int x = 0; x < gridLength; x++) {
            for (int y = 0; y < gridHeight; y++) {
                graphic[x][y] = "o";
            }
        }

        for(int i = 0; i < route.size(); i++) {
            Node n = route.get(i);
            if(!(graphic[n.getX()][n.getY()].equals("o"))) {
                int temp = Integer.parseInt(graphic[n.getX()][n.getY()]);
                temp++;
                graphic[n.getX()][n.getY()] = "" + temp;
            }
            else {
                graphic[n.getX()][n.getY()] = "1";
            }
        }
        for(Node n : baseLayer.getStationaryObstacles()) {
            graphic[n.getX()][n.getY()] = "X";
        }

        for(int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridLength; x++) {
                System.out.print(graphic[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
