package Warehouse;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.BaseLayer;
import Warehouse.Aisle.Aisle;
import Warehouse.Racks.HorizontalRackRow;
import Warehouse.Racks.Rack;
import Warehouse.Racks.RackRow;

import java.util.ArrayList;
import java.util.List;

public class Simple7x7Warehouse implements Warehouse {
    private List<Node> nodeList;
    private List<RackRow> rackRowList;
    private final int SIZE = 7;

    public Simple7x7Warehouse() {
        this.nodeList = new ArrayList<>();
        this.rackRowList = new ArrayList<>();

        this.createNodeGrid();
        this.setupRackRows();
    }

    private void createNodeGrid() {
        nodeList = new ArrayList<>();
        for (int j = 0; j < this.getWidth(); j++) {
            for (int i = 0; i < this.getLength(); i++) {
                nodeList.add(new Node(new Point2D(i, j)));
            }
        }
    }

    private void setupRackRows() {
        List<Point2D> rackRowStartPoints = new ArrayList<>();
        rackRowStartPoints.add(new Point2D(1, 1));
        rackRowStartPoints.add(new Point2D(1, 3));
        rackRowStartPoints.add(new Point2D(1, 5));

        int rackRowLenght = 5;
        int shelvesPerRack = 3;
        for (Point2D startPoint : rackRowStartPoints) {
            this.rackRowList.add(new HorizontalRackRow(startPoint, rackRowLenght, shelvesPerRack));
        }
        for (RackRow rackRow : this.getRackRowList()) {
            rackRow.setRacksAsObstacles(nodeList);
        }
        this.setupProducts();
    }

    private void setupProducts() {
        int i = 1;
        for (RackRow rackRow : this.getRackRowList()) {
            for (Rack rack : rackRow.getRackList()) {
                while (!rack.isFull()) {
                    rack.addProduct(new Product(i));
                    i++;
                }
            }
        }
    }

    @Override
    public List<RackRow> getRackRowList() {
        return rackRowList;
    }

    @Override
    public BaseLayer getBaseLayer() {
        return new BaseLayer(nodeList);
    }

    @Override
    public List<PickingPoint> getPickingPoints(List<Integer> productIdList) {
        List<PickingPoint> pickingPoints = new ArrayList<>();

        for (RackRow rackRow : this.getRackRowList()) {
            //pickingPoints.addAll();
        }

        return pickingPoints;
    }

    @Override
    public int getWidth() {
        return SIZE;
    }

    @Override
    public int getLength() {
        return SIZE;
    }

    @Override
    public Point2D getRouteStartPoint() {
        return new Point2D(0, 0);
    }

    @Override
    public Point2D getRouteEndPoint() {
        return new Point2D(0, 0);
    }
}
