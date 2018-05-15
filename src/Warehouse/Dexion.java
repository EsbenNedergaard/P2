package Warehouse;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Graph.BaseLayer;
import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import Warehouse.Aisle.Aisle;
import Warehouse.Aisle.HorizontalAisle;
import Warehouse.Racks.Rack;
import Warehouse.Racks.RackRow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Dexion implements Warehouse {
    private int length;
    private int width;
    private List<Aisle> aisleList;
    private List<Node> nodeList;
    private Point2D routeStartPoint;
    private Point2D routeEndPoint;

    private static final int AISLE_LENGTH = 34;
    private static final int AISLE_PADDING = 5;


    public Dexion() {
        length = AISLE_LENGTH + (AISLE_PADDING * 2);
        width = 12;
        createNodeGrid();
        createAisleList();
        createProducts();
        setStartAndEndPoints(new Point2D(0, 4), new Point2D(0, 6));
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
        int i = 1;
        for (Aisle aisleElement : aisleList) {
            for (RackRow rackRowElement : aisleElement.getRackRowList()) {
                for (Rack rackElement : rackRowElement.getRackList()) {
                    while (!rackElement.checkIfFull()) {
                        rackElement.addProduct(new Product(i));
                        i++;
                    }
                }
            }
        }
    }

    private void setStartAndEndPoints(Point2D routeStartPoint, Point2D routeEndPoint) {
        this.routeStartPoint = routeStartPoint;
        this.routeEndPoint = routeEndPoint;
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
    public List<PickingPoint> getPickingPoints(List<Integer> productIdList) {
        List<PickingPoint> pickingPointList = new ArrayList<>();

        for (Aisle aisleElement : aisleList) {
            pickingPointList.addAll(aisleElement.getPickingPoints(productIdList));
        }

        return pickingPointList;
    }

    @Override
    public Point2D getRouteStartPoint() {
        return routeStartPoint;
    }

    @Override
    public Point2D getRouteEndPoint() {
        return routeEndPoint;
    }

    @Override
    public List<Aisle> getAisleList() {
        return aisleList;
    }

    @Override
    public BaseLayer getBaseLayer() {
        return new BaseLayer(this.nodeList);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getLength() {return this.length;
    }
}
