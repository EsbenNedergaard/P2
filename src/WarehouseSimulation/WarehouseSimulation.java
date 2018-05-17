package WarehouseSimulation;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Pathfinding.FastestAndShortestRoute;
import BackEnd.Pathfinding.RouteFinders.RouteFinder;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.PickingRoute;
import Exceptions.IllegalTextInputException;
import Warehouse.Warehouse;
import WarehouseSimulation.GraphicalObjects.Colors.PickerColors;
import WarehouseSimulation.GraphicalObjects.Interaction.Handler.RandomProducts;
import WarehouseSimulation.GraphicalObjects.Interaction.TableView.TableFactoryData;
import WarehouseSimulation.GraphicalObjects.Interaction.Handler.InputFieldDataHandler;

import static WarehouseSimulation.GraphicalObjects.Interaction.ButtonType.*;

import WarehouseSimulation.GraphicalObjects.Interaction.InteractionGraphics;
import WarehouseSimulation.GraphicalObjects.Interaction.TableView.Table;
import WarehouseSimulation.GraphicalObjects.OrderPicker.MovingObject;
import WarehouseSimulation.GraphicalObjects.OrderPicker.OrderPicker;
import WarehouseSimulation.GraphicalObjects.OrderPicker.ShadowPicker;
import WarehouseSimulation.GraphicalObjects.RouteHighlighter;
import WarehouseSimulation.GraphicalObjects.Warehouse.WarehouseGraphics;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarehouseSimulation {
    private Warehouse warehouse;
    // Path finder
    private RouteFinder routeFinder;
    private final int MAX_TIME = 500;
    private Group orderPickerGroup;
    private List<MovingObject> orderPickerList;
    private PickerColors pickerColorGenerator;
    // The simulation
    private int routesAdded = 0;
    private RouteHighlighter routeHighlighter;
    private AnimationTimer programTimer;
    private InputFieldDataHandler textHandler;

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
        interactions.getButton(ADDFASTEST).setOnMouseClicked(e -> this.actionsForAddFastestRoute());
        interactions.getButton(ADDBOTH).setOnMouseClicked(e -> this.actionsForAddBothRoutes());
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
            if(e.getCode() == KeyCode.ENTER) this.actionsForAddFastestRoute();
        });
    }

    private void actionsForAddBothRoutes() {
        TextField inputField = interactions.getInputField();

        if(alertIfInputFieldEmpty(inputField))
            return;

        textHandler = new InputFieldDataHandler();
        List<Integer> tempProductIDList = getProductIDList(inputField.getText());

        if(tempProductIDList != null) {
            FastestAndShortestRoute fastAndShortestRoute = getBothRoutesFromIDList(tempProductIDList);
            setupPickers(fastAndShortestRoute.getFastestRoute(), fastAndShortestRoute.getShortestRoute());
        } else {
            return;
        }
        inputField.clear();
    }

    private void actionsForAddFastestRoute() {
        TextField inputField = interactions.getInputField();
        if(alertIfInputFieldEmpty(inputField))
            return;

        textHandler = new InputFieldDataHandler();
        List<Integer> tempProductIDList = getProductIDList(inputField.getText());

        if(tempProductIDList != null) {
            PickingRoute fastestRoute = getFastestRouteFromIDList(tempProductIDList);
            setupPicker(fastestRoute);
        } else {
            return;
        }
        inputField.clear();
    }

    private List<Integer> getProductIDList(String productIDString) {
        try {
            return textHandler.generateProductIDList(productIDString);
        } catch (IllegalTextInputException e) {
            showAlert(e.getMessage(), Alert.AlertType.WARNING);
            return null;
        }
    }

    private boolean alertIfInputFieldEmpty(TextField inputField) {
        if(inputField.getText().isEmpty()) {
            showAlert("The text field was empty", Alert.AlertType.WARNING);
            return true;
        }
        return false;
    }

    private void setupPicker(PickingRoute pickingRoute) {
        String pickerColorValue = pickerColorGenerator.getUnusedColor();
        MovingObject orderPicker = new OrderPicker(pickingRoute.getRoute(), pickerColorValue);
        addPicker(orderPicker);
        routesAdded++;
        this.addPickerToTable(pickingRoute, pickerColorValue);
    }

    private void setupPickers(PickingRoute fastestRoute, PickingRoute shortestRoute) {
        String pickerColorValue = pickerColorGenerator.getUnusedColor();
        MovingObject fastestPicker = new OrderPicker(fastestRoute.getRoute(), pickerColorValue);
        MovingObject shortestPicker = new ShadowPicker(shortestRoute.getRoute(), pickerColorValue);
        addPicker(fastestPicker, shortestPicker);
        routesAdded++;
        this.addPickerToTable(fastestRoute, pickerColorValue);
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

    private void addPicker(MovingObject ... picker) {
        orderPickerList.addAll(Arrays.asList(picker));
        for(MovingObject currentPicker : picker)
            orderPickerGroup.getChildren().add((Node) currentPicker);
    }

    private void scaleSimulationSpeed(int scaleFactor) {
        for(MovingObject currentOrderPicker : orderPickerList) {
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
        actionsForAddBothRoutes();
    }

    private void reLaunchOptions() {
        if(UPDATE_COUNTER == 0) {
            showAlert("And how would you like this to be possible?", Alert.AlertType.INFORMATION);
        } else {
            UPDATE_COUNTER = 0;
            for (MovingObject currentPicker : orderPickerList)
                currentPicker.startOver();
        }
    }

    private FastestAndShortestRoute getBothRoutesFromIDList(List<Integer> idList) {
        List<PickingPoint> pickPointList = this.warehouse.getPickingPoints(idList);
        return routeFinder.calculateBothRoutes(pickPointList);
    }

    private PickingRoute getFastestRouteFromIDList(List<Integer> idList) {
        List<PickingPoint> pickPointList = this.warehouse.getPickingPoints(idList);
        return routeFinder.calculateFastestRoute(pickPointList);
    }

    private void addPickerToTable(PickingRoute pickingRoute, String pickerColorValue){
        Table table = interactions.getTableView();
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
        for(MovingObject picker : orderPickerList)
            if(picker.move(UPDATE_COUNTER));
    }
}