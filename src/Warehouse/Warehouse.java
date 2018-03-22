package Warehouse;

import Warehouse.Aisle.Aisle;
import Warehouse.ProductContainer.Rack;
import java.util.List;



public interface Warehouse {
    void createAisleList();
    List<Aisle> getAisleList();

    List<Rack> getRackList();

    int getHeight();
    int getWidth();
}
