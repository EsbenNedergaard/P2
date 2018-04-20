package Warehouse.Racks;

import Geometry.Point2D;
import Warehouse.Product;

import java.util.List;

public interface RackRow {
    boolean isHorizontal();

    void addProduct(Product e);
    void addProductToRack(Product product, int rackIndex);

    int doesItContainProduct(Product e);

    int getRackRowLength();
    Point2D getStartPoint();
    List<Product> getAllProductsInRackRow();
    List<Rack> getRackList();
    Rack getRackByIndex(int Index);

    int hashCode();
    boolean equals(Object that);
}
