package Warehouse;

import BackEnd.Graph.BaseLayer;
import BackEnd.Geometry.Point2D;
import Warehouse.Aisle.Aisle;
import Warehouse.Racks.RackRow;

import java.util.List;

public interface Warehouse {
    void createAisleList();
    List<Aisle> getAisleList();
    List<RackRow> getRackRowList();
    BaseLayer getBaseLayer();
    List<PickingPoint> getPickingPoints(List<Product> productPickList);
    List<PickingPoint> getPickingPointsFromIDs(List<Integer> productIDList);
    int getWidth();
    int getLength();
}
