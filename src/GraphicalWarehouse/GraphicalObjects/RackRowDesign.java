package GraphicalWarehouse.GraphicalObjects;

import Warehouse.Racks.RackRow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class RackRowDesign extends Rectangle {

    public RackRowDesign(RackRow rackRow) {
        if(rackRow.isHorizontal()) {
            setWidth(TILE_SIZE * rackRow.getRackRowLength());
            setHeight(TILE_SIZE);
        } else {
            setWidth(TILE_SIZE);
            setHeight(TILE_SIZE * rackRow.getRackRowLength());
        }

        // Relocates the rackRow to its position
        relocate(rackRow.getStartPoint().getXPixels(), rackRow.getStartPoint().getYPixels());
        // Color fill
        setFill(Color.valueOf("yellow"));
    }
}
