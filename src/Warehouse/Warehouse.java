package Warehouse;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Graph.BaseLayer;
import Warehouse.Aisle.Aisle;
import Warehouse.Racks.RackRow;

import java.util.List;

public interface Warehouse {
    void createAisleList();
    List<Aisle> getAisleList();
    List<RackRow> getRackRowList();
    BaseLayer getBaseLayer();
    List<PickingPoint> getPickingPoints(List<Integer> productIdList);
    int getWidth();
    int getLength();
}
