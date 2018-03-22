package GraphicalWarehouse.GraphicalObjects;

import Warehouse.Product;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class ProductDesign extends Rectangle {

    public ProductDesign(Product product) {
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        relocate(product.getProductPosition().getXPixels(), product.getProductPosition().getYPixels());

        setFill(Color.valueOf("green"));
    }
}
