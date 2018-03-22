package Warehouse.ProductContainer;

import Warehouse.Product;
import Geometry.Point2D;

import java.util.List;

public interface ProductContainerRow {
    List<Product> getProductList();

    void addProduct(Product product);
    boolean doesItContainProduct(Product product);

    boolean checkIfPointInProductContainer(Point2D point);
}
