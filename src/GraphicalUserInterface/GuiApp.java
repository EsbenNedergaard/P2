package GraphicalUserInterface;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GuiApp extends Application {

    public static final int SCALE = 3;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    public static final int TILE_SIZE = 5 * SCALE;

    private Group tileGroup = new Group();
    private Group rackGroup = new Group();

    public void createTiles() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Tile tile = new Tile(x, y);
                tileGroup.getChildren().add(tile);
            }
        }
    }

    public void createRack() {
        // TODO: Should not create both rack and products

        // Create rack
        Rack rack = new Rack("A", TILE_SIZE, 15 * TILE_SIZE, 2, 2);
        Group productGroup = new Group();

        // Create products and put into rack
        // Rack holds an array of products

        rack.addProduct(new Product("Apples", 1, 0, 0));
        rack.addProduct(new Product("Oranges", 2, 0, 1));
        rack.addProduct(new Product("Monkeys", 3, 0, 2));

        // Add to graphical group
        for(Product product : rack.getProductList())
            productGroup.getChildren().add(product);

        rackGroup.getChildren().addAll(rack, productGroup);

    }

    public Parent createContent() {
        Pane root = new Pane();

        createTiles();
        createRack();

        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(rackGroup, tileGroup);

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Warehouse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

