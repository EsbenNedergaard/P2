package GraphicalUserInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile(boolean color, int x, int y) {
        setWidth(GuiApp.TILE_SIZE);
        setHeight(GuiApp.TILE_SIZE);

        relocate(x * GuiApp.TILE_SIZE, y * GuiApp.TILE_SIZE);
        setFill(Color.TRANSPARENT);
        setStroke(Color.valueOf("#b7b7b7"));
        setStrokeWidth(0.5);
    }
}

