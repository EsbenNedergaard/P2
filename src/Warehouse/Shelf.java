package Warehouse;


import Exceptions.Warehouse.ShelfNotInRackException;
import Warehouse.Racks.Rack;

import java.util.List;

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

    public Rack getRack() {
        return rack;
    }

    public boolean containsProduct() {
        return this.product != null;
    }

    public int getShelfIndex() {
        int amtShelves = this.rack.getAmountOfShelvesInRack();
        List<Shelf> shelves = rack.getShelfList();

        for(int i = 0; i < amtShelves; i++) {
            if (this.equals(shelves.get(i))) {
                return i;
            }
        }
        throw new ShelfNotInRackException("The shelf: " + this + "somehow was not placed in " + this.getRack() + "'s shelve list");
    }
}
