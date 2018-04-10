package Warehouse;

import Geometry.Node;
import Geometry.Point2D;
import Warehouse.Aisle.Aisle;
import Warehouse.Racks.RackRow;

import java.util.List;

public interface Warehouse {
    void createAisleList();
    List<Aisle> getAisleList();
    List<RackRow> getRackRowList();
    List<Node> getNodeList();
    List<Point2D> getPickingPoints(List<Product> productPickList);

    int getWidth();
    int getLength();
}
