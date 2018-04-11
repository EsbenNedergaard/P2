package Warehouse.PickList;

import Warehouse.Product;
import java.util.List;

public interface PickList {
    List<Product> getPickList();
    int getAmountOfProducts();
}
