package Warehouse;

import BackEnd.Geometry.Point2D;

public class PickingPoint {
    private Point2D pickPoint;
    private Product product;
    private int pickTime;

    public PickingPoint(Point2D pickPoint, Product product) {
        this.pickPoint = pickPoint;
        this.product = product;
        this.pickTime = 5;
    }

    public void setPickPoint(Point2D pickPoint) {
        this.pickPoint = pickPoint;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPickTime(int pickTime) {
        this.pickTime = pickTime;
    }
}
