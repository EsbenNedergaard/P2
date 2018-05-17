package Warehouse;

import BackEnd.Geometry.Node.Node;
import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.BaseLayer;
import Exceptions.Warehouse.RackRowDoesNotContainProductException;
import Warehouse.Racks.HorizontalRackRow;
import Warehouse.Racks.Rack;
import Warehouse.Racks.RackRow;

import java.util.ArrayList;
import java.util.List;

public class SimpleNxNWarehouse implements Warehouse {
    private List<Node> nodeList;
    private List<RackRow> rackRowList;
    private int size;
    private int amountOfProducts;

    public SimpleNxNWarehouse(int size) {
        this.nodeList = new ArrayList<>();
        this.rackRowList = new ArrayList<>();
        this.size = size;

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
        //We start at one and add 2 every time
        for (int i = 1; i < size; i += 2) {
            rackRowStartPoints.add(new Point2D(1, i));
        }

        int rackRowLenght = size - 2;
        int shelvesPerRack = 1;
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
        amountOfProducts = i - 1;
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

        for (Integer productId : productIdList) {
            Product tempProduct;
            for (RackRow rackRow : this.getRackRowList()) {
                try {
                    tempProduct = rackRow.getProductPointerFromID(productId);
                    Point2D productPosition = tempProduct.getProductPosition();
                    //We y-1, because it is a bottomRackRow and our coordinate system goes downward
                    pickingPoints.add(new PickingPoint(new Point2D(productPosition.getX(), productPosition.getY() - 1), tempProduct));
                } catch (RackRowDoesNotContainProductException ignored) {
                }
            }
        }

        return pickingPoints;
    }

    @Override
    public int getWidth() {
        return size;
    }

    @Override
    public int getLength() {
        return size;
    }

    @Override
    public Point2D getRouteStartPoint() {
        return new Point2D(0, 0);
    }

    @Override
    public Point2D getRouteEndPoint() {
        return new Point2D(0, 0);
    }

    @Override
    public int getAmountOfProducts() {
        return amountOfProducts;
    }
}
