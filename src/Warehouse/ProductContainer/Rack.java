package Warehouse.ProductContainer;

import Exceptions.FullRackException;
import Warehouse.Product;
import Geometry.Point2D;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public abstract class Rack extends Rectangle implements ProductContainer {
    private int rackLength;
    private Point2D startPoint;
    private List<Product> productList = new ArrayList<>();

    Rack(int rackLength, Point2D startPoint) {
        this.rackLength = rackLength;
        this.startPoint = startPoint;
    }

    abstract Point2D createProductPlacementPoint(int productPosition);
    public abstract boolean isHorizontal();

    public void addProduct(Product product) {
        // Check if rack has space for a product
        if(getRackLength() <= productList.size())
            throw new FullRackException();

        productList.add(product);
    }

    int getRackLength() {
        return rackLength;
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    // Return the whole product list
    @Override
    public List<Product> getProductList() {
        return this.productList;
    }

    @Override
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean doesItContainProductID(int productId) {
        return false;
    }

    @Override
    public boolean checkIfPointInProductContainer(Point2D point) {
        int pointXCoordinate = point.getX();
        int pointYCoordinate = point.getY();
        int rackXCoordinate = this.startPoint.getX();
        int rackYCoordinate = this.startPoint.getY();

        boolean xInRack = rackXCoordinate <= pointXCoordinate && pointXCoordinate < rackXCoordinate + this.getWidth() / TILE_SIZE;
        boolean yInRack = rackYCoordinate <= pointYCoordinate && pointYCoordinate < rackYCoordinate + this.getHeight() / TILE_SIZE;

        if(xInRack && yInRack)
            return true;
        return false;
    }
}
