package Warehouse;

import Geometry.Point2D;
import Warehouse.ProductContainer.HorizontalRack;
import Warehouse.ProductContainer.Rack;
import Warehouse.ProductContainer.VerticalRack;

import java.util.ArrayList;
import java.util.List;



public interface Warehouse {
    void createTileGrid();
    void createRackList();
    TileGrid getTileGrid();
    List<Rack> getRackList();

    int getHeight();
    int getWidth();
}
