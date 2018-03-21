package Warehouse.ProductContainer;

import Exceptions.FullRackException;
import Exceptions.IllegalProductPositionException;
import Exceptions.IllegalRackDimensionException;
import Geometry.Point2D;
import Warehouse.Product;
import javafx.scene.paint.Color;


import static Warehouse.GUIWarehouse.TILE_SIZE;

public class HorizontalRack extends Rack {
    public HorizontalRack(String name, int length, Point2D startPoint, int widthWarehouse, int heightWarehouse) {
        super(name, length, startPoint, widthWarehouse, heightWarehouse);

        if(rackOutsideWarehouse())
            throw new IllegalRackDimensionException();

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

    private boolean rackOutsideWarehouse() {
        int rackPositionX = this.getStartPoint().getX();
        int rackPositionY = this.getStartPoint().getY();

        if(this.getWidthWarehouse() < rackPositionX + getRackLength() || rackPositionX + getRackLength() < 0)
            return true;

        if(this.getHeightWarehouse() <= rackPositionY  || rackPositionY < 0)
            return true;

        return false;
    }
}
