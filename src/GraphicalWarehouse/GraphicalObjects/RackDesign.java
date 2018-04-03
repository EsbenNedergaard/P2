package GraphicalWarehouse.GraphicalObjects;

import Warehouse.Racks.Rack;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;


//TODO: BURDE LAVES OM TIL AT VÆRE RACK DESIGN
public class RackDesign extends Rectangle {
    public RackDesign(Rack rack) {
        rack.getProductList().size();

        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        relocate(rack.getRackPosition().getXPixels(), rack.getRackPosition().getYPixels());

        setFill(Color.valueOf("green"));
    }
}
