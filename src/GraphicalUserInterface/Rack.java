package GraphicalUserInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static GraphicalUserInterface.GuiApp.TILE_SIZE;

public class Rack extends Rectangle {

    private int xCoordinate, yCoordinate;
    private Product[][] products;

    public Rack(int width, int height, int x, int y) {
        setWidth(width);
        setHeight(height);

        this.xCoordinate = x;
        this.yCoordinate = y;

        relocate(x * TILE_SIZE, y * TILE_SIZE);
        setFill(Color.valueOf("yellow"));

        products = new Product[width / TILE_SIZE][height / TILE_SIZE];
    }

    public void setProduct(Product product) {
        // TODO: Throw exception if out of bound

        int productOldX = (int) product.getTranslateX();
        int productOldY = (int) product.getTranslateY();

        product.setTranslateX((productOldX + this.xCoordinate) * TILE_SIZE);
        product.setTranslateY((productOldY + this.yCoordinate) * TILE_SIZE);

        products[productOldX][productOldY] = product;
    }

    public Product getProduct(int x, int y) {
        // TODO: Throw exception if out of bound
        return products[x][y];
    }

}
