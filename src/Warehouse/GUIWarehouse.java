package Warehouse;
import GraphicalWarehouse.GraphicalWarehouse;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GUIWarehouse extends Application {
    private Warehouse warehouse;
    private GraphicalWarehouse graphicalWarehouse;

    public static final int SCALE = 5;
    public static final int TILE_SIZE = 5 * SCALE;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void init() throws Exception {
        this.warehouse = new Dexion();
        this.graphicalWarehouse = new GraphicalWarehouse(warehouse);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(graphicalWarehouse.getWarehouseGraphics());
        scene.getStylesheets().add("GraphicalWarehouse/Styles/stylesheet.css");
        primaryStage.setTitle("GUIWarehouse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

