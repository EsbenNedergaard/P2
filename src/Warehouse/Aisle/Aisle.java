package Warehouse.Aisle;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import BackEnd.Geometry.PickingPoint;
import Warehouse.Product;
import Warehouse.Racks.RackRow;

import java.util.List;

//TODO: Lave en liste af aisle, som er sorteret efter deres y-koordianter, og derefter kan vi bare regne afstanden ud mellem to elementer i streg på en liste for at finde afstande mellem punkterne

public interface Aisle {
    int getAisleLength();
    Point2D getStartPoint();

    List<RackRow> getRackRowList();

    void addProduct(Product e);
    List<PickingPoint> getPickingPoints(List<Integer> productIdList);
    boolean doesItContainProductID(int id); //Should be used to look through the racks an see if it contains the product, and then we can add the products point to the pickingPoints.
    void setRacksAsObstacles(List<Node> nodeGrid);
}