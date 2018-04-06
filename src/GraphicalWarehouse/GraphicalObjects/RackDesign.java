package GraphicalWarehouse.GraphicalObjects;

import Warehouse.Racks.Rack;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import static Warehouse.GUIWarehouse.TILE_SIZE;


//TODO: BURDE LAVES OM TIL AT VÆRE RACK DESIGN
public class RackDesign extends Rectangle {
    public RackDesign(Rack rack) {
        rack.getProductList().size();

        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        relocate(rack.getRackPosition().getXPixels(), rack.getRackPosition().getYPixels());

        if (rack.getProductList().size() > 0) {
            setFill(Color.valueOf("green"));
            setStroke(Color.valueOf("yellow"));
            setStrokeWidth(2);
            setStrokeType(StrokeType.INSIDE);
            setOpacity(0.5);
        }
        else {
            setOpacity(0);
        }
    }
}
