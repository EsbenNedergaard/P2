package GraphicalWarehouse;

import Geometry.Point2D;
import GraphicalWarehouse.GraphicalObjects.PickPointsDesign;
import GraphicalWarehouse.GraphicalObjects.RackDesign;
import GraphicalWarehouse.GraphicalObjects.RackRowDesign;
import GraphicalWarehouse.GraphicalObjects.Tile;
import Warehouse.Aisle.Aisle;
import Warehouse.Racks.Rack;
import Warehouse.Racks.RackRow;
import Warehouse.Warehouse;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class GraphicalWarehouse {
    private Warehouse warehouse;
    private int LENGTH_WAREHOUSE;
    private int WIDTH_WAREHOUSE;

    //private Group orderPickerGroup = new Group();

    public GraphicalWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.LENGTH_WAREHOUSE = warehouse.getLength();
        this.WIDTH_WAREHOUSE = warehouse.getWidth();
    }

    // Returns a group of graphical tiles which represents the warehouse floor
    private Group getTileGroup() {
        // Create a group to the graphical tiles
        Group tileGroup = new Group();

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
            RackRowDesign graphicRack = new RackRowDesign(rackRowElement);
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
                RackDesign graphicRack = new RackDesign(rackElement);
                Label amtProducts = new Label("" + rackElement.getProductList().size());
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
            PickPointsDesign startPointDesign = new PickPointsDesign(aisleElement.getStartPoint());
            PickPointsDesign endPointDesign = new PickPointsDesign(aisleElement.getEndPoint());

            // Puts the points into the group
            pickPointGroup.getChildren().addAll(startPointDesign, endPointDesign);
        }

        return pickPointGroup;
    }

    public Parent getWarehouseGraphics() {
        Pane root = new Pane();

        Group pickPointGroup = getPickPointGroup();
        Group tileGroup = getTileGroup();
        Group rackRowGroup = getRackRowGroup();
        //TODO: skal fixes senere hvor vi laver noget med tal på dem i stedet alt efter hvor mange er ligger på dem
        Group rackGroup = getRackGroup();


        root.setPrefSize(LENGTH_WAREHOUSE * TILE_SIZE, WIDTH_WAREHOUSE * TILE_SIZE);
        //root.getChildren().addAll(pickPointGroup, rackRowGroup, tileGroup);
        root.getChildren().addAll(pickPointGroup, rackRowGroup, rackGroup, tileGroup);

        return root;
    }

}
