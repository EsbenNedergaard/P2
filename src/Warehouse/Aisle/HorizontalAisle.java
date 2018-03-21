package Warehouse.Aisle;

import Geometry.Point2D;
import Warehouse.Product;
import Warehouse.ProductContainer.HorizontalRack;
import Warehouse.ProductContainer.Rack;

import java.util.List;

public class HorizontalAisle implements Aisle {
    int aisleLenght;
    Point2D startPoint;
    Point2D endPoint;
    Rack firstRack;
    Rack secondRack;

    public HorizontalAisle(int aisleLenght, Point2D startPoint) {
        //TODO: Sæt exceptions op for når for lang en gang og et punkt placeret forkert
        this.aisleLenght = aisleLenght;
        this.startPoint = startPoint;

        this.endPoint = new Point2D(startPoint.getX() + aisleLenght, startPoint.getY());

        Point2D firstRackStartPoint = new Point2D(startPoint.getX()+1, startPoint.getY()-1);
        firstRack = new HorizontalRack(aisleLenght-2, firstRackStartPoint);
    }

    @Override
    public int getAisleLenght() {
        return 0;
    }

    @Override
    public Point2D getStartPoint() {
        return null;
    }

    @Override
    public Point2D getEndPoint() {
        return null;
    }

    @Override
    public Rack getFirstRack() {
        return null;
    }

    @Override
    public Rack getSecondRack() {
        return null;
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
