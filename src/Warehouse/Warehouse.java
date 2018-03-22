package Warehouse;

import Warehouse.Aisle.Aisle;
import Warehouse.ProductContainer.RackRow;

import java.util.List;

public interface Warehouse {
    void createAisleList();
    List<Aisle> getAisleList();

    List<RackRow> getRackRowList();

    int getHeight();
    int getWidth();
}
