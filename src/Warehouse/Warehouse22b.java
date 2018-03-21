package Warehouse;

import Exceptions.FullRackException;
import Exceptions.IllegalProductPositionException;
import Exceptions.IllegalRackDimensionException;
import Geometry.Point2D;
import Warehouse.ProductContainer.HorizontalRack;
import Warehouse.ProductContainer.Rack;
import Warehouse.ProductContainer.VerticalRack;

import java.util.ArrayList;
import java.util.List;

public class Warehouse22b implements Warehouse {
    private int width;
    private int height;
    private TileGrid tileGrid;
    private List<Rack> rackList = new ArrayList<>();


    public Warehouse22b() {
        width = 80;
        height = 40;

        createTileGrid();
        createRackList();

    }

    public void createTileGrid() {
        tileGrid = new TileGrid(this.width, this.height);
    }

    public void createRackList() {
        try {
            //TODO: something is fucky here, because it does not throw correctly
            if(VerticalRack.rackOutsideWarehouse(new Point2D(1, 100), 15, this.width, this.height))
                throw new IllegalRackDimensionException();
            rackList.add(new VerticalRack("A", 15, new Point2D(1, 1)));

            if(HorizontalRack.rackOutsideWarehouse(new Point2D(5,5), 15, this.width, this.height))
                throw new IllegalRackDimensionException();
            rackList.add(new HorizontalRack("B", 15, new Point2D(5,5)));

            try {
                rackList.get(0).addProduct(new Product("Apple", 1), 20);
                rackList.get(0).addProduct(new Product("Orange", 2));
                rackList.get(0).addProduct(new Product("Grapes", 3));
                //rack.addProduct(new Product("Orange", 10));
            } catch (IllegalProductPositionException | FullRackException e) {
                System.out.println(e.toString());
            }

            try {
                rackList.get(1).addProduct(new Product("Pear", 5));
                rackList.get(1).addProduct(new Product("Orange", 10));
                rackList.get(1).addProduct(new Product("Grapes", 17), 4);
                //rack.addProduct(new Product("Orange", 10));
            } catch (IllegalProductPositionException | FullRackException e) {
                System.out.println(e.toString());
            }
        } catch (IllegalRackDimensionException e) {
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
