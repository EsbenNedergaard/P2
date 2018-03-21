package Warehouse.ProductContainer;

import Exceptions.FullRackException;
import Exceptions.IllegalProductPositionException;
import Exceptions.IllegalRackDimensionException;
import Warehouse.Product;
import Geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.List;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class VerticalRack /*extends Rack*/ {
    /*public VerticalRack(String name, int length, Point2D startPoint, int widthWarehouse, int heightWarehouse) {
        super(name, length, startPoint, widthWarehouse, heightWarehouse);

        if(rackOutsideWarehouse())
            throw new IllegalRackDimensionException();

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

    public boolean rackOutsideWarehouse() {
        int rackPositionX = this.getStartPoint().getX();
        int rackPositionY = this.getStartPoint().getY();

        if(this.getWidthWarehouse() <= rackPositionX || rackPositionX < 0)
            return true;

        if(this.getHeightWarehouse() < rackPositionY + this.getRackLength() || rackPositionY + this.getRackLength() < 0)
            return true;

        return false;
    }*/
}
