package Warehouse;

import Geometry.Point2D;
import javafx.scene.Group;

public class TileGrid {
    private int width;
    private int height;
    private Tile[][] grid;


    public TileGrid(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Tile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Tile(new Point2D(x, y));
            }
        }
    }

    public Tile[][] getGrid() {
        return grid;
    }
}
