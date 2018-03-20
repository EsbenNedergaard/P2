package Warehouse.ProductContainer;

import Exceptions.FullRackException;
import Exceptions.IllegalProductPositionException;
import Exceptions.IllegalRackDimensionException;
import Warehouse.Product;
import Geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.List;

import static Warehouse.GUIWarehouse.HEIGHT_WAREHOUSE;
import static Warehouse.GUIWarehouse.TILE_SIZE;
import static Warehouse.GUIWarehouse.WIDTH_WAREHOUSE;

public class VerticalRack extends Rack {

    public VerticalRack(String name, int length, Point2D startPoint) {
        super(name, length, startPoint);

        if(rackOutsideWarehouse())
            throw new IllegalRackDimensionException();

        setUpGraphics();
    }

    private void setUpGraphics(){
        setWidth(TILE_SIZE);
        setHeight(this.getRackLength() * TILE_SIZE);

        Point2D startPoint = this.getStartPoint();
        relocate(startPoint.getXPixels(), startPoint.getYPixels());
        // Color of rack
        setFill(Color.valueOf("yellow"));
    }

    @Override
    public void addProduct(Product product) {
        // Check if rack has space for a product

        if(getRackLength() <= productList.size())
            throw new FullRackException();

        int rackPositionX = this.getStartPoint().getX();
        int rackPositionY = this.getStartPoint().getY();
        int i = 0;
        boolean found = false;

        while(i < getRackLength() && !found) {
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
        if(productPosition > getRackLength())
            throw new IllegalProductPositionException("The product position was too high");


        int rackPositionX = this.getStartPoint().getX();
        int rackPositionY = this.getStartPoint().getY();

        Point2D placementPoint = new Point2D(rackPositionX, rackPositionY + productPosition);

        // Check if position contains a product
        if(containsProduct(placementPoint))
            throw new IllegalProductPositionException("Place contains a product");

        product.setProductPosition(placementPoint);
        productList.add(product);
    }

    private boolean rackOutsideWarehouse() {
        int rackPositionX = this.getStartPoint().getX();
        int rackPositionY = this.getStartPoint().getY();


        if(WIDTH_WAREHOUSE <= rackPositionX || rackPositionX < 0)
            return true;

        if(HEIGHT_WAREHOUSE <= rackPositionY + getRackLength() || rackPositionY + getRackLength() < 0)
            return true;

        return false;

    }
}
