package GraphicalWarehouse.GraphicalObjects;

import Warehouse.Product;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class ProductDesign extends Rectangle {

    public ProductDesign(Product product) {

        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        try {
            relocate(product.getProductPosition().getXPixels(), product.getProductPosition().getYPixels());
        } catch (NullPointerException e) {
            System.out.println("Some");
        }

        setFill(Color.valueOf("green"));

    }
}
