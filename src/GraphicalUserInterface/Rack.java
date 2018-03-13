package GraphicalUserInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static GraphicalUserInterface.GuiApp.TILE_SIZE;

public class Rack extends Rectangle {

    private int xCoordinate, yCoordinate;

    public Rack(int width, int height, int x, int y) {
        setWidth(width);
        setHeight(height);

        this.xCoordinate = x;
        this.yCoordinate = y;

        relocate(x * TILE_SIZE, y * TILE_SIZE);
        setFill(Color.valueOf("yellow"));
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }
}
