package Warehouse;

import BackEnd.Geometry.Point2D;
import Exceptions.Warehouse.ProductNotInShelfException;

import java.util.List;
import java.util.Objects;

public class Product {
    private int id;
    private Shelf shelf;

    public Product(int id) {
        this.id = id;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public int getId() {
        return id;
    }

    public Shelf getShelf(){
        return this.shelf;
    }

    public Point2D getProductPosition() {
        return this.getShelf().getRack().getRackPosition();
    }

    private int getShelfIndex() {
       if(shelf == null) {
           throw new ProductNotInShelfException();
       }
       return shelf.getShelfIndex();
    }

    public int getPickTime(){
        int basePickTime = 3;
        return this.getShelfIndex() + basePickTime;
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
}
