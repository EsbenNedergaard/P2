package Warehouse;

import Geometry.Point2D;
import Warehouse.Aisle.Aisle;
import Warehouse.Aisle.HorizontalAisle;
import Warehouse.Racks.RackRow;

import java.util.ArrayList;
import java.util.List;

public class Warehouse22b implements Warehouse {
    private int length;
    private int width;
    private List<Aisle> aisleList = new ArrayList<>();

    private static final int AISLE_LENGTH = 36;
    private static final int AISLE_PADDING = 4;


    Warehouse22b() {
        length = AISLE_LENGTH + (AISLE_PADDING * 2);
        width = 12;
        createAisleList();
        createProducts();
    }

    public void createAisleList() {
        aisleList.add(new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 1)));
        aisleList.add(new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 4)));
        aisleList.add(new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 7)));
        aisleList.add(new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 10)));
    }

    private void createProducts() {
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
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getLength() {return this.length;
    }
}
