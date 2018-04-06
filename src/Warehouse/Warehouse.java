package Warehouse;

import Geometry.Node;
import Warehouse.Aisle.Aisle;
import Warehouse.Racks.RackRow;

import java.util.List;

public interface Warehouse {
    void createAisleList();
    List<Aisle> getAisleList();
    List<RackRow> getRackRowList();
    List<Node> getNodeList();

    int getWidth();
    int getLength();
}
