package Warehouse.ProductContainer;

import Exceptions.ProductDoesNotExistException;
import Warehouse.Product;
import Geometry.Point2D;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

import static Warehouse.GUIWarehouse.HEIGHT_WAREHOUSE;
import static Warehouse.GUIWarehouse.TILE_SIZE;

public abstract class Rack extends Rectangle implements ProductContainer {
    private String name;
    private int rackLength;
    private Point2D startPoint;
    List<Product> productList = new ArrayList<>();

    Rack(String name, int rackLength, Point2D startPoint) {
        this.name = name;
        this.rackLength = rackLength;
        this.startPoint = startPoint;
    }

    abstract public void addProduct(Product product);
    abstract public void addProduct(Product product, int productPosition);


    public String getName() {
        return name;
    }

    int getRackLength() {
        return rackLength;
    }

    Point2D getStartPoint() {
        return startPoint;
    }

    @Override
    public Product getProduct(int id) {
        for(Product item : productList) {
            if(item.getById() == id)
                return item;
        }
        // Product does not exist
        throw new ProductDoesNotExistException();
    }

    // Return the whole product list
    @Override
    public List<Product> getProductList() {
        return this.productList;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setRackLength(int rackLength) {
        this.rackLength = rackLength;
    }

    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean containsProduct(Point2D point) {
        for(Product item : productList) {
            if(item.getProductPosition().equals(point))
                return true;
        }
        return false;
    }

    @Override
    public boolean inProductContainer(Point2D point) {
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
