package GraphicalUserInterface;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GuiApp extends Application {

    public static final int SCALE = 2;
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
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                tileGroup.getChildren().add(tile);
            }
        }
    }

    public void createRacks() {
        Rack rack = new Rack(20 * TILE_SIZE, 10 * TILE_SIZE, 1, 1);
        rackGroup.getChildren().add(rack);

    }

    public Parent createContent() {
        Pane root = new Pane();

        createTiles();
        createRacks();

        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, rackGroup);


        return root;
    }
}

