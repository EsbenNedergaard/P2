package Warehouse;

import Geometry.Node;
import Geometry.Point2D;
import Warehouse.Aisle.Aisle;
import Warehouse.Aisle.HorizontalAisle;
import Warehouse.Racks.Rack;
import Warehouse.Racks.RackRow;

import java.util.ArrayList;
import java.util.List;

public class Dexion implements Warehouse {
    private int length;
    private int width;
    private List<Aisle> aisleList;
    private List<Node> nodeList;

    private static final int AISLE_LENGTH = 36;
    private static final int AISLE_PADDING = 4;


    public Dexion() {
        length = AISLE_LENGTH + (AISLE_PADDING * 2);
        width = 12;
        createNodeGrid();
        createAisleList();
        createProducts();
    }

    private void createNodeGrid(){
        nodeList = new ArrayList<>();
        for(int j = 0; j < width; j++) {
            for(int i = 0; i < length; i++) {
                nodeList.add(new Node(new Point2D(i, j)));
            }
        }

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
            element.setRacksAsObstacles(nodeList);
        }
    }

    private void createProducts() {

//        aisleList.get(0).getFirstRackRow().addProduct(new Product(1));
//        aisleList.get(0).getFirstRackRow().addProduct(new Product(2));
//        aisleList.get(1).getSecondRackRow().getRackByIndex(5).addProduct(new Product(3));d
        int i = 1;
        for (Aisle aisleElement : aisleList) {
            for (RackRow rackRowElement : aisleElement.getRackRowList()) {
                for (Rack rackElement : rackRowElement.getRackArray()) {
                    while (!rackElement.checkIfFull()) {
                        rackElement.addProduct(new Product(i));
                        i++;
                    }
                }
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
    public List<Point2D> getPickingPoints(List<Product> productPickList) {
        List<Point2D> pickingPointList = new ArrayList<>();

        for (Aisle aisleElement : aisleList) {
            pickingPointList.addAll(aisleElement.getPickingPoints(productPickList));
        }

        return pickingPointList;
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
