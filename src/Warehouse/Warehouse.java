package Warehouse;

import Warehouse.Aisle.Aisle;
import Warehouse.ProductContainer.RackRow;

import java.util.List;



public interface Warehouse {
    void createAisleList();
    List<Aisle> getAisleList();

    List<RackRow> getRackList();

    int getHeight();
    int getWidth();
}
