package GraphicalWarehouse;

import Geometry.*;

import Geometry.Node;
import GraphicalWarehouse.GraphicalObjects.*;
import GraphicalWarehouse.InteractionHandler.InputFieldDataHandler;
import Warehouse.Aisle.Aisle;
import Warehouse.Racks.*;
import Warehouse.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static Warehouse.GUIWarehouse.SCALE;
import static Warehouse.GUIWarehouse.TILE_SIZE;

public class GraphicalWarehouse {
    private Warehouse warehouse;
    private int LENGTH_WAREHOUSE;
    private int WIDTH_WAREHOUSE;

    // Path finder
    private FastestRoute pathFinder;
    private final int MAX_TIME = 500;
    // Graphic groups
    private Group tileGroup;
    private Group pickPointGroup;
    private Group rackRowGroup;
    private Group rackGroup;
    private Group orderPickerGroup;
    private Group interactionFieldGroup;

    private List<OrderPickerGraphics> orderPickerList;

    private InteractionGraphics interactionGraphics = new InteractionGraphics();

    private GenerateRandomPickingRoute
            randomPickingRoute = new GenerateRandomPickingRoute();

    // Animation timer
    private AnimationTimer timer;
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
        this.pathFinder = new FastestRoute(grid);
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

            for (Rack rackElement : rackRowElement.getRackArray()) {
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

    private Group getPickPointGroup() {
        Group pickPointGroup = new Group();

        for(Aisle aisleElement : warehouse.getAisleList()) {
            // Setup the design for the points
            PickPointsGraphics startPointDesign = new PickPointsGraphics(aisleElement.getStartPoint());
            PickPointsGraphics endPointDesign = new PickPointsGraphics(aisleElement.getEndPoint());

            // Puts the points into the group
            pickPointGroup.getChildren().addAll(startPointDesign, endPointDesign);
        }

        return pickPointGroup;
    }

    private Group getOrderPickerGroup() {
        Group orderPickerGroup = new Group();
        OrderPickerGraphics orderPickerTest;

        return orderPickerGroup;
    }

    public void addPicker(OrderPickerGraphics picker) {
        orderPickerList.add(picker);
        orderPickerGroup.getChildren().add(picker);
    }

    private Group getInteractionFieldGroup() {

        BorderPane borderPane = new BorderPane();
        GridPane gridpane = new GridPane();
        gridpane.setMinWidth(TILE_SIZE * WIDTH_WAREHOUSE * SCALE);
        gridpane.setHgap(10);
        Label heading = new Label("Add product list to queue. Product id separated by comma");
        heading.getStyleClass().add("heading-label");

        Button addRouteButton = interactionGraphics.getAddDataButton();
        TextField inputField = interactionGraphics.getInputField();

        gridpane.add(heading, 1, 1, 5, 1);
        gridpane.add(inputField, 1, 2, 4, 2);
        gridpane.add(addRouteButton, 5, 2);
        //gridpane.add(launchButton, 5, 5);

        TableGraphics table = (TableGraphics) interactionGraphics.getTable();

        borderPane.getStyleClass().add("interaction-border-pane");

        borderPane.setRight(gridpane);
        borderPane.setLeft(table);

        setOnButtonClickEvent(addRouteButton, inputField, table);

        return new Group(borderPane);

    }

    private void setOnButtonClickEvent(Button addButton, TextField inputField, TableGraphics table) {

        addButton.setOnMouseClicked(e -> this.actionsForAddProductIDs(inputField, table));
        inputField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                this.actionsForAddProductIDs(inputField, table);
            }
        });
    }

    private void actionsForAddProductIDs(TextField inputField, TableGraphics table) {
        InputFieldDataHandler textHandler = new InputFieldDataHandler();
        List<Integer> tempProductIDList = textHandler.generateProductIDList(inputField.getText());
        // Find route for picker
        List<Point2D> nodeList = this.warehouse.getPickingPointsFromIDs(tempProductIDList);
        List<Node> fastestRoute = this.pathFinder.calculateBestRoute(nodeList);

        OrderPickerGraphics orderPicker = new OrderPickerGraphics(fastestRoute);
        addPicker(orderPicker);

        // Add to table
        String generatedProductIDs = textHandler.generateProductIDString();

        if(!generatedProductIDs.equals(""))
            table.add(generatedProductIDs);

        inputField.clear();
    }

    public Parent getWarehouseGraphics() {
        Pane root = new Pane();
        BorderPane borderPane = new BorderPane();
        Group simulationElementsGroup = new Group();

        pickPointGroup = getPickPointGroup();
        tileGroup = getTileGroup();
        rackRowGroup = getRackRowGroup();
        rackGroup = getRackGroup();
        orderPickerGroup = getOrderPickerGroup();
        interactionFieldGroup = getInteractionFieldGroup();

        // Add all elements for the simulation
        simulationElementsGroup.getChildren().addAll(pickPointGroup, rackRowGroup, rackGroup, tileGroup, orderPickerGroup);

        borderPane.setTop(simulationElementsGroup);
        borderPane.setBottom(interactionFieldGroup);

        root.setPrefSize(LENGTH_WAREHOUSE * TILE_SIZE, WIDTH_WAREHOUSE * TILE_SIZE * 2.43);

        root.getChildren().addAll(borderPane);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private void onUpdate() {
        UPDATE_COUNTER++;
        for(OrderPickerGraphics picker : orderPickerList) {
            if(picker.move(UPDATE_COUNTER));
        }
        //if(orderPickerTest.move(UPDATE_COUNTER));
        //if(orderPickerTest2.move(UPDATE_COUNTER));
        //if(orderPickerTest3.move(UPDATE_COUNTER));
    }
}
