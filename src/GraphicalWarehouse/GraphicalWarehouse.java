package GraphicalWarehouse;

import GraphicalWarehouse.GraphicalObjects.TableViewData.ProductIDSet;
import GraphicalWarehouse.InteractionHandler.InputFieldDataHandler;
import GraphicalWarehouse.GraphicalObjects.*;
import static Warehouse.GUIWarehouse.TILE_SIZE;
import static Warehouse.GUIWarehouse.SCALE;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import Warehouse.Racks.*;
import javafx.scene.*;
import Geometry.Node;
import Warehouse.*;
import java.util.*;
import Geometry.*;

public class GraphicalWarehouse {
    private Warehouse warehouse;
    private int LENGTH_WAREHOUSE;
    private int WIDTH_WAREHOUSE;
    // Path finder
    private OptimalRouteFinder pathFinder;
    private final int MAX_TIME = 500;
    // Graphic groups
    private Group tileGroup;
    private Group rackRowGroup;
    private Group rackGroup;
    private Group orderPickerGroup;
    private Group interactionFieldGroup;

    private List<OrderPickerGraphics> orderPickerList;

    // Table for routes and product IDs
    private Table table = new Table();

    // Animation programTimer
    private AnimationTimer programTimer;
    private int UPDATE_COUNTER = 0;

    public GraphicalWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.LENGTH_WAREHOUSE = warehouse.getLength();
        this.WIDTH_WAREHOUSE = warehouse.getWidth();
        this.orderPickerList = new ArrayList<>();

