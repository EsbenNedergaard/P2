package GraphicalUserInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static GraphicalUserInterface.GUIWarehouse.TILE_SIZE;

public class Product extends Rectangle {

    private String name;
    private int id;

    // Uses constructor from tile
    public Product(String name, int id, int x, int y) {
        this.name = name;
        this.id = id;
        // Position of object

        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);

        setDesign();
    }

    public Product(String name, int id) {
        this.name = name;
        this.id = id;

        setTranslateX(-1 * TILE_SIZE);
        setTranslateY(-1 * TILE_SIZE);

        setDesign();
    }

    public void setDesign() {
        // Product background color
        setFill(Color.valueOf("green"));
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

}
