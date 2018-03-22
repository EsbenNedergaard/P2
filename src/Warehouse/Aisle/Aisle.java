package Warehouse.Aisle;

import Geometry.Point2D;
import Warehouse.Product;
import Warehouse.ProductContainer.RackRow;

import java.util.List;

//TODO: Lave en liste af aisle, som er sorteret efter deres y-koordianter, og derefter kan vi bare regne afstanden ud mellem to elementer i streg på en liste for at finde afstande mellem punkterne

public interface Aisle {
    int getAisleLength();
    Point2D getStartPoint();
    Point2D getEndPoint();

    RackRow getFirstRack();
    RackRow getSecondRack();
    List<RackRow> getRackRowList();

    List<Point2D> getPickingPoints(List<Product> productPickList);
    boolean doesItContainProductID(int id); //Should be used to look through the racks an see if it contains the product, and then we can add the products point to teh pickingpoints.
}