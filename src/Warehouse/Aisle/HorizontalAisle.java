package Warehouse.Aisle;

import Geometry.Point2D;
import Warehouse.Product;
import Warehouse.ProductContainer.HorizontalRack;
import Warehouse.ProductContainer.Rack;

import java.util.ArrayList;
import java.util.List;

public class HorizontalAisle implements Aisle {
    private int aisleLength;
    private Point2D startPoint;
    private Point2D endPoint;
    private List<Rack> RackList = new ArrayList<>();


    public HorizontalAisle(int aisleLength, Point2D startPoint) {
        //TODO: Sæt exceptions op for når for lang en gang og et punkt placeret forkert
        this.aisleLength = aisleLength;
        this.startPoint = startPoint;

        this.endPoint = new Point2D(startPoint.getX() + aisleLength - 1, startPoint.getY());

        Point2D firstRackStartPoint = new Point2D(startPoint.getX() + 1, startPoint.getY() - 1);
        RackList.add(new HorizontalRack(aisleLength - 2, firstRackStartPoint));

        Point2D secondRackStartPoint = new Point2D(startPoint.getX() + 1, startPoint.getY() + 1);
        RackList.add(new HorizontalRack(aisleLength - 2, secondRackStartPoint));
    }

    @Override
    public int getAisleLength() {
        return this.aisleLength;
    }

    @Override
    public Point2D getStartPoint() {
        return this.startPoint;
    }

    @Override
    public Point2D getEndPoint() {
        return this.endPoint;
    }

    @Override
    public Rack getFirstRack() {
        return this.RackList.get(0);
    }

    @Override
    public Rack getSecondRack() {
        return this.RackList.get(1);
    }

    @Override
    public List<Rack> getRackList() {
        return this.RackList;
    }

    @Override
    public List<Point2D> getPickingPoints(List<Product> productPickList) {
        return null;
    }

    @Override
    public boolean doesItContainProductID(int id) {
        return false;
    }
}
