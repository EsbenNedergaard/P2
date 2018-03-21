package Warehouse.Aisle;

import Geometry.Point2D;
import Warehouse.Product;
import Warehouse.ProductContainer.HorizontalRack;
import Warehouse.ProductContainer.Rack;

import java.util.ArrayList;
import java.util.List;

public class HorizontalAisle implements Aisle {
    private int aisleLenght;
    private Point2D startPoint;
    private Point2D endPoint;
    private List<Rack> RackList = new ArrayList<>();


    public HorizontalAisle(int aisleLenght, Point2D startPoint) {
        //TODO: Sæt exceptions op for når for lang en gang og et punkt placeret forkert
        this.aisleLenght = aisleLenght;
        this.startPoint = startPoint;

        this.endPoint = new Point2D(startPoint.getX() + aisleLenght, startPoint.getY());

        Point2D firstRackStartPoint = new Point2D(startPoint.getX()+1, startPoint.getY()-1);
        RackList.add(new HorizontalRack(aisleLenght-2, firstRackStartPoint));

        Point2D secondRackStartPoint = new Point2D(startPoint.getX()+1, startPoint.getY()+1);
        RackList.add(new HorizontalRack(aisleLenght-2, secondRackStartPoint));
    }

    @Override
    public int getAisleLenght() {
        return this.aisleLenght;
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
    public List<Point2D> getPickingPoints(List<Product> productList) {
        return null;
    }

    @Override
    public boolean doesItContainProductID(int id) {
        return false;
    }
}
