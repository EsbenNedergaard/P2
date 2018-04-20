package Warehouse.Aisle;

import Exceptions.FullRackException;
import Geometry.Node;
import Geometry.Point2D;
import Warehouse.Product;
import Warehouse.Racks.HorizontalRackRow;
import Warehouse.Racks.Rack;
import Warehouse.Racks.RackRow;

import java.util.ArrayList;
import java.util.List;

import static Geometry.NodeType.OBSTACLE;


/* This is a class that we use for our Aisles, the purpose of this class is to make it possible for us too make a
 * pickPointList because we know which rackRow is top rackRow, and which is bottom rackRow. This info is needed so
 * we can place the pickPoint over or under the rackRow*/
public class HorizontalAisle implements Aisle {
    private int aisleLength;
    private Point2D startPoint;
    private RackRow topRackRow;
    private RackRow bottomRackRow;

    public HorizontalAisle(int aisleLength, Point2D startPoint) {
        //TODO: Sæt exceptions op for når for lang en gang og et punkt placeret forkert\
        this.aisleLength = aisleLength;
        this.startPoint = startPoint;

        Point2D topRackRowStartPoint = new Point2D(startPoint.getX() + 1, startPoint.getY() - 1);
        Point2D bottomRackRowStartPoint = new Point2D(startPoint.getX() + 1, startPoint.getY() + 1);
        topRackRow = new HorizontalRackRow(topRackRowStartPoint, aisleLength - 2, 8);
        bottomRackRow = new HorizontalRackRow(bottomRackRowStartPoint, aisleLength - 2,8);
}

    @Override
    public void setRacksAsObstacles(List<Node> nodeGrid) {
        for(Node n : nodeGrid) {
            for(RackRow rackRow : getRackRowList()) {
                for(Rack rack : rackRow.getRackArray()) {
                    if (n.getX() == rack.getXCoordinate() && n.getY() == rack.getYCoordinate()) {
                        n.setNodeType(OBSTACLE);
                        break; //We jump out of the inner loop
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
    public RackRow getTopRackRow() {
        return this.topRackRow;
    }

    @Override
    public RackRow getBottomRackRow() {
        return this.bottomRackRow;
    }

    @Override
    public List<RackRow> getRackRowList() {
        List<RackRow> temp = new ArrayList<>();
        temp.add(topRackRow);
        temp.add(bottomRackRow);

        return temp;
    }

    @Override
    public List<Point2D> getPickingPoints(List<Product> productPickList) {
        List<Point2D> pickingPointList = new ArrayList<>();

        for (Product productElement : productPickList) {

            RackRow rackRowElement = getTopRackRow();
            int rackIndex = rackRowElement.doesItContainProduct(productElement);
            if (rackIndex != -1) {
                Point2D rackPosition = rackRowElement.getRackByIndex(rackIndex).getRackPosition();
                pickingPointList.add(new Point2D(rackPosition.getX(), rackPosition.getY() + 1));
            }


            rackRowElement = getBottomRackRow();
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
        for (RackRow rackRowElement : this.getRackRowList()) {
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
