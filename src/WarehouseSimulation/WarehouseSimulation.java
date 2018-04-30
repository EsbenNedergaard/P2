package WarehouseSimulation;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Pathfinding.OptimalRouteFinder;
import BackEnd.Pathfinding.PickingRoute;
import Exceptions.IllegalTextInputException;
import BackEnd.Graph.SpaceTimeGrid;
import WarehouseSimulation.GraphicalObjects.Colors.PickerColors;
import WarehouseSimulation.GraphicalObjects.Interaction.TableView.TableFactoryData;
import WarehouseSimulation.GraphicalObjects.Interaction.Handler.InputFieldDataHandler;
import static Warehouse.GUIWarehouse.TILE_SIZE;
import static Warehouse.GUIWarehouse.SCALE;

import WarehouseSimulation.GraphicalObjects.Interaction.InteractionGraphics;
import WarehouseSimulation.GraphicalObjects.Warehouse.WarehouseGraphics;
import WarehouseSimulation.GraphicalObjects.*;
import WarehouseSimulation.GraphicalObjects.Interaction.TableView.Table;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import Warehouse.*;
import java.util.*;

/* THIS IS THE WAREHOUSE SIMULATION, WHICH RETURNS A PARENT THAT CAN
 * BE SET IN A SCENE OBJECT. THE WAREHOUSE SIMULATION CONTAINS A SIMULATION
 * AND A INTERACTION FIELD. ALL GRAPHICS ARE SET FROM OTHER CLASSES */

public class WarehouseSimulation {

    private Warehouse warehouse;
    private int LENGTH_WAREHOUSE;
    private int WIDTH_WAREHOUSE;
    // Path finder
    private OptimalRouteFinder pathFinder;
    private final int MAX_TIME = 500;
    private Group orderPickerGroup;
    private List<OrderPickerGraphic> orderPickerList;
    private PickerColors pickerColorGenerator;
    // The simulation
    private WarehouseGraphics warehouseSimulator;
    // Number of routes added (Only used for the table)
    private int routesAdded = 0;
    private RouteHighlighter routeHighlighter;
    // Animation programTimer
    private AnimationTimer programTimer;
    private int UPDATE_COUNTER = 0;

    public WarehouseSimulation(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.LENGTH_WAREHOUSE = warehouse.getLength();
        this.WIDTH_WAREHOUSE = warehouse.getWidth();
        this.orderPickerList = new ArrayList<>();
        this.routeHighlighter = new RouteHighlighter();
        this.warehouseSimulator = new WarehouseGraphics(warehouse);
        this.pickerColorGenerator = new PickerColors();
        setupPathFinder();
    }

    private void setupPathFinder() {
        SpaceTimeGrid grid = new SpaceTimeGrid(this.warehouse.getBaseLayer(), MAX_TIME);
        this.pathFinder = new OptimalRouteFinder(grid);
    }

    private void addPicker(OrderPickerGraphic picker) {
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
        Table table = interactionGraphics.getTableView();

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
        setOnButtonClickEvent(addRouteButton, inputField, launchButton, resetAllButton, table);

        return new Group(borderPane);

    }

    private void setOnButtonClickEvent(Button addButton, TextField inputField,
                                       Button launchButton, Button resetAllButton, Table table) {
        // Run the same method on button clicked and ENTER pressed
        addButton.setOnMouseClicked(e -> this.actionsForAddProductIDs(inputField, table));
        inputField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                this.actionsForAddProductIDs(inputField, table);
            }
        });

        launchButton.setOnMouseClicked(e -> programTimer.start());

        // Reset button
        resetAllButton.setOnMouseClicked(e -> this.resetWarehouseOptions(table));

    }

    private void actionsForAddProductIDs(TextField inputField, Table table) {

        if (inputField.getText().isEmpty()) {
            showAlert("The text field was empty", Alert.AlertType.WARNING);
            return;
        }

        InputFieldDataHandler textHandler = new InputFieldDataHandler();

        // Get the id list from the input field
        List<Integer> tempProductIDList;
        try {
            tempProductIDList = textHandler.generateProductIDList(inputField.getText());
        }
        catch(IllegalTextInputException e) {
            showAlert(e.getMessage(), Alert.AlertType.WARNING);
            return;
        }

        // Get color for picker
        String pickerColor = pickerColorGenerator.getUnusedColor();

        // Find route for picker
        List<PickingPoint> pickPointList = this.warehouse.getPickingPointsFromIDs(tempProductIDList);
        PickingRoute fastestRoute = pathFinder.calculateBestRoute(pickPointList);

        OrderPickerGraphic orderPicker = new OrderPickerGraphic(fastestRoute.getRoute(), pickerColor);
        addPicker(orderPicker);

        // Create a data type which fits the table view
        routesAdded++;
        TableFactoryData generatedProductIDs = new TableFactoryData(
                textHandler.generateProductIDString(),
                routesAdded,
                fastestRoute.getRoute(),
                pickerColor
        );

        setViewRouteButtonClickEvent(generatedProductIDs);

        if(!generatedProductIDs.getProductIDSet().equals(""))
            table.add(generatedProductIDs);

        // Clear the input field when done
        inputField.clear();
    }

    private void showAlert(String contentText, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(contentText);
        alert.show();
    }

    // Sets a new event handler for the button "View"
    private void setViewRouteButtonClickEvent(TableFactoryData generatedProductIDs) {
        generatedProductIDs.getHighlightButton().setOnMouseClicked(e -> {
            routeHighlighter.setHighlightRouteList(generatedProductIDs.getRouteList(),
                                                   generatedProductIDs.getRouteColor());
        });
    }

    private void resetWarehouseOptions(Table table) {
        programTimer.stop();
        pathFinder.reset();
        orderPickerGroup.getChildren().clear();
        orderPickerList.clear();
        table.clear();
        routesAdded = 0;
        routeHighlighter.reset();
        pickerColorGenerator.resetIndexOfUnusedColor();
    }

    public Parent getWarehouseGraphics() {
        Pane root = new Pane();
        BorderPane borderPane = new BorderPane();
        Group simulationElementsGroup = new Group();
        orderPickerGroup = new Group();

        // Set interaction graphics
        Group interactionFieldGroup = getInteractionFieldGroup();

        // Add all elements for the simulation
        simulationElementsGroup.getChildren().addAll(
                // Graphical groups for simulations
                routeHighlighter.getHighlightGroup(),
                warehouseSimulator.getRackRowGroup(),
                warehouseSimulator.getRackGroup(),
                warehouseSimulator.getTileGroup(),
                orderPickerGroup
        );

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
        for(OrderPickerGraphic picker : orderPickerList) {
            if(picker.move(UPDATE_COUNTER));
        }
    }
}
