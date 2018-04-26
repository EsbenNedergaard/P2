package WarehouseSimulation.GraphicalObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

import static Warehouse.GUIWarehouse.TILE_SIZE;


//TODO: BURDE LAVES OM TIL AT VÆRE RACK DESIGN
public class RackGraphic extends Circle {
    public RackGraphic(Warehouse.Racks.Rack rack) {
        rack.getProductList().size();

        setRadius(TILE_SIZE / 2.5);
        relocate(rack.getRackPosition().getXPixels() + 2, rack.getRackPosition().getYPixels() + 2);

        if (rack.getProductList().size() > 0) {
            setFill(Color.valueOf("#fc481e"));
            setStrokeType(StrokeType.INSIDE);
        }
        else {
            setOpacity(0);
        }
    }
}
