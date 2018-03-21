package Warehouse;

import Exceptions.FullRackException;
import Exceptions.IllegalProductPositionException;
import Geometry.Point2D;
import Warehouse.ProductContainer.HorizontalRack;
import Warehouse.ProductContainer.Rack;

import java.util.ArrayList;
import java.util.List;

public class Warehouse22b implements Warehouse {
    private int width;
    private int height;
    private TileGrid tileGrid;
    private List<Rack> rackList = new ArrayList<>();

    public static final int RACK_LENGTH = 34;
    public static final int RACK_PADDING = 5;


    public Warehouse22b() {
        width = RACK_LENGTH + (RACK_PADDING * 2);
        height = 12;

        createTileGrid();
        createRackList();
    }

    public void createTileGrid() {
        tileGrid = new TileGrid(this.width, this.height);
    }

    public void createRackList() {

        rackList.add(new HorizontalRack(RACK_LENGTH, new Point2D(RACK_PADDING, 0)));
        rackList.add(new HorizontalRack(RACK_LENGTH, new Point2D(RACK_PADDING, 2)));
        rackList.add(new HorizontalRack(RACK_LENGTH, new Point2D(RACK_PADDING, 3)));
        rackList.add(new HorizontalRack(RACK_LENGTH, new Point2D(RACK_PADDING, 5)));
        rackList.add(new HorizontalRack(RACK_LENGTH, new Point2D(RACK_PADDING, 6)));
        rackList.add(new HorizontalRack(RACK_LENGTH, new Point2D(RACK_PADDING, 8)));
        rackList.add(new HorizontalRack(RACK_LENGTH, new Point2D(RACK_PADDING, 9)));
        rackList.add(new HorizontalRack(RACK_LENGTH, new Point2D(RACK_PADDING, 11)));


        try {
            rackList.get(0).addProduct(new Product("Apple", 1));
            rackList.get(0).addProduct(new Product("Orange", 2));
            rackList.get(0).addProduct(new Product("Grapes", 3));
            //rack.addProduct(new Product("Orange", 10));
        } catch (IllegalProductPositionException | FullRackException e) {
            System.out.println(e.toString());
        }

        try {
            rackList.get(1).addProduct(new Product("Pear", 5));
            rackList.get(1).addProduct(new Product("Orange", 10));
            rackList.get(1).addProduct(new Product("Grapes", 17));
            //rack.addProduct(new Product("Orange", 10));
        } catch (IllegalProductPositionException | FullRackException e) {
            System.out.println(e.toString());
        }
    }

    public TileGrid getTileGrid() {
        return this.tileGrid;
    }

    @Override
    public List<Rack> getRackList() {
        return this.rackList;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }
}
