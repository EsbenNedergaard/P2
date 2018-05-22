package WarehouseSimulation.GraphicalObjects.Warehouse;

import BackEnd.Geometry.Point2D;
import Warehouse.Racks.RackRow;
import Warehouse.Warehouse;
import WarehouseSimulation.GraphicalObjects.RackRowGraphic;
import WarehouseSimulation.GraphicalObjects.Tile;
import javafx.scene.Group;

public class WarehouseGraphics {

    private Warehouse warehouse;
    private int LENGTH_WAREHOUSE;
    private int WIDTH_WAREHOUSE;

    public WarehouseGraphics(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.LENGTH_WAREHOUSE = warehouse.getLength();
        this.WIDTH_WAREHOUSE = warehouse.getWidth();
    }

    public Group getTileGroup() {
        Group tileGroup = new Group();

        for (int x = 0; x < LENGTH_WAREHOUSE; x++) {
            for (int y = 0; y < WIDTH_WAREHOUSE; y++) {
                Point2D tilePoint = new Point2D(x, y);
                tileGroup.getChildren().add(new Tile(tilePoint));
            }
        }

        return tileGroup;
    }

    public Group getRackRowGroup() {
        Group rackRowGroup = new Group();

        for (RackRow rackRowElement : warehouse.getRackRowList()) {
            RackRowGraphic graphicRack = new RackRowGraphic(rackRowElement);
            rackRowGroup.getChildren().add(graphicRack);
        }

        return rackRowGroup;
    }

    public Group createStartAndEndPoints(Point2D startPoint, Point2D endPoint) {
        Group pointGroup = new Group();
        Line startFill = new Line("#49eb41", startPoint);
        Line endFill = new Line("#ee4a2f", endPoint);
        pointGroup.getChildren().addAll(startFill, endFill);
        return pointGroup;
    }

}
