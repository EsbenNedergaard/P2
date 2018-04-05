package Warehouse;

import Geometry.Node;
import Geometry.Point2D;
import Warehouse.Aisle.Aisle;
import Warehouse.Aisle.HorizontalAisle;
import Warehouse.Racks.RackRow;

import java.util.ArrayList;
import java.util.List;

public class Warehouse22b implements Warehouse {
    private int length;
    private int width;
    private List<Aisle> aisleList;
    private Node[][] nodeGrid;


    private static final int AISLE_LENGTH = 36;
    private static final int AISLE_PADDING = 4;


    Warehouse22b() {
        length = AISLE_LENGTH + (AISLE_PADDING * 2);
        width = 12;
        createNodeGrid();
        createAisleList();
        createProducts();
    }

    public void createAisleList() {
        aisleList = new ArrayList<>();
        Aisle aisle1 = new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 1));
        Aisle aisle2 = new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 4));
        Aisle aisle3 = new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 7));
        Aisle aisle4 = new HorizontalAisle(AISLE_LENGTH, new Point2D(AISLE_PADDING, 10));

        aisleList.add(aisle1);
        aisleList.add(aisle2);
        aisleList.add(aisle3);
        aisleList.add(aisle4);
    }

    private void createProducts() {
        aisleList.get(0).getFirstRackRow().addProduct(new Product(1));
        aisleList.get(0).getFirstRackRow().addProduct(new Product(2));
    }

    private void createNodeGrid(){
        nodeGrid = new Node[length][width];

        for(int i = 0; i < length; i++) {
            for(int j = 0; j < width; j++) {
                nodeGrid[i][j] = new Node(new Point2D(i, j));
            }
        }
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
