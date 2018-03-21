package Warehouse.ProductContainer;

import Exceptions.FullRackException;
import Exceptions.IllegalProductPositionException;
import Exceptions.ProductDoesNotExistException;
import Warehouse.Product;
import Geometry.Point2D;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public abstract class Rack extends Rectangle implements ProductContainer {
    private String name;
    private int rackLength;
    private Point2D startPoint;
    List<Product> productList = new ArrayList<>();
    private int widthWarehouse;
    private int heightWarehouse;

    Rack(String name, int rackLength, Point2D startPoint, int widthWarehouse, int heightWarehouse) {
        this.name = name;
        this.rackLength = rackLength;
        this.startPoint = startPoint;
        this.widthWarehouse = widthWarehouse;
        this.heightWarehouse = heightWarehouse;

    }
    abstract Point2D createProductPlacementPoint(int productPosition);

    public void addProduct(Product product) {
        // Check if rack has space for a product
        if(getRackLength() <= productList.size())
            throw new FullRackException();

        int i = 0;
        boolean found = false;

        while(i < getRackLength() && !found) {
            Point2D productPoint = createProductPlacementPoint(i);
            if(!containsProduct(productPoint)) {
                product.setProductPosition(productPoint);
                productList.add(product);
                found = true;
            }
            i++;
        }
    }

    public void addProduct(Product product, int productPosition) {
        checkForIllegalProductPostion(productPosition);

        Point2D placementPoint = createProductPlacementPoint(productPosition);
        // Check if position contains a product
        if(containsProduct(placementPoint))
            throw new IllegalProductPositionException("Place contains a product");

        product.setProductPosition(placementPoint);
        productList.add(product);
    }


    public String getName() {
        return name;
    }

    int getRackLength() {
        return rackLength;
    }

    Point2D getStartPoint() {
        return startPoint;
    }

    public int getWidthWarehouse() {
        return widthWarehouse;
    }

    public int getHeightWarehouse() {
        return heightWarehouse;
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

    void checkForIllegalProductPostion(int productPosition){
        // Check if position is negative
        if(productPosition < 0)
            throw new IllegalProductPositionException("The product position cannot be negative");

        // Check if rack position is in rack
        if(productPosition > getRackLength())
            throw new IllegalProductPositionException("The product position was too high");

    }
}
