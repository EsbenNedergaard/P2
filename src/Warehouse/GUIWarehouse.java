package Warehouse;
import WarehouseSimulation.WarehouseSimulation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GUIWarehouse extends Application {
    private Warehouse warehouse;
    private WarehouseSimulation graphicalWarehouse;

    public static final int SCALE = 5;
    public static final int TILE_SIZE = 5 * SCALE;

    public static void main(String[] args) {
        launch(args);
    }

    public GUIWarehouse() {
        this.init();
    }

    @Override
    public void init() {
        //this.warehouse = new Dexion();
        this.warehouse = new SimpleNxNWarehouse(10);
        this.graphicalWarehouse = new WarehouseSimulation(warehouse);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(graphicalWarehouse.getWarehouseGraphics());
        scene.getStylesheets().add("WarehouseSimulation/Styles/stylesheet.css");
        primaryStage.setTitle("GUIWarehouse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

