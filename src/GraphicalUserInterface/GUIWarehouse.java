package GraphicalUserInterface;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUIWarehouse extends Application {

    public static final int SCALE = 3;

    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    public static final int TILE_SIZE = 5 * SCALE;

    private Group tileGraphicalGroup = new Group();
    private Group rackGraphicalGroup = new Group();

    public void createTiles() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Tile tile = new Tile(x, y);
                tileGraphicalGroup.getChildren().add(tile);
            }
        }
    }

    public void createRack() {
        // TODO: Should not create both rack and products

        // Create rack
        Rack rack = new Rack("A", 1, 15, 2, 2);
        Group productGraphicalGroup = new Group();

        // Create products and put into rack
        // Rack holds an array of products

        rack.addProduct(new Product("Apple", 1, 2, 2));
        rack.addProduct(new Product("Orange", 2, 2, 3));
        rack.addProduct(new Product("Grapes", 3));
        rack.addProduct(new Product("Anton", 10, 2, 10));

        // Add to graphical group
        for(Product product : rack.getProductList())
            productGraphicalGroup.getChildren().add(product);

        rackGraphicalGroup.getChildren().addAll(rack, productGraphicalGroup);

    }

    public Parent createContent() {
        Pane root = new Pane();

        createTiles();
        createRack();

        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
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

