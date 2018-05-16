package Warehouse.Aisle;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import Warehouse.Product;
import Warehouse.Racks.RackRow;

import java.util.List;

//TODO: Lave en liste af aisle, som er sorteret efter deres y-koordianter, og derefter kan vi bare regne afstanden ud mellem to elementer i streg på en liste for at finde afstande mellem punkterne

public interface Aisle {
    int getAisleLength();

    Point2D getStartPoint();

    List<RackRow> getRackRowList();

    boolean addProduct(Product e);

    List<PickingPoint> getPickingPoints(List<Integer> productIdList);
}