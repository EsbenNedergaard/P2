package Warehouse;

import Exceptions.FullRackException;
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
    private List<Node> nodeList;


    private static final int AISLE_LENGTH = 36;
    private static final int AISLE_PADDING = 4;


    public Warehouse22b() {
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

        for(Aisle element : aisleList) {
            element.updateNodeGrid(nodeList);
        }
    }

    private void createProducts() {
        //aisleList.get(0).getFirstRackRow().addProduct(new Product(1));
        aisleList.get(0).getFirstRackRow().addProduct(new Product(2));

        int i = 0;
        for (Aisle aisleElement : aisleList) {
            try {
                while (true) {
                    aisleElement.addProduct(new Product(i));
                    i++;
                }
            }

            catch (FullRackException exc) {
                System.out.println("Aisle is full!");
            }
        }
    }

    private void createNodeGrid(){
        nodeList = new ArrayList<>();
        for(int j = 0; j < width; j++) {
            for(int i = 0; i < length; i++) {
                nodeList.add(new Node(new Point2D(i, j)));
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
    public List<Node> getNodeList() {
        return this.nodeList;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getLength() {return this.length;
    }
}
