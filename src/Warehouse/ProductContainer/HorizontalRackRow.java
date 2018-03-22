package Warehouse.ProductContainer;
import Geometry.Point2D;

public class HorizontalRackRow extends RackRow {
    public HorizontalRackRow(int length, Point2D startPoint) {
        super(length, startPoint);
    }

    @Override
    Point2D createProductPlacementPoint(int productPosition) {
        int rackPositionX = this.getStartPoint().getX();
        int rackPositionY = this.getStartPoint().getY();
        return new Point2D(rackPositionX + productPosition, rackPositionY);
    }

    @Override
    public boolean isHorizontal() {
        return true;
    }
}
