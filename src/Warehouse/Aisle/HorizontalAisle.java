package Warehouse.Aisle;

import Exceptions.FullRackException;
import Geometry.Node;
import Geometry.NodeType;
import Geometry.Point2D;
import Warehouse.Product;
import Warehouse.Racks.HorizontalRackRow;
import Warehouse.Racks.Rack;
import Warehouse.Racks.RackRow;

import java.util.ArrayList;
import java.util.List;

public class HorizontalAisle implements Aisle {
    private int aisleLength;
    private Point2D startPoint;
    private Point2D endPoint;
    private List<RackRow> rackRowList = new ArrayList<>();

    public HorizontalAisle(int aisleLength, Point2D startPoint) {
        //TODO: Sæt exceptions op for når for lang en gang og et punkt placeret forkert
        this.aisleLength = aisleLength;
        this.startPoint = startPoint;

        this.endPoint = new Point2D(startPoint.getX() + aisleLength - 1, startPoint.getY());

        Point2D firstRackStartPoint = new Point2D(startPoint.getX() + 1, startPoint.getY() - 1);
        rackRowList.add(new HorizontalRackRow(firstRackStartPoint, aisleLength - 2, 8));

        Point2D secondRackStartPoint = new Point2D(startPoint.getX() + 1, startPoint.getY() + 1);
        rackRowList.add(new HorizontalRackRow(secondRackStartPoint, aisleLength - 2,8));
    }

    @Override
    public void updateNodeGrid(List<Node> nodeGrid) {
        for (RackRow rackRowElement : rackRowList) {
            for(Rack rackElement : rackRowElement.getRackArray()) {
                for (Node nodeElement : nodeGrid) {
                    if (nodeElement.getX() == rackElement.getXCoordinate() && nodeElement.getY() == rackElement.getYCoordinate()) {
                        nodeElement.setNodeType(NodeType.OBSTACLE);
                    }
                }
            }
        }
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
    public RackRow getFirstRackRow() {
        return this.rackRowList.get(0);
    }

    @Override
    public RackRow getSecondRackRow() {
        return this.rackRowList.get(1);
    }

    @Override
    public List<RackRow> getRackRowList() {
        return this.rackRowList;
    }

    @Override
    public List<Point2D> getPickingPoints(List<Product> productPickList) {
        List<Point2D> pickingPointList = new ArrayList<>();

        for (Product productElement : productPickList)
        {
            RackRow rackRowElement = getFirstRackRow();
            int rackIndex = rackRowElement.doesItContainProduct(productElement);
            if (rackIndex != -1) {
                Point2D rackPosition = rackRowElement.getRackByIndex(rackIndex).getRackPosition();
                pickingPointList.add(new Point2D(rackPosition.getX(), rackPosition.getY() + 1));
            }


            rackRowElement = getSecondRackRow();
            rackIndex = rackRowElement.doesItContainProduct(productElement);
            if (rackIndex != -1) {
                Point2D rackPosition = rackRowElement.getRackByIndex(rackIndex).getRackPosition();
                pickingPointList.add(new Point2D(rackPosition.getX(), rackPosition.getY() - 1));
            }
        }

        return pickingPointList;
    }

    @Override
    public boolean doesItContainProductID(int id) {
        return false;
    }

    @Override
    public void addProduct(Product e) {
        for (RackRow rackRowElement : rackRowList) {
            for (Rack rackElement : rackRowElement.getRackArray()) {
                if (!rackElement.checkIfFull()) {
                    rackElement.addProduct(e);
                    return;
                }
            }
        }
        throw new FullRackException("This aisle is already full");
    }
}
