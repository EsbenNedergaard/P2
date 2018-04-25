package Warehouse.Racks;

import Exceptions.FullRackException;
import BackEnd.Geometry.Point2D;
import Warehouse.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HorizontalRackRow implements RackRow {
    private Point2D startPoint;
    private int rackRowLength;
    private List<Rack> rackList;

    public HorizontalRackRow(Point2D startPoint, int rackRowLength, int maxAmtInSingleRack) {
        this.startPoint = startPoint;
        this.rackRowLength = rackRowLength;
        this.rackList = new ArrayList<>();

        for(int i = 0; i < rackRowLength; i++) {
            rackList.add(new Rack(maxAmtInSingleRack, new Point2D(startPoint.getX() + i, startPoint.getY())));
        }
    }

    @Override
    public boolean isHorizontal() {
        return true;
    }

    @Override
    public void addProduct(Product e) {
        for (Rack rackElement : rackList) {
            if (!rackElement.checkIfFull()) {
                rackElement.addProduct(e);
                return;
            }
        }
        throw new FullRackException("This rackrow is already full");
    }

    @Override
    public void addProductToRack(Product product, int rackIndex) {
        rackList.get(rackIndex).addProduct(product);
    }

    @Override
    public int doesItContainProduct(Product e) {
        int i = 0;
        for(Rack r : rackList) {
            if (r.doesItContainProduct(e)) {
                return i;
            }
            i++;
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
        for(Rack rackElement : rackList) {
            productList.addAll(rackElement.getProductList());
        }
        return productList;
    }

    @Override
    public List<Rack> getRackList() {
        return this.rackList;
    }

    @Override
    public Rack getRackByIndex(int index) {
        return rackList.get(index);
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
