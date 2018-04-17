package Warehouse.Racks;

import Exceptions.FullRackException;
import Geometry.Point2D;
import Warehouse.Product;
import Warehouse.Racks.Rack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HorizontalRackRow implements RackRow {
    private Point2D startPoint;
    private int rackRowLength;
    private Rack[] rackArray;

    public HorizontalRackRow(Point2D startPoint, int rackRowLength, int maxAmtInSingleRack) {
        this.startPoint = startPoint;
        this.rackRowLength = rackRowLength;

        rackArray = new Rack[rackRowLength];

        for(int i = 0; i < rackRowLength; i++) {
            rackArray[i] = new Rack(maxAmtInSingleRack, new Point2D(startPoint.getX()+i, startPoint.getY()));
        }
    }

    @Override
    public boolean isHorizontal() {
        return true;
    }

    @Override
    public void addProduct(Product e) {
        for (Rack rackElement : rackArray) {
            if (!rackElement.checkIfFull()) {
                rackElement.addProduct(e);
                return;
            }
        }
        throw new FullRackException("This rackrow is already full");
    }

    @Override
    public void addProductToRack(Product product, int rackIndex) {
        rackArray[rackIndex].addProduct(product);
    }

    @Override
    public int doesItContainProduct(Product e) {
        for (int i = 0; i < rackArray.length; i++) {
            if (rackArray[i].doesItContainProduct(e)) {
                return i;
            }
        }

        return -1;
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
        List<Product> productList = new ArrayList<>();
        for(Rack rackElement : rackArray) {
            productList.addAll(rackElement.getProductList());
        }
        return productList;
    }

    @Override
    public Rack[] getRackArray() {
        return this.rackArray;
    }

    @Override
    public Rack getRackByIndex(int Index) {
        return rackArray[Index];
    }


    @Override
    public int hashCode() {
        return Objects.hash(startPoint) + Objects.hash(rackRowLength);
    }

    @Override
    public boolean equals(Object that) {
        if(this == that) return true;
        if(that == null || this.getClass() != that.getClass()) return false;

        RackRow thatRackRow = (RackRow) that;
        return this.getStartPoint().equals(thatRackRow.getStartPoint()) && this.getRackRowLength() == thatRackRow.getRackRowLength();
    }
}
