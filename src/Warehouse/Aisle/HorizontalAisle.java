package Warehouse.Aisle;


import BackEnd.Geometry.PickingPoint;
import Warehouse.Exceptions.FullAisleException;
import Warehouse.Exceptions.FullRackException;
import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import Warehouse.Product;
import Warehouse.Racks.HorizontalRackRow;
import Warehouse.Racks.Rack;
import Warehouse.Racks.RackRow;

import java.util.ArrayList;
import java.util.List;

import static BackEnd.Geometry.NodeType.OBSTACLE;


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
        bottomRackRow = new HorizontalRackRow(bottomRackRowStartPoint, aisleLength,SHELVES_PER_RACK);
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

        RackRow topRackRow = getTopRackRow();
        RackRow bottomRackRow = getBottomRackRow();

        for (Integer productId : productIdList) {
            Product tempProduct = new Product(productId);

            // if top rack row contains the product
            int rackIndex = topRackRow.doesItContainProduct(tempProduct);
            if (rackIndex != -1) {
                List<Product> productsInRack = topRackRow.getRackByIndex(rackIndex).getProductList();
                //We do this to get the correct productPointer
                for(Product product : productsInRack) {
                    if(product.equals(tempProduct)) {
                        tempProduct = product;
                    }
                }

                Point2D productPosition = tempProduct.getProductPostion();
                //We add one to the Y, because it is a topRackRow and our coordinate system goes downward
                pickingPointList.add(new PickingPoint(new Point2D(productPosition.getX(), productPosition.getY() + 1), tempProduct));
            }
            else {
                // if bottom rack row contains the product
                rackIndex = bottomRackRow.doesItContainProduct(tempProduct);
                if (rackIndex != -1) {
                    List<Product> productsInRack = bottomRackRow.getRackByIndex(rackIndex).getProductList();
                    for (Product product : productsInRack) {
                        if (product.equals(tempProduct)) {
                            tempProduct = product;
                        }
                    }

                    Point2D rackPosition = bottomRackRow.getRackByIndex(rackIndex).getRackPosition();
                    pickingPointList.add(new PickingPoint(new Point2D(rackPosition.getX(), rackPosition.getY() - 1), tempProduct));
                }
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
