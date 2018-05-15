package Warehouse.Racks;

import Warehouse.Exceptions.FullRackException;
import BackEnd.Geometry.Point2D;
import Warehouse.Product;
import Warehouse.Shelf;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rack {
    private List<Shelf> shelfList = new ArrayList<>();
    private int amountOfShelvesInRack;
    private Point2D rackPosition;

    public Rack(int amountOfShelvesInRack, Point2D rackPosition) {
        this.amountOfShelvesInRack = amountOfShelvesInRack;
        this.rackPosition = rackPosition;

        for (int i = 0; i < amountOfShelvesInRack; i++) {
            shelfList.add(new Shelf());
        }
    }

    public void addProduct(Product product) {
        if (isFull()) {
            throw new FullRackException("This rack is already full");
        }
        product.setRack(this);

        for (Shelf shelf : shelfList) {
            if (!shelf.containsProduct()) {
                shelf.setProduct(product);
                return;
            }
        }
    }

    public boolean doesItContainProduct(Product product) {
        for (Shelf shelf : shelfList) {
            if(product.equals(shelf.getProduct()))
                return true;
        }
        return false;
    }

    public boolean isFull(){
        for (Shelf shelf : shelfList) {
            if (!shelf.containsProduct()) {
                return false;
            }
        }

        return true;
    }

    public List<Product> getProductList(){
        List<Product> productList = new ArrayList<>();

        for (Shelf shelf : shelfList) {
            if (shelf.containsProduct()) {
                productList.add(shelf.getProduct());
            }
        }

        return productList;
    }

    public Product getProduct(int shelfIndex) {
        return shelfList.get(shelfIndex).getProduct();
    }

    public int getAmountOfShelvesInRack() {
        return this.amountOfShelvesInRack;
    }

    public Point2D getRackPosition() {
        return rackPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rackPosition);
    }

    @Override
    public boolean equals(Object that) {
        if(this == that) return true;
        if(that == null || this.getClass() != that.getClass()) return false;

        Rack thatRack = (Rack) that;
        return this.getRackPosition().equals(thatRack.getRackPosition());
    }

}