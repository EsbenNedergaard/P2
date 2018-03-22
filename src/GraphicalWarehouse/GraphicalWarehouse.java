package GraphicalWarehouse;

import Geometry.Point2D;
import GraphicalWarehouse.GraphicalObjects.PickPointsDesign;
import GraphicalWarehouse.GraphicalObjects.ProductDesign;
import GraphicalWarehouse.GraphicalObjects.RackRowDesign;
import GraphicalWarehouse.GraphicalObjects.Tile;
import Warehouse.Aisle.Aisle;
import Warehouse.ProductContainer.RackRow;
import Warehouse.Warehouse;
import Warehouse.Product;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class GraphicalWarehouse {
    private Warehouse warehouse;
    private int WIDTH_WAREHOUSE;
    private int HEIGHT_WAREHOUSE;

    //private Group orderPickerGroup = new Group();

    public GraphicalWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.WIDTH_WAREHOUSE = warehouse.getWidth();
        this.HEIGHT_WAREHOUSE = warehouse.getHeight();
    }

    // Returns a group of graphical tiles which represents the warehouse floor
    private Group getTileGroup() {
        // Create a group to the graphical tiles
        Group tileGroup = new Group();

        for (int x = 0; x < WIDTH_WAREHOUSE; x++) {
            for (int y = 0; y < HEIGHT_WAREHOUSE; y++) {
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

    private Group getProductGroup() {
        // Create a group of graphical products
        Group productGroup = new Group();

        for(RackRow rackRowElement : warehouse.getRackRowList()) {
            // Now get the product list from the current rack
            for(Product productElement : rackRowElement.getProductList()) {
                // Styles the product
                ProductDesign graphicProduct = new ProductDesign(productElement);
                // Put the product into the group
                productGroup.getChildren().add(graphicProduct);
            }
        }

        return productGroup;
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
        //Group productGroup = getProductGroup(); TODO: skal fixes senere hvor vi laver noget med tal på dem i stedet alt efter hvor mange er ligger på dem


        root.setPrefSize(WIDTH_WAREHOUSE * TILE_SIZE, HEIGHT_WAREHOUSE * TILE_SIZE);
        root.getChildren().addAll(pickPointGroup, rackRowGroup, tileGroup);
        //root.getChildren().addAll(pickPointGroup, rackRowGroup, productGroup, tileGroup);

        return root;
    }

}