        setupPathFinder();
    }

    private void setupPathFinder() {
        SpaceTimeGrid grid = new SpaceTimeGrid(this.warehouse.getBaseLayer(), MAX_TIME);
        this.pathFinder = new OptimalRouteFinder(grid);
    }

    // Returns a group of graphical tiles which represents the warehouse floor
    private Group getTileGroup() {
        // Create a group to the graphical tiles
        tileGroup = new Group();

        for (int x = 0; x < LENGTH_WAREHOUSE; x++) {
            for (int y = 0; y < WIDTH_WAREHOUSE; y++) {
                Point2D tilePoint = new Point2D(x, y);
                tileGroup.getChildren().add(new Tile(tilePoint));
            }
        }

        return tileGroup;
    }

    private Group getRackRowGroup() {
        // Create a group of graphical racks
        Group rackRowGroup = new Group();

        for(RackRow rackRowElement : warehouse.getRackRowList()) {
            // Styles the rack
            RackRowGraphics graphicRack = new RackRowGraphics(rackRowElement);
            // Puts the rack into the group
            rackRowGroup.getChildren().add(graphicRack);
        }

        return rackRowGroup;
    }

    private Group getRackGroup() {
        // Create a group of graphical racks
        Group rackGroup = new Group();

        for(RackRow rackRowElement : warehouse.getRackRowList()) {

            for (Rack rackElement : rackRowElement.getRackList()) {
                RackGraphics graphicRack = new RackGraphics(rackElement);

                Label amtProducts = new Label("" + rackElement.getProductList().size());
                amtProducts.setPadding(new Insets(4, 5, 5, 8));
                amtProducts.setTextFill(Color.valueOf("white"));

                if (rackElement.getProductList().size() == 0)
                    amtProducts.setVisible(false);

                amtProducts.relocate(rackElement.getRackPosition().getXPixels(), rackElement.getRackPosition().getYPixels());
                rackGroup.getChildren().addAll(graphicRack, amtProducts);
            }
        }

        return rackGroup;
    }

    private void addPicker(OrderPickerGraphics picker) {
        orderPickerList.add(picker);
        orderPickerGroup.getChildren().add(picker);
    }

    private Group getInteractionFieldGroup() {

        InteractionGraphics interactionGraphics = new InteractionGraphics();
        BorderPane borderPane = new BorderPane();
        GridPane gridpane = new GridPane();

        gridpane.setMinWidth(TILE_SIZE * WIDTH_WAREHOUSE * SCALE);
        gridpane.setHgap(10);
        Label heading = new Label("Add product list to queue. Product id separated by comma");
        heading.getStyleClass().add("heading-label");

        // Get interaction objects
        Button addRouteButton = interactionGraphics.getAddDataButton();
        Button launchButton = interactionGraphics.getLaunchButton();
        Button resetAllButton = interactionGraphics.getResetAllButton();
        TextField inputField = interactionGraphics.getInputField();

        // Set objects in GridPane
        gridpane.add(heading, 1, 1, 5, 1);
        gridpane.add(inputField, 1, 2, 4, 2);
        gridpane.add(addRouteButton, 5, 2);
        gridpane.add(launchButton, 5, 5);
        gridpane.add(resetAllButton, 5, 6);

        borderPane.getStyleClass().add("interaction-border-pane");
        borderPane.setRight(gridpane);
        borderPane.setLeft(table.getTable());

        // Set event listeners
        setOnButtonClickEvent(addRouteButton, inputField, launchButton, resetAllButton);

        return new Group(borderPane);

    }

    private void setOnButtonClickEvent(Button addButton, TextField inputField, Button launchButton,
                                       Button resetAllButton) {
        // Run the same method on button clicked and ENTER pressed
        addButton.setOnMouseClicked(e -> this.actionsForAddProductIDs(inputField));
        inputField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                this.actionsForAddProductIDs(inputField);
            }
        });

        launchButton.setOnMouseClicked(e -> programTimer.start());

        // Reset button
        resetAllButton.setOnMouseClicked(e -> this.resetWarehouseOptions());
    }

    private void actionsForAddProductIDs(TextField inputField) {


        InputFieldDataHandler textHandler = new InputFieldDataHandler();
        // Get the id list from the input field
        List<Integer> tempProductIDList = textHandler.generateProductIDList(inputField.getText());

        // Find route for picker
        List<Point2D> pickPointList = this.warehouse.getPickingPointsFromIDs(tempProductIDList);
        List<Node> fastestRoute = this.pathFinder.calculateBestRoute(pickPointList);


        OrderPickerGraphics orderPicker = new OrderPickerGraphics(fastestRoute);
        addPicker(orderPicker);

        // Create a data type which fits the table view
        ProductIDSet generatedProductIDs = new ProductIDSet(textHandler.generateProductIDString());

        if(!generatedProductIDs.getProductIDSet().equals(""))
            table.add(generatedProductIDs);

        // Clear the input field when done
        inputField.clear();
    }

    private void resetWarehouseOptions() {
        programTimer.stop();
        pathFinder.reset();
        orderPickerGroup.getChildren().clear();
        orderPickerList.clear();
        table.clear();
    }

    public Parent getWarehouseGraphics() {
        Pane root = new Pane();
        BorderPane borderPane = new BorderPane();
        Group simulationElementsGroup = new Group();

        tileGroup = getTileGroup();
        rackRowGroup = getRackRowGroup();
        rackGroup = getRackGroup();
        orderPickerGroup = new Group();
        interactionFieldGroup = getInteractionFieldGroup();

        // Add all elements for the simulation
        simulationElementsGroup.getChildren().addAll(rackRowGroup, rackGroup, tileGroup, orderPickerGroup);

        borderPane.setTop(simulationElementsGroup);
        borderPane.setBottom(interactionFieldGroup);

        root.setPrefSize(LENGTH_WAREHOUSE * TILE_SIZE, WIDTH_WAREHOUSE * TILE_SIZE * 2.43);

        root.getChildren().addAll(borderPane);

        programTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };

        return root;
    }

    private void onUpdate() {
        UPDATE_COUNTER++;
        for(OrderPickerGraphics picker : orderPickerList) {
            if(picker.move(UPDATE_COUNTER));
        }
    }
}
