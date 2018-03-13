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

    public void createTiles() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Tile tile = new Tile(x, y);
                tileGroup.getChildren().add(tile);
            }
        }
    }

    public void createRacks() {
        Rack rack = new Rack(2 * TILE_SIZE, 15 * TILE_SIZE, 1, 1);

        rackGroup.getChildren().addAll(rack, createProducts());

    }

    public Group createProducts() {
        Group productGroup = new Group();
        Product product1 = new Product(0, 0);
        Product product2 = new Product(1, 0);
        productGroup.getChildren().addAll(product1, product2);
        return productGroup;
    }

    public Parent createContent() {
        Pane root = new Pane();

        createTiles();
        createRacks();

        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(rackGroup, tileGroup);


        return root;
    }
}

