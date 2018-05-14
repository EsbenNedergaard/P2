package Warehouse.Racks;

import Warehouse.Exceptions.FullRackException;
import BackEnd.Geometry.Point2D;
import Warehouse.Exceptions.FullRackRowException;
import Warehouse.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HorizontalRackRow implements RackRow {
    private Point2D startPoint;
    private int rackRowLength;
    private List<Rack> rackList;

    public HorizontalRackRow(Point2D startPoint, int rackRowLength, int numberOfShelvesInSingleRack) {
        this.startPoint = startPoint;
        this.rackRowLength = rackRowLength;
        this.rackList = new ArrayList<>();

        for(int i = 0; i < rackRowLength; i++) {
            rackList.add(new Rack(numberOfShelvesInSingleRack, new Point2D(startPoint.getX() + i, startPoint.getY())));
        }
    }

    @Override
    public boolean isHorizontal() {
        return true;
    }

    @Override
    public boolean addProduct(Product e) {
        for (Rack rackElement : rackList) {
            if (!rackElement.checkIfFull()) {
                rackElement.addProduct(e);
                return true;
            }
        }
        throw new FullRackRowException("This rackrow is already full");
    }

    @Override
    public boolean addProductToRack(Product product, int rackIndex) throws FullRackException{
        rackList.get(rackIndex).addProduct(product);

        //We only get here if the rack is not full otherwise it will throw an fullRackException
        return true;
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
