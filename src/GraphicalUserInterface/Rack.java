package GraphicalUserInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rack extends Rectangle {

    public Rack(int width, int height, int x, int y) {
        setWidth(width);
        setHeight(height);

        relocate(x * GuiApp.TILE_SIZE, y * GuiApp.TILE_SIZE);
        setFill(Color.valueOf("yellow"));
    }
}
