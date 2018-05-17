package Warehouse;

import BackEnd.Geometry.Point2D;
import Exceptions.Warehouse.ProductNotInRackException;
import Warehouse.Racks.Rack;

import java.util.List;
import java.util.Objects;

public class Product {
    private int id;
    private Shelf shelf;
    private Rack rack;

    public Product(int id) {
        this.id = id;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public int getId() {
        return id;
    }



    public Rack getRack() {
        return rack;
    }

    public Point2D getProductPostion() {
        return this.getRack().getRackPosition();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        // Two product objects are equal if they have the same ID
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Product that = (Product) o;

        return that.getId() == this.id;
    }

    public int getShelfIndex() {
        if (getRack() == null)
            throw new ProductNotInRackException();

        List<Product> productList = rack.getProductList();
        int productListSize = productList.size();

        for (int i = 0; i < productListSize; i++) {
            if (this.equals(productList.get(i))) {
                return i;
            }
        }

        throw new ProductNotInRackException();
    }
}
