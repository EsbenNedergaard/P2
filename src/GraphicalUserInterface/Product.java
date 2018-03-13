package GraphicalUserInterface;

import javafx.scene.paint.Color;

public class Product extends Tile {
    public Product(int x, int y) {
        super(x, y);
    }

    @Override
    public void design() {
        setFill(Color.valueOf("green"));

    }


}
