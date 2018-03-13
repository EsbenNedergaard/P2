package GraphicalUserInterface;

import javafx.scene.paint.Color;

public class Product extends Tile {

    // Uses constructor from tile
    public Product(int x, int y) {
        super(x, y);
    }

    @Override
    public void design() {
        // Product background color
        setFill(Color.valueOf("green"));
    }


}
