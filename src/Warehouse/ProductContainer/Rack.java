package Warehouse.ProductContainer;

import Exceptions.ProductDoesNotExistException;
import Warehouse.Product;
import Geometry.Point2D;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public abstract class Rack extends Rectangle implements ProductContainer {

    protected String name;
    protected int rackLength;
    protected Point2D startPoint;
    protected List<Product> productList = new ArrayList<>();

    public Rack(String name, int rackLength, Point2D startPoint) {
        this.name = name;
        this.rackLength = rackLength;
        this.startPoint = startPoint;
    }

    abstract public void addProduct(Product product);
    abstract public void addProduct(Product product, int productPosition);

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
