package Warehouse;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.BaseLayer;
import Warehouse.Aisle.Aisle;
import Warehouse.Racks.HorizontalRackRow;
import Warehouse.Racks.RackRow;

import java.util.ArrayList;
import java.util.List;

public class Simple7x7Warehouse implements Warehouse {
    private List<Node> nodeList;
    private List<RackRow> rackRowList;

    public Simple7x7Warehouse() {
        this.nodeList = new ArrayList<>();
        this.rackRowList = new ArrayList<>();

        this.createNodeGrid();
        this.setupRackRows();
    }

    private void createNodeGrid(){
        nodeList = new ArrayList<>();
        for(int j = 0; j < this.getWidth(); j++) {
            for(int i = 0; i < this.getLength(); i++) {
                nodeList.add(new Node(new Point2D(i, j)));
            }
        }
    }

    private void setupRackRows() {
        Point2D firstRackRowStartPoint = new Point2D(1, 1);
        Point2D secondRackRowStartPoint = new Point2D(1, 3);

        int rackRowLenght = 3;
        int shelvesPerRack = 3;
        this.rackRowList.add(new HorizontalRackRow(firstRackRowStartPoint, rackRowLenght, shelvesPerRack));
        this.rackRowList.add(new HorizontalRackRow(secondRackRowStartPoint, rackRowLenght, shelvesPerRack));

        for(RackRow rackRow : this.getRackRowList()) {
            rackRow.setRacksAsObstacles(nodeList);
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

        return pickingPoints;
    }

    @Override
    public int getWidth() {
        return 5;
    }

    @Override
    public int getLength() {
        return 5;
    }

    @Override
    public Point2D getRouteStartPoint() {
        return new Point2D(0,0);
    }

    @Override
    public Point2D getRouteEndPoint() {
        return new Point2D(0, 0);
    }
}
