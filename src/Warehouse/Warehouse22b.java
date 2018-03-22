package Warehouse;

import Exceptions.FullRackException;
import Exceptions.IllegalProductPositionException;
import Geometry.Point2D;
import Warehouse.Aisle.Aisle;
import Warehouse.Aisle.HorizontalAisle;
import Warehouse.ProductContainer.HorizontalRack;
import Warehouse.ProductContainer.Rack;

import java.util.ArrayList;
import java.util.List;

public class Warehouse22b implements Warehouse {
    private int width;
    private int height;
    private List<Aisle> aisleList = new ArrayList<>();

    public static final int AISLE_LENGTH = 36;
    public static final int AISLE_PADDING = 4;


    public Warehouse22b() {
        width = AISLE_LENGTH + (AISLE_PADDING * 2);
        height = 12;
        createAisleList();
    }

    public void createAisleList() {
        aisleList.add(new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 1)));
        aisleList.add(new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 4)));
        aisleList.add(new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 7)));
        aisleList.add(new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 10)));
    }

    @Override
    public List<Rack> getRackList() {
        List<Rack> tempRackList = new ArrayList<>();
        for(Aisle aisleElement : aisleList) {
            tempRackList.addAll(aisleElement.getRackList());
        }
        return tempRackList;
    }

    @Override
    public List<Aisle> getAisleList() {
        return aisleList;
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
