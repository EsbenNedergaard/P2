package Warehouse.ProductContainer;

import Exceptions.FullRackException;
import Warehouse.Product;
import Geometry.Point2D;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public abstract class RackRow extends Rectangle implements ProductContainerRow {
    private int rackRowLength;
    private Point2D startPoint;
    private Rack[] rackArray;
    private int maxAmtInSingleRack;

    RackRow(int rackRowLength, Point2D startPoint, int maxAmtInRack) {
        this.rackRowLength = rackRowLength;
        this.startPoint = startPoint;
        this.maxAmtInSingleRack = maxAmtInRack;

        this.rackArray = new Rack[rackRowLength];

        for(Rack rackElement : rackArray) {
            rackElement = new Rack(maxAmtInSingleRack);
        }
    }

    abstract Point2D getRackPlacementPoint(int RackIndex);
    public abstract boolean isHorizontal();

    public void addProduct(Product product) {
        for (Rack rackElement : rackArray) {
            if (!rackElement.checkIfFull()) {
                rackElement.addProduct(product);
                return;
            }
        }
        throw new FullRackException("This rackrow is aldready full");
    }

    public void addProductToRack(Product product, int rackIndex) {
        rackArray[rackIndex].addProduct(product);
    }

    public int getRackRowLength() {
        return rackRowLength;
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    // Return the whole product list
    @Override
    public List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        for(Rack rackElement : rackArray) {
            productList.addAll(rackElement.getProductList());
        }
        return productList;
    }

    //TODO: FÅ LAVET MIG
    @Override
    public boolean doesItContainProduct(Product product) {
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
