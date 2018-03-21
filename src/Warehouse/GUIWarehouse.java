package Warehouse;

import Warehouse.ProductContainer.Rack;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;



// TODO: Make sure Rakcs cant be placed on top of each other
// TODO: Få splittet grafik og objekter op, og få ændre createRack til at returne et rack. Lave en class som modtager en racklist og laver grafik for denne.
// TODO: Få lavet en racklist, og lav funktion for grafik til denne
// TODO: Sæt op test.
// TODO: Skal have gjort så vi gemmer et ARRAY af tiles, og så Tiles gemmer info omkring, hvad er er på dem, da lige nu har vi ingen ide om, hvor der er er racks og tiles henne

public class GUIWarehouse extends Application {
    private Warehouse warehouse;
    private Group tileGroup = new Group();
    private Group rackGroup = new Group();
    private Group productGroup = new Group();

    private static final int SCALE = 3;
    public static final int TILE_SIZE = 5 * SCALE;

    public GUIWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void fillRackGroup(){
        List<Rack> rackList = warehouse.getRackList();
        for(Rack rackElement : rackList) {
            rackGroup.getChildren().add(rackElement);
            fillProductGroup(rackElement);
        }
    }

    private void fillProductGroup(Rack rackElement) {
        for(Product product : rackElement.getProductList())
            productGroup.getChildren().add(product);
    }

    public void fillTileGroup(){
        Tile[][] grid = warehouse.getTileGrid().getGrid();

        for (int x = 0; x < warehouse.getWidth(); x++) {
            for (int y = 0; y < warehouse.getHeight(); y++) {
                tileGroup.getChildren().add(grid[x][y]);
            }
        }
    }

    public Parent createContent() {
        Pane root = new Pane();
        fillRackGroup();
        fillTileGroup();

        root.setPrefSize(warehouse.getWidth() * TILE_SIZE, warehouse.getHeight() * TILE_SIZE);
        root.getChildren().addAll(rackGroup, productGroup, tileGroup);

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

