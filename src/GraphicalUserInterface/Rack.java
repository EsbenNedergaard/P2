package GraphicalUserInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static GraphicalUserInterface.GUIWarehouse.TILE_SIZE;

public class Rack extends Rectangle {

    private String name;
    private int xCoordinate, yCoordinate;
    private List<Product> productList = new ArrayList<>();

    public Rack(String name, int width, int height, int x, int y) {
        // TODO: A rack can only hold one row or one column of products
        // TODO: so, if width > 1 when height = 1 or if height > 1 then width = 1

        this.name = name;
        this.xCoordinate = x;
        this.yCoordinate = y;

        setWidth(width);
        setHeight(height);

        relocate(x * TILE_SIZE, y * TILE_SIZE);
        setFill(Color.valueOf("yellow"));

    }

    public void addProduct(Product product) {
        // Elements of list decides which coordinates the product should have

        // TODO: Add product and set location x and y by value of rack x and y
        // TODO: Use product.relocate();

        // TODO: Try to add product which already got a location

    }

    public Product getProduct(int id) {
        for(Product item : productList) {
            if(item.getById() == id)
                return item;
        }

        // TODO: skal caste en exception om at produktet ikke findes!!!!!
        return new Product("NULL", 0, 0, 0);
    }

    public List<Product> getProductList() {
        return this.productList;
    }

}
