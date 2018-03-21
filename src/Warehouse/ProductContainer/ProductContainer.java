package Warehouse.ProductContainer;

import Warehouse.Product;
import Geometry.Point2D;

import java.util.List;

public interface ProductContainer {
    List<Product> getProductList();
    void setProductList(List<Product> productList);

    void addProduct(Product product);
    boolean doesItContainProductID(int productId);

    boolean checkIfPointInProductContainer(Point2D point);
}
