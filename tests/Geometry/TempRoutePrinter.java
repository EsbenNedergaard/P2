package Geometry;

import java.util.List;

public class TempRoutePrinter {
    private List<Node> route;
    private BaseLayer baseLayer;

    public TempRoutePrinter(List<Node> route, BaseLayer baseLayer) {
        this.route = route;
        this.baseLayer = baseLayer;
    }


    public void printRoute(int gridLength, int gridHeight) {
        Character[][] graphic = new Character[gridLength][gridHeight];
        for(int x = 0; x < gridLength; x++) {
            for (int y = 0; y < gridHeight; y++) {
                graphic[x][y] = ' ';
            }
        }

        for(Node n : route) {
            graphic[n.getX()][n.getY()] = 'o';
        }
        for(Node n : baseLayer.getStationaryObstacles()) {
            graphic[n.getX()][n.getY()] = 'x';
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
