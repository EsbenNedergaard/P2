package Warehouse;

import Geometry.BaseLayer;
import Geometry.Node;
import Geometry.Point2D;
import Warehouse.Aisle.Aisle;
import Warehouse.Racks.RackRow;

import java.util.List;

public interface Warehouse {
    void createAisleList();
    List<Aisle> getAisleList();
    List<RackRow> getRackRowList();
    BaseLayer getBaseLayer();
    List<Point2D> getPickingPoints(List<Product> productPickList);

    int getWidth();
    int getLength();
}
