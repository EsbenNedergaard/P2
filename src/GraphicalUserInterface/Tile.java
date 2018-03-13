package GraphicalUserInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static GraphicalUserInterface.GuiApp.TILE_SIZE;

public class Tile extends Rectangle {
    public Tile(int x, int y) {
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        relocate(x * TILE_SIZE, y * TILE_SIZE);
        setFill(Color.TRANSPARENT);
        setStroke(Color.valueOf("#b7b7b7"));
        setStrokeWidth(0.5);
    }
}

