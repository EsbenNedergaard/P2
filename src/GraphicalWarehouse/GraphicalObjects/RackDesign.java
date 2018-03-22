package GraphicalWarehouse.GraphicalObjects;

import Warehouse.ProductContainer.Rack;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class RackDesign extends Rectangle {

    public RackDesign(Rack rack) {
        if(rack.isHorizontal()) {
            setWidth(TILE_SIZE * rack.getRackLength());
            setHeight(TILE_SIZE);
        } else {
            setWidth(TILE_SIZE);
            setHeight(TILE_SIZE * rack.getRackLength());
        }

        // Relocates the rack to its position
        relocate(rack.getStartPoint().getXPixels(), rack.getStartPoint().getYPixels());
        // Color fill
        setFill(Color.valueOf("yellow"));
    }
}
