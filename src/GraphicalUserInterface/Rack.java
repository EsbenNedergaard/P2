package GraphicalUserInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static GraphicalUserInterface.GuiApp.TILE_SIZE;

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
        // TODO: check product out of rack

        productList.add(product);
    }

    public Product getProduct(int id) {
        for(Product product : productList) {
            if(product.getById() == id)
                return product;
        }

        // TODO: skal caste en exception om at productet ikke findes!!!!!
        return new Product("NULL", 0, 0, 0);
    }

    public List<Product> getProductList() {
        return this.productList;
    }

}
