package Warehouse;

import Geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class Product extends Rectangle {

    private String name;
    private int id;
    private Point2D productPosition;

    public Product(String name, int id) {
        this.name = name;
        this.id = id;

        this.productPosition = null;
    }

    private void setDesign() {
        // Product background color
        setFill(Color.valueOf("green"));
        // Graphical position
        setTranslateX(this.productPosition.getXPixels());
        setTranslateY(this.productPosition.getYPixels());
        // Pixel width of tile
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);
    }

    public String getName() {
        return name;
    }

    public int getById() {
        return id;
    }

    public int getXCoordinate() {
        if(productPosition == null)
            throw new NullPointerException("Tried to get an unplaced product");

        return this.productPosition.getX();
    }

    public int getYCoordinate() {
        if(productPosition == null)
            throw new NullPointerException("Tried to get an unplaced product");

        return this.productPosition.getY();
    }

    public Point2D getProductPosition() {
        if(productPosition == null)
            throw new NullPointerException("Tried to get an unplaced product");

        return productPosition;
    }

    public void setProductPosition(Point2D productPosition) {
        this.productPosition = productPosition;
        setDesign();
    }

}
