package WarehouseSimulation;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.RouteFinders.FastestRouteFinder;
import BackEnd.Pathfinding.RouteFinders.RouteFinder;
import BackEnd.Pathfinding.PickingRoute;
import Exceptions.IllegalTextInputException;
import BackEnd.Graph.SpaceTimeGrid;
import WarehouseSimulation.GraphicalObjects.Colors.PickerColors;
import WarehouseSimulation.GraphicalObjects.Interaction.TableView.TableFactoryData;
import WarehouseSimulation.GraphicalObjects.Interaction.Handler.InputFieldDataHandler;
import static Warehouse.GUIWarehouse.*;
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

public class WarehouseSimulation {
    private Warehouse warehouse;
    private int LENGTH_WAREHOUSE;
    private int WIDTH_WAREHOUSE;
    //Route finder
    private RouteFinder routeFinder;
    private final int MAX_TIME = 500;
    private Group orderPickerGroup;
    private List<OrderPickerGraphic> orderPickerList;
    private PickerColors pickerColorGenerator;
    // The simulation
    private WarehouseGraphics warehouseSimulator;
    private int routesAdded = 0;
    private RouteHighlighter routeHighlighter;
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
        setupOptimalRouteFinder();
    }

    private void setupOptimalRouteFinder() {
        SpaceTimeGrid grid = new SpaceTimeGrid(this.warehouse.getBaseLayer(), MAX_TIME);

        Point2D routeStartPoint = warehouse.getRouteStartPoint();
        Point2D routeEndPoint = warehouse.getRouteEndPoint();
        this.routeFinder = new FastestRouteFinder(new PathFinder(grid), routeStartPoint, routeEndPoint);
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
        Button startOverButton = interactionGraphics.getReLaunchButton();
        Button normalSpeedButton = interactionGraphics.getNormalSpeedButton();
        Button doubleSpeedButton = interactionGraphics.getDoubleSpeedButton();
        Button speedUpByFiveButton = interactionGraphics.getSpeedUpByFiveButton();
        TextField inputField = interactionGraphics.getInputField();
        Table table = interactionGraphics.getTableView();

        VBox headingBox = new VBox(heading);
        HBox inputBox = new HBox(inputField, addRouteButton);
        HBox buttonsBox = new HBox(launchButton, startOverButton, resetAllButton);
        HBox speedOptionsBox = new HBox(normalSpeedButton, doubleSpeedButton, speedUpByFiveButton);
        speedOptionsBox.setSpacing(15);
        buttonsBox.setSpacing(20);

        gridpane.add(headingBox, 1, 1, 5, 1);
        gridpane.add(inputBox, 1, 2, 5, 2);
        gridpane.add(buttonsBox, 1, 4, 5, 4);
        gridpane.add(new Label("Change speed"), 1, 5, 5, 5);
        gridpane.add(speedOptionsBox, 1, 8, 5, 8);
        gridpane.setVgap(15); // Padding between rows

        borderPane.getStyleClass().add("interaction-border-pane");
        borderPane.setRight(gridpane);
        borderPane.setLeft(table.getTable());

        // Set event listeners
        setOnButtonClickEvent(
                addRouteButton, inputField, launchButton, resetAllButton,
                startOverButton, normalSpeedButton, doubleSpeedButton, speedUpByFiveButton,
                table
        );

        return new Group(borderPane);

    }

    private void setOnButtonClickEvent(Button addButton, TextField inputField,
                                       Button launchButton, Button resetAllButton,
                                       Button startOverButton, Button noramalSpeedButton,
                                       Button doubleSpeedButton, Button speedUpFiveButton,
                                       Table table) {
        // Run the same method on button clicked and ENTER pressed
        addButton.setOnMouseClicked(e -> this.actionsForAddProductIDs(inputField, table));
        inputField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                this.actionsForAddProductIDs(inputField, table);
            }
        });

        launchButton.setOnMouseClicked(e -> programTimer.start());
        startOverButton.setOnMouseClicked(e -> this.startOverOptions());
        resetAllButton.setOnMouseClicked(e -> this.resetWarehouseOptions(table));
        noramalSpeedButton.setOnMouseClicked(e -> this.scaleSimulationSpeed(1));
        doubleSpeedButton.setOnMouseClicked(e -> this.scaleSimulationSpeed(2));
        speedUpFiveButton.setOnMouseClicked(e -> this.scaleSimulationSpeed(5));

    }

    private void scaleSimulationSpeed(int scaleFactor) {
        for(OrderPickerGraphic currentOrderPicker : orderPickerList) {
            currentOrderPicker.setScaleSpeed(scaleFactor);
        }
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
        PickingRoute pickingRoute = getPickingRouteFromIDlist(tempProductIDList);

        String pickerColorValue = this.setupPicker(pickingRoute);

        // Create a data type which fits the table view
        TableFactoryData generatedProductIDs = new TableFactoryData(
                textHandler.generateProductIDString(),
                routesAdded,
                pickingRoute.getRoute(),
                pickingRoute.getProductPoints(),
                pickerColorValue
        );

        setViewRouteButtonClickEvent(generatedProductIDs);

        if(!generatedProductIDs.getProductIDSet().equals(""))
            table.add(generatedProductIDs);

        // Clear the input field when done
        inputField.clear();
    }

    private void startOverOptions() {
        if(UPDATE_COUNTER == 0) {
            showAlert("And how would you like this to be possible?", Alert.AlertType.INFORMATION);
        }
        else {
            UPDATE_COUNTER = 0;
            for(OrderPickerGraphic currentPicker : orderPickerList)
                currentPicker.startOver();
        }
    }

    private PickingRoute getPickingRouteFromIDlist(List<Integer> idList) {
        // Find route for picker
        List<PickingPoint> pickPointList = this.warehouse.getPickingPoints(idList);
        return routeFinder.calculateRoute(pickPointList);
    }

    private String setupPicker(PickingRoute pickingRoute) {
        // Get color for picker
        String pickerColorValue = pickerColorGenerator.getUnusedColor();
        OrderPickerGraphic orderPicker = new OrderPickerGraphic(pickingRoute.getRoute(), pickerColorValue);
        addPicker(orderPicker);
        routesAdded++;

        return pickerColorValue;
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
                                                   generatedProductIDs.getRouteColor(),
                                                   generatedProductIDs.getProductPositions());
        });
    }

    private void resetWarehouseOptions(Table table) {
        programTimer.stop();
        routeFinder.reset();
        orderPickerGroup.getChildren().clear();
        orderPickerList.clear();
        table.clear();
        routesAdded = 0;
        routeHighlighter.reset();
        pickerColorGenerator.resetIndexOfUnusedColor();
        UPDATE_COUNTER = 0;
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
                warehouseSimulator.getRackRowGroup(),
                routeHighlighter.getHighlightGroup(),
                warehouseSimulator.createStartAndEndPoints(
                        warehouse.getRouteStartPoint(),
                        warehouse.getRouteEndPoint()
                ),
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
