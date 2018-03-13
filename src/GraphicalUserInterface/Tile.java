package GraphicalUserInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile(boolean color, int x, int y) {
        setWidth(GuiApp.TILE_SIZE);
        setHeight(GuiApp.TILE_SIZE);

        relocate(x * GuiApp.TILE_SIZE, y * GuiApp.TILE_SIZE);
        setFill(color ? Color.valueOf("grey") : Color.valueOf("white"));
    }
}

