package GraphicalWarehouse;

import Geometry.Node;
import Geometry.Point2D;
import GraphicalWarehouse.GraphicalObjects.*;
import Warehouse.Aisle.Aisle;
import Warehouse.Racks.*;
import Warehouse.Warehouse;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class GraphicalWarehouse {
    private Warehouse warehouse;
    private int LENGTH_WAREHOUSE;
    private int WIDTH_WAREHOUSE;

    // Graphic groups
    private Group tileGroup;
    private Group pickPointGroup;
    private Group rackRowGroup;
    private Group rackGroup;
    private Group orderPickerGroup;

    private OrderPickerGraphics orderPickerTest;
    private OrderPickerGraphics orderPickerTest2;
    private OrderPickerGraphics orderPickerTest3;

    private GenerateRandomPickingRoute
            randomPickingRoute = new GenerateRandomPickingRoute();

    // Animation timer
    private AnimationTimer timer;
    private int UPDATE_COUNTER = 0;

    public GraphicalWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.LENGTH_WAREHOUSE = warehouse.getLength();
        this.WIDTH_WAREHOUSE = warehouse.getWidth();
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
        orderPickerTest = new OrderPickerGraphics(randomPickingRoute.getRoute1());
        orderPickerTest2 = new OrderPickerGraphics(randomPickingRoute.getRoute2());
        orderPickerTest3 = new OrderPickerGraphics(randomPickingRoute.getRoute3());

        orderPickerGroup.getChildren().addAll(orderPickerTest, orderPickerTest2, orderPickerTest3);
        return orderPickerGroup;
    }

    public Parent getWarehouseGraphics() {
        Pane root = new Pane();

        pickPointGroup = getPickPointGroup();
        tileGroup = getTileGroup();
        rackRowGroup = getRackRowGroup();
        rackGroup = getRackGroup();
        orderPickerGroup = getOrderPickerGroup();

        root.setPrefSize(LENGTH_WAREHOUSE * TILE_SIZE, WIDTH_WAREHOUSE * TILE_SIZE);

        root.getChildren().addAll(pickPointGroup, rackRowGroup, rackGroup, tileGroup, orderPickerGroup);

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

        if(!orderPickerTest.move(UPDATE_COUNTER)) {
            System.out.println("picker 1 is done");
        }
        if(!orderPickerTest2.move(UPDATE_COUNTER)) {
            System.out.println("picker 2 is done");
        }
        if(!orderPickerTest3.move(UPDATE_COUNTER)) {
            System.out.println("picker 3 is done");
        }
    }
}
