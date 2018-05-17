package Warehouse;


import Warehouse.Racks.Rack;

public class Shelf {
    private Product product;
    private Rack rack;

    public Shelf(Rack rack, Product product) {
        this.rack = rack;
        this.product = product;
    }

    public Shelf(Rack rack) {
        this.rack = rack;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public boolean containsProduct() {
        return this.product != null;
    }
}
