package GraphicalWarehouse.GraphicalObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class OrderPickerDesign extends Rectangle {

    public OrderPickerDesign() {
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        relocate(TILE_SIZE, TILE_SIZE);

        setFill(Color.valueOf("#000"));
    }
}
