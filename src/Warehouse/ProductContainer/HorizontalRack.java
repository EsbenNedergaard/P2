package Warehouse.ProductContainer;

import Exceptions.FullRackException;
import Exceptions.IllegalProductPositionException;
import Exceptions.IllegalRackDimensionException;
import Geometry.Point2D;
import Warehouse.Product;
import javafx.scene.paint.Color;


import static Warehouse.GUIWarehouse.TILE_SIZE;

public class HorizontalRack extends Rack {
    public HorizontalRack(String name, int length, Point2D startPoint) {
        super(name, length, startPoint);
        setUpGraphics();
    }

    private void setUpGraphics(){
        setWidth(getRackLength() * TILE_SIZE);
        setHeight(TILE_SIZE);

        Point2D startPoint = this.getStartPoint();
        relocate(startPoint.getXPixels(), startPoint.getYPixels());
        // Color of rack
        setFill(Color.valueOf("yellow"));
    }

    @Override
    Point2D createProductPlacementPoint(int productPosition) {
        int rackPositionX = this.getStartPoint().getX();
        int rackPositionY = this.getStartPoint().getY();
        return new Point2D(rackPositionX + productPosition, rackPositionY);
    }


    public static boolean rackOutsideWarehouse(Point2D rackStartPoint, int rackLength, int widthWarehouse, int heightWarehouse) {
        int rackPositionX = rackStartPoint.getX();
        int rackPositionY = rackStartPoint.getY();


        if(widthWarehouse < rackPositionX + rackLength || rackPositionX + rackLength < 0)
            return true;

        if(heightWarehouse <= rackPositionY || rackPositionY < 0)
            return true;

        return false;
    }

}
