package Warehouse.ProductContainer;

import Exceptions.FullRackException;
import Exceptions.IllegalProductPositionException;
import Exceptions.IllegalRackDimensionException;
import Geometry.Point2D;
import Warehouse.Product;
import javafx.scene.paint.Color;



import static Warehouse.GUIWarehouse.HEIGHT_WAREHOUSE;
import static Warehouse.GUIWarehouse.TILE_SIZE;
import static Warehouse.GUIWarehouse.WIDTH_WAREHOUSE;

public class HorizontalRack extends Rack {
    public HorizontalRack(String name, int length, Point2D startPoint) {
        super(name, length, startPoint);

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
    Point2D createPlacementPoint(int productPosition) {
        int rackPositionX = this.getStartPoint().getX();
        int rackPositionY = this.getStartPoint().getY();
        return new Point2D(rackPositionX + productPosition, rackPositionY);
    }

    private boolean rackOutsideWarehouse() {
        int rackPositionX = this.getStartPoint().getX();
        int rackPositionY = this.getStartPoint().getY();

        if(WIDTH_WAREHOUSE < rackPositionX + getRackLength() || rackPositionX + getRackLength() < 0)
            return true;

        if(HEIGHT_WAREHOUSE <= rackPositionY  || rackPositionY < 0)
            return true;

        return false;
    }
}
