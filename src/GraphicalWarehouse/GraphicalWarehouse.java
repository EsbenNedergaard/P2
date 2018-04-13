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


    private List<Node> routeNodesList = new ArrayList<>();
    private List<Node> routeNodesList2 = new ArrayList<>();

    // Animation timer
    private AnimationTimer timer;
    private int UPDATE_COUNTER = 0;

    public GraphicalWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.LENGTH_WAREHOUSE = warehouse.getLength();
        this.WIDTH_WAREHOUSE = warehouse.getWidth();
    }

    // TODO: Delete this method
    private void fillRandomNodes() {
        int i;

        for(i = 0; i <= 38; i++) {
            routeNodesList.add(new Node(new Point2D(i, 1)));
        }

        routeNodesList.add(new Node(new Point2D(i, 1)));
        routeNodesList.add(new Node(new Point2D(i, 2)));
        routeNodesList.add(new Node(new Point2D(i, 2)));
        routeNodesList.add(new Node(new Point2D(i, 3)));
        routeNodesList.add(new Node(new Point2D(i, 4)));
        routeNodesList.add(new Node(new Point2D(i, 4)));

        for(i = 38; i > 4; i--) {
            routeNodesList.add(new Node(new Point2D(i, 4)));
        }

        routeNodesList.add(new Node(new Point2D(i, 4)));
        routeNodesList.add(new Node(new Point2D(i, 5)));
        routeNodesList.add(new Node(new Point2D(i, 6)));

        for(i = 4; i <= 38; i++) {
            routeNodesList.add(new Node(new Point2D(i, 7)));
        }

        routeNodesList.add(new Node(new Point2D(i, 7)));
        routeNodesList.add(new Node(new Point2D(i, 8)));
        routeNodesList.add(new Node(new Point2D(i, 8)));
        routeNodesList.add(new Node(new Point2D(i, 9)));
        routeNodesList.add(new Node(new Point2D(i, 10)));
        routeNodesList.add(new Node(new Point2D(i, 10)));

        for(i = 38; i >= 4; i--) {
            routeNodesList.add(new Node(new Point2D(i, 10)));
        }

        //for(Node item : routeNodesList) {
         //   System.out.println(item.getX() + "," + item.getY());
        //}

    }

    private void fillRandomNodes2() {
        routeNodesList2.add(new Node(new Point2D(1, 1)));
        routeNodesList2.add(new Node(new Point2D(1, 2)));
        routeNodesList2.add(new Node(new Point2D(1, 3)));
        routeNodesList2.add(new Node(new Point2D(1, 4)));
        routeNodesList2.add(new Node(new Point2D(1, 5)));
        routeNodesList2.add(new Node(new Point2D(1, 6)));
        routeNodesList2.add(new Node(new Point2D(1, 7)));
        routeNodesList2.add(new Node(new Point2D(2, 7)));
        routeNodesList2.add(new Node(new Point2D(3, 7)));
        routeNodesList2.add(new Node(new Point2D(4, 7)));
        routeNodesList2.add(new Node(new Point2D(5, 7)));
        routeNodesList2.add(new Node(new Point2D(6, 7)));
        routeNodesList2.add(new Node(new Point2D(7, 7)));
        routeNodesList2.add(new Node(new Point2D(8, 7)));
        routeNodesList2.add(new Node(new Point2D(9, 7)));
        routeNodesList2.add(new Node(new Point2D(10, 7)));
        routeNodesList2.add(new Node(new Point2D(11, 7)));
        routeNodesList2.add(new Node(new Point2D(12, 7)));
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
        orderPickerTest = new OrderPickerGraphics(routeNodesList);
        orderPickerTest2 = new OrderPickerGraphics(routeNodesList2);

        orderPickerGroup.getChildren().addAll(orderPickerTest, orderPickerTest2);
        return orderPickerGroup;
    }

    public Parent getWarehouseGraphics() {
        Pane root = new Pane();

        fillRandomNodes();
        fillRandomNodes2();

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

        if(orderPickerTest2.move(UPDATE_COUNTER));
        if(orderPickerTest.move(UPDATE_COUNTER));
    }
}
