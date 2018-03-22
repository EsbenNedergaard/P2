package Warehouse;

import Geometry.Point2D;
import Warehouse.Aisle.Aisle;
import Warehouse.Aisle.HorizontalAisle;
import Warehouse.Racks.RackRow;

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
        createProducts();
    }

    public void createAisleList() {
        aisleList.add(new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 1)));
        aisleList.add(new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 4)));
        aisleList.add(new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 7)));
        aisleList.add(new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 10)));
    }

    public void createProducts() {
        aisleList.get(0).getFirstRackRow().addProduct(new Product("Pear", 1));
        aisleList.get(0).getFirstRackRow().addProduct(new Product("Apple", 2));
    }

    @Override
    public List<RackRow> getRackRowList() {
        List<RackRow> tempRackRowList = new ArrayList<>();
        for(Aisle aisleElement : aisleList) {
            tempRackRowList.addAll(aisleElement.getRackRowList());
        }
        return tempRackRowList;
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
