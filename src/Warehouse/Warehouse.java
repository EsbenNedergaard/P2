package Warehouse;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.BaseLayer;
import Warehouse.Racks.RackRow;

import java.util.List;

public interface Warehouse {
    List<RackRow> getRackRowList();

    BaseLayer getBaseLayer();

    List<PickingPoint> getPickingPoints(List<Integer> productIdList);

    int getWidth();

    int getLength();

    Point2D getRouteStartPoint();

    Point2D getRouteEndPoint();
    int getAmountOfProducts();
}
