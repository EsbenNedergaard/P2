package Warehouse;

import Warehouse.Racks.Rack;

import java.util.Objects;

public class Product {
    private String name;
    private int id;
    private Rack rack;

    public Product(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public String getName() {
        return name;
    }

    int getId() {
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
        if(this == that) return true;
        if(that == null || this.getClass() != that.getClass()) return false;

        Product product = (Product) that;

        return  product.getId() == this.id;
    }


}
