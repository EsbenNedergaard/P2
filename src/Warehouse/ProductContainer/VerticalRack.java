package Warehouse.ProductContainer;

import Exceptions.FullRackException;
import Exceptions.IllegalProductPositionException;
import Exceptions.IllegalRackDimensionException;
import Warehouse.Product;
import Geometry.Point2D;
import javafx.scene.paint.Color;

import static Warehouse.GUIWarehouse.HEIGHT_WAREHOUSE;
import static Warehouse.GUIWarehouse.TILE_SIZE;
import static Warehouse.GUIWarehouse.WIDTH_WAREHOUSE;

public class VerticalRack extends Rack {

    public VerticalRack(String name, int length, Point2D startPoint) {
        super(name, length, startPoint);

        if(rackOutsideWarehouse())
            throw new IllegalRackDimensionException();

        setWidth(TILE_SIZE);
        setHeight(length * TILE_SIZE);
        relocate(startPoint.getXPixels(), startPoint.getYPixels());
        // Color of rack
        setFill(Color.valueOf("yellow"));
    }

    @Override
    public void addProduct(Product product) {
        // Check if rack has space for a product
        if(this.rackLength <= productList.size())
            throw new FullRackException();

        int rackPositionX = this.startPoint.getX();
        int rackPositionY = this.startPoint.getY();
        int i = 0;
        boolean found = false;

        while(i < this.rackLength && !found) {

            Point2D productPoint = new Point2D(rackPositionX, rackPositionY + i);

            if(!containsProduct(productPoint)) {
                product.setProductPosition(productPoint);
                productList.add(product);
                found = true;
            }
            i++;
        }

    }

    public void addProduct(Product product, int productPosition) {
        // Check if position is negative
        if(productPosition < 0)
            throw new IllegalProductPositionException("The product position cannot be negative");

        // Check if rack position is in rack
        if(productPosition > this.rackLength)
            throw new IllegalProductPositionException("The product position was too high");


        int rackPositionX = this.startPoint.getX();
        int rackPositionY = this.startPoint.getY();

        Point2D placementPoint = new Point2D(rackPositionX, rackPositionY + productPosition);

        // Check if position contains a product
        if(containsProduct(placementPoint))
            throw new IllegalProductPositionException("Place contains a product");

        product.setProductPosition(placementPoint);
        productList.add(product);

    }

    private boolean rackOutsideWarehouse() {
        if(this.startPoint.getX() < 0 || this.startPoint.getX() >= WIDTH_WAREHOUSE)
            return true;

        if(this.startPoint.getY() + this.rackLength > HEIGHT_WAREHOUSE)
            return true;

        return false;

    }

}
