package BackEnd.Geometry;

import Warehouse.Product;

import java.util.Objects;

public class PickingPoint extends Point2D {
    private Product product;

    public PickingPoint(Point2D pickPoint, Product product) {
        super(pickPoint);
        this.product = product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public int getPickTime() {
        return product.getPickTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PickingPoint that = (PickingPoint) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product);
    }
}
