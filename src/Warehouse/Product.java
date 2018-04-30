package Warehouse;

import Exceptions.ProductNotInRackException;
import Warehouse.Racks.Rack;

import java.util.List;
import java.util.Objects;

public class Product {
    private int id;
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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object that) {
        // Two product objects are equal if they have the same ID
        if(this == that) return true;
        if(that == null || this.getClass() != that.getClass()) return false;

        Product product = (Product) that;

        return product.getId() == this.id;
    }

    public int getShelfIndex() {
        if(getRack() == null)
            throw new ProductNotInRackException();

        List<Product> productList = rack.getProductList();
        int productListSize = productList.size();

        for(int i = 0; i < productListSize; i++) {
            if(this.equals(productList.get(i))) {
                return i;
            }
        }

        throw new ProductNotInRackException();
    }
}
