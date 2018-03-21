package Warehouse.ProductContainer;

import Warehouse.Product;
import Geometry.Point2D;

import java.util.List;

public interface ProductContainer {

    void addProduct(Product product);
    //void checkRackDimensions(int width, int height);
    Product getProduct(int id);
    List<Product> getProductList();
    void setProductList(List<Product> productList);
    boolean inProductContainer(Point2D point);
    boolean containsProduct(Point2D point);
}
