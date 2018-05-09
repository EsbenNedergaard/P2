package BackEnd.Geometry;

import Warehouse.Product;

public class PickingPoint extends Point2D {
    private Product product;
    private int pickTime;

    public PickingPoint(Point2D pickPoint, Product product) {
        super(pickPoint);
        this.product = product;
        this.pickTime = 3 + product.getShelfIndex();
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPickTime(int pickTime) {
        this.pickTime = pickTime;
    }

    public Product getProduct() {
        return product;
    }

    public int getPickTime() {
        return pickTime;
    }
}
