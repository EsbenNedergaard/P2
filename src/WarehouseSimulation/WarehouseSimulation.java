package WarehouseSimulation;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Pathfinding.RouteFinders.RouteFinder;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.PickingRoute;
import BackEnd.Pathfinding.RouteFinders.RouteFinder;
import Exceptions.IllegalTextInputException;
import Warehouse.Warehouse;
import WarehouseSimulation.GraphicalObjects.Colors.PickerColors;
import WarehouseSimulation.GraphicalObjects.Interaction.Handler.RandomProducts;
import WarehouseSimulation.GraphicalObjects.Interaction.TableView.TableFactoryData;
import WarehouseSimulation.GraphicalObjects.Interaction.Handler.InputFieldDataHandler;
import static Warehouse.GUIWarehouse.*;
import static WarehouseSimulation.GraphicalObjects.Interaction.ButtonType.*;

import WarehouseSimulation.GraphicalObjects.Interaction.InteractionGraphics;
import WarehouseSimulation.GraphicalObjects.Interaction.TableView.Table;
import WarehouseSimulation.GraphicalObjects.Interaction.TableView.TableFactoryData;
import WarehouseSimulation.GraphicalObjects.OrderPickerGraphic;
import WarehouseSimulation.GraphicalObjects.RouteHighlighter;
import WarehouseSimulation.GraphicalObjects.Warehouse.WarehouseGraphics;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

import static Warehouse.GUIWarehouse.SCALE;
import static Warehouse.GUIWarehouse.TILE_SIZE;

public class WarehouseSimulation {
    private Warehouse warehouse;
    // Path finder
    private RouteFinder routeFinder;
    private final int MAX_TIME = 500;
    private Group orderPickerGroup;
    private List<OrderPickerGraphic> orderPickerList;
    private PickerColors pickerColorGenerator;
    // The simulation
    private int routesAdded = 0;
    private RouteHighlighter routeHighlighter;
    private AnimationTimer programTimer;

    private int UPDATE_COUNTER = 0;

    private InteractionGraphics interactions;

    public WarehouseSimulation(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.orderPickerGroup = new Group();
        this.orderPickerList = new ArrayList<>();
        this.routeHighlighter = new RouteHighlighter();
        this.pickerColorGenerator = new PickerColors();
        this.interactions = new InteractionGraphics();
        setupOptimalRouteFinder();
    }

    private void setupOptimalRouteFinder() {
        SpaceTimeGrid grid = new SpaceTimeGrid(this.warehouse.getBaseLayer(), MAX_TIME);
        Point2D routeStartPoint = warehouse.getRouteStartPoint();
        Point2D routeEndPoint = warehouse.getRouteEndPoint();
        this.routeFinder = new RouteFinder(new PathFinder(grid), routeStartPoint, routeEndPoint);
    }

    public Parent getWarehouseGraphics() {
        Pane root = new Pane(getGraphicsInBorderBane());
        setupProgramTimer();
        return root;
    }

    private BorderPane getGraphicsInBorderBane() {
        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(getInteractionFieldGroup());
        borderPane.setTop(getSimulationElements());
        return borderPane;
    }

    private Group getInteractionFieldGroup() {
        BorderPane interactionPane = interactions.getInteractionPane();
        setButtonsClickEvents(interactions);
        return new Group(interactionPane);
    }

    private void setButtonsClickEvents(InteractionGraphics interactions) {
        setEnterToCallAddProductIDs();
        interactions.getButton(ADD).setOnMouseClicked(e -> this.actionsForAddProductIDs());
        interactions.getButton(LAUNCH).setOnMouseClicked(e -> programTimer.start());
        interactions.getButton(RELAUNCH).setOnMouseClicked(e -> this.reLaunchOptions());
        interactions.getButton(RESET).setOnMouseClicked(e -> this.resetWarehouse());
        interactions.getButton(SPEEDX1).setOnMouseClicked(e -> this.scaleSimulationSpeed(1));
        interactions.getButton(SPEEDX2).setOnMouseClicked(e -> this.scaleSimulationSpeed(2));
        interactions.getButton(SPEEDX5).setOnMouseClicked(e -> this.scaleSimulationSpeed(5));
        interactions.getButton(RANDOMIZE).setOnMouseClicked(e -> this.setRandomProductsToInputField());
    }

