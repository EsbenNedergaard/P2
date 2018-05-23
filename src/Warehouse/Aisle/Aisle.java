package Warehouse.Aisle;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import Warehouse.Product;
import Warehouse.Racks.RackRow;

import java.util.List;

public interface Aisle {
    int getAisleLength();

    Point2D getStartPoint();

    List<RackRow> getRackRowList();

    boolean addProduct(Product e);

    List<PickingPoint> getPickingPoints(List<Integer> productIdList);
}