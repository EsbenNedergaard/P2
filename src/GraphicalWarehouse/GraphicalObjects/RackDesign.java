package GraphicalWarehouse.GraphicalObjects;

import Warehouse.ProductContainer.RackRow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class RackDesign extends Rectangle {

    public RackDesign(RackRow rackRow) {
        if(rackRow.isHorizontal()) {
            setWidth(TILE_SIZE * rackRow.getRackLength());
            setHeight(TILE_SIZE);
        } else {
            setWidth(TILE_SIZE);
            setHeight(TILE_SIZE * rackRow.getRackLength());
        }

        // Relocates the rackRow to its position
        relocate(rackRow.getStartPoint().getXPixels(), rackRow.getStartPoint().getYPixels());
        // Color fill
        setFill(Color.valueOf("yellow"));
    }
}
