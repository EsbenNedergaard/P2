package Warehouse.Racks;

import Geometry.Point2D;
import Warehouse.Product;
import Warehouse.Racks.Rack;

import java.util.List;

public class HorizontalRackRow implements RackRow {
    private Point2D startPoint;
    private int rackRowLength;
    private Rack[] rackArray;
    private int maxAmtInSingleRack;

    public HorizontalRackRow(Point2D startPoint, int rackRowLength, int maxAmtInSingleRack) {
        this.startPoint = startPoint;
        this.rackRowLength = rackRowLength;
        this.maxAmtInSingleRack = maxAmtInSingleRack;

        rackArray = new Rack[rackRowLength];

        for(int i = 0; i < rackRowLength; i++) {
            rackArray[i] = new Rack(maxAmtInSingleRack);
        }
    }

    @Override
    public boolean isHorizontal() {
        return true;
    }

    @Override
    public void addProduct(Product e) {

    }

    @Override
    public void addProductToRack(Product product, int rackIndex) {

    }

    @Override
    public boolean doesItContainProduct(Product e) {
        return false;
    }

    @Override
    public int getRackRowLength() {
        return this.rackRowLength;
    }

    @Override
    public Point2D getStartPoint() {
        return this.startPoint;
    }

    @Override
    public List<Product> getAllProductsInRackRow() {
        return null;
    }

    @Override
    public Rack[] getRackArray() {
        return this.rackArray;
    }
}
