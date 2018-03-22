package Warehouse;

import Warehouse.Aisle.Aisle;
import Warehouse.Racks.RackRow;

import java.util.List;

public interface Warehouse {
    void createAisleList();
    List<Aisle> getAisleList();

    List<RackRow> getRackRowList();

    int getHeight();
    int getWidth();
}
