package Warehouse.ProductContainer;

import Exceptions.FullRackException;
import Exceptions.IllegalProductPositionException;
import Exceptions.IllegalRackDimensionException;
import Warehouse.Product;
import Geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.List;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class VerticalRack extends Rack {
    public VerticalRack(String name, int length, Point2D startPoint) {
        super(name, length, startPoint);
        setUpGraphics();
    }

    private void setUpGraphics(){
        setWidth(TILE_SIZE);
        setHeight(this.getRackLength() * TILE_SIZE);
        Point2D startPoint = this.getStartPoint();
        relocate(startPoint.getXPixels(), startPoint.getYPixels());
        // Color of rack
        setFill(Color.valueOf("yellow"));
    }


    @Override
    Point2D createProductPlacementPoint(int productPosition) {
        int rackPositionX = this.getStartPoint().getX();
        int rackPositionY = this.getStartPoint().getY();
        return new Point2D(rackPositionX, rackPositionY + productPosition);
    }

    public static boolean rackOutsideWarehouse(Point2D rackStartPoint, int rackLength, int widthWarehouse, int heightWarehouse) {
        int rackPositionX = rackStartPoint.getX();
        int rackPositionY = rackStartPoint.getY();


        if(widthWarehouse <= rackPositionX || rackPositionX < 0)
            return true;

        if(heightWarehouse < rackPositionY + rackLength || rackPositionY + rackLength < 0)
            return true;

        return false;
    }
}
