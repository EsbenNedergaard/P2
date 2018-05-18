package Warehouse.Aisle;


import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import Exceptions.Warehouse.RackRowDoesNotContainProductException;
import Warehouse.Exceptions.FullAisleException;
import Warehouse.Product;
import Warehouse.Racks.HorizontalRackRow;
import Warehouse.Racks.RackRow;

import java.util.ArrayList;
import java.util.List;


/* This is a class that we use for our Aisles, the purpose of this class is to make it possible for us too make a
 * pickPointList because we know which rackRow is top rackRow, and which is bottom rackRow. This info is needed so
 * we can place the pickPoint over or under the rackRow*/
public class HorizontalAisle implements Aisle {
    private final int SHELVES_PER_RACK = 8;
    private int aisleLength;
    private Point2D startPoint;
    private RackRow topRackRow;
    private RackRow bottomRackRow;

    public HorizontalAisle(int aisleLength, Point2D startPoint) {
        //TODO: Sæt exceptions op for når for lang en gang og et punkt placeret forkert\
        this.aisleLength = aisleLength;
        this.startPoint = startPoint;

        Point2D topRackRowStartPoint = new Point2D(startPoint.getX(), startPoint.getY() - 1);
        Point2D bottomRackRowStartPoint = new Point2D(startPoint.getX(), startPoint.getY() + 1);
        topRackRow = new HorizontalRackRow(topRackRowStartPoint, aisleLength, SHELVES_PER_RACK);
        bottomRackRow = new HorizontalRackRow(bottomRackRowStartPoint, aisleLength, SHELVES_PER_RACK);
    }

    @Override
    public int getAisleLength() {
        return this.aisleLength;
    }

    @Override
    public Point2D getStartPoint() {
        return this.startPoint;
    }

    private RackRow getTopRackRow() {
        return this.topRackRow;
    }

    private RackRow getBottomRackRow() {
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
    public List<PickingPoint> getPickingPoints(List<Integer> productIdList) {
        List<PickingPoint> pickingPointList = new ArrayList<>();

        for (Integer productId : productIdList) {
            Product tempProduct;
            //We try to get the pointer to the product with the ID if it exists in the top rackRow
            try {
                tempProduct = topRackRow.getProductPointerFromID(productId);
                Point2D productPosition = tempProduct.getProductPosition();
                //We say y+1, because it is a topRackRow and our coordinate system goes downward
                pickingPointList.add(new PickingPoint(new Point2D(productPosition.getX(), productPosition.getY() + 1), tempProduct));
            } catch (RackRowDoesNotContainProductException ignored) {
            }

            //Same for the bottom.
            try {
                tempProduct = bottomRackRow.getProductPointerFromID(productId);
                Point2D productPosition = tempProduct.getProductPosition();
                //We y-1, because it is a bottomRackRow and our coordinate system goes downward
                pickingPointList.add(new PickingPoint(new Point2D(productPosition.getX(), productPosition.getY() - 1), tempProduct));
            } catch (RackRowDoesNotContainProductException ignored) {
            }
        }

        return pickingPointList;
    }

    @Override
    public boolean addProduct(Product e) {
        for (RackRow rackRowElement : this.getRackRowList()) {
            rackRowElement.addProduct(e);
        }
        throw new FullAisleException("This aisle is already full");
    }
}
