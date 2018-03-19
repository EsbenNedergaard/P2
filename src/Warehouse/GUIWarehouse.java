package Warehouse;

import Exceptions.FullRackException;
import Exceptions.IllegalProductPositionException;
import Warehouse.ProductContainer.Rack;
import Warehouse.ProductContainer.VerticalRack;
import javafx.application.Application;
import Geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUIWarehouse extends Application {

    public static final int SCALE = 3;

    public static final int WIDTH_WAREHOUSE = 80;
    public static final int HEIGHT_WAREHOUSE = 40;
    public static final int TILE_SIZE = 5 * SCALE;

    private Group tileGraphicalGroup = new Group();
    private Group rackGraphicalGroup = new Group();

    public void createTiles() {
        for (int x = 0; x < WIDTH_WAREHOUSE; x++) {
            for (int y = 0; y < HEIGHT_WAREHOUSE; y++) {
                Tile tile = new Tile(new Point2D(x, y));
                tileGraphicalGroup.getChildren().add(tile);
            }
        }
    }

    public void createRack() {
        // TODO: Should not create both rack and products

        // Create rack
        Rack rack = new VerticalRack("A", 12, new Point2D(1, 1));
        Group productGraphicalGroup = new Group();

        // Create products and put into rack
        // RackSome holds an array of products

        try {
            rack.addProduct(new Product("Apple", 1));
            rack.addProduct(new Product("Orange", 2));
            rack.addProduct(new Product("Grapes", 3), 20);
            //rack.addProduct(new Product("Orange", 10));
        } catch (IllegalProductPositionException | FullRackException e) {
            System.out.println(e.toString());
        }

        // Add to graphical group
        for(Product product : rack.getProductList())
            productGraphicalGroup.getChildren().add(product);

        rackGraphicalGroup.getChildren().addAll(rack, productGraphicalGroup);

    }

    public Parent createContent() {
        Pane root = new Pane();

        createTiles();
        createRack();

        root.setPrefSize(WIDTH_WAREHOUSE * TILE_SIZE, HEIGHT_WAREHOUSE * TILE_SIZE);
        root.getChildren().addAll(rackGraphicalGroup, tileGraphicalGroup);

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("GUIWarehouse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

