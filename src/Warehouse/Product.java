package Warehouse;

import Geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class Product {

    private String name;
    private int id;
    private Point2D productPosition;

    public Product(String name, int id) {
        this.name = name;
        this.id = id;
        this.productPosition = new Point2D(-1, -1);
    }

    public String getName() {
        return name;
    }

    public int getById() {
        return id;
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

        return  product.getById() == this.id;
    }


}
