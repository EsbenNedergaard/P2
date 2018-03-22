package Warehouse.ProductContainer;
import Geometry.Point2D;

public class HorizontalRackRow extends RackRow {
    public HorizontalRackRow(int length, Point2D startPoint, int maxAmtInRack) {
        super(length, startPoint, maxAmtInRack);
    }

    //TODO: Fjern eller tag i brug
    @Override
    Point2D getRackPlacementPoint(int RackIndex) {
        int rackPositionX = this.getStartPoint().getX();
        int rackPositionY = this.getStartPoint().getY();
        return new Point2D(rackPositionX + RackIndex, rackPositionY);
    }

    @Override
    public boolean isHorizontal() {
        return true;
    }
}