    private void setEnterToCallAddProductIDs() {
        interactions.getInputField().setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) this.actionsForAddProductIDs();
        });
    }

    private void actionsForAddProductIDs() {
        TextField inputField = interactions.getInputField();
        Table table = interactions.getTableView();

        if (inputField.getText().isEmpty()) {
            showAlert("The text field was empty", Alert.AlertType.WARNING);
            return;
        }

        InputFieldDataHandler textHandler = new InputFieldDataHandler();

        // Get the id list from the input field
        List<Integer> tempProductIDList;
        try {
            tempProductIDList = textHandler.generateProductIDList(inputField.getText());
        } catch (IllegalTextInputException e) {
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

        if (!generatedProductIDs.getProductIDSet().equals(""))
            table.add(generatedProductIDs);

        // Clear the input field when done
        inputField.clear();
    }

    private Group getSimulationElements() {
        WarehouseGraphics simulationElements = new WarehouseGraphics(warehouse);
        return new Group(
                simulationElements.getRackRowGroup(),
                routeHighlighter.getHighlightGroup(),
                simulationElements.createStartAndEndPoints(
                        warehouse.getRouteStartPoint(),
                        warehouse.getRouteEndPoint()
                ),
                simulationElements.getTileGroup(),
                orderPickerGroup
        );
    }

    private void addPicker(OrderPickerGraphic picker) {
        orderPickerList.add(picker);
        orderPickerGroup.getChildren().add(picker);
    }

    private void scaleSimulationSpeed(int scaleFactor) {
        for(OrderPickerGraphic currentOrderPicker : orderPickerList) {
            currentOrderPicker.setScaleSpeed(scaleFactor);
        }
    }

    private void setRandomProductsToInputField() {
        TextField inputField = interactions.getInputField();

        inputField.clear();
        RandomProducts rand = new RandomProducts();
        List<String> randomProductIDList = rand.nextProductIDList(5, warehouse.getAmountOfProducts());
        for(int i = 0; i < randomProductIDList.size(); i++) {
            String currentProductID = randomProductIDList.get(i);
            if(i != randomProductIDList.size() - 1)
                inputField.setText(inputField.getText() + currentProductID + ", ");
            else
                inputField.setText(inputField.getText() + currentProductID);
        }
        actionsForAddProductIDs();
    }

    private void reLaunchOptions() {
        if(UPDATE_COUNTER == 0) {
            showAlert("And how would you like this to be possible?", Alert.AlertType.INFORMATION);
        } else {
            UPDATE_COUNTER = 0;
            for (OrderPickerGraphic currentPicker : orderPickerList)
                currentPicker.startOver();
        }
    }

    private PickingRoute getPickingRouteFromIDlist(List<Integer> idList) {
        List<PickingPoint> pickPointList = this.warehouse.getPickingPoints(idList);
        return routeFinder.calculateFastestRoute(pickPointList);
    }

    private String setupPicker(PickingRoute pickingRoute) {
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

    private void resetWarehouse() {
        programTimer.stop();
        routeFinder.reset();
        orderPickerGroup.getChildren().clear();
        orderPickerList.clear();
        interactions.getTableView().clear();
        routesAdded = 0;
        routeHighlighter.reset();
        pickerColorGenerator.resetIndexOfUnusedColor();
        UPDATE_COUNTER = 0;
    }

    private void setupProgramTimer() {
        programTimer = new AnimationTimer() {
            @Override
            public void handle(long now) { onUpdate(); }
        };
    }

    private void onUpdate() {
        UPDATE_COUNTER++;
        for(OrderPickerGraphic picker : orderPickerList)
            if(picker.move(UPDATE_COUNTER));
    }
}