package WarehouseSimulation.GraphicalObjects.OrderPicker;

import BackEnd.Geometry.Node.Node;
import javafx.scene.paint.Color;

import java.util.List;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class OrderPicker extends MovingCircle {

    public OrderPicker(List<Node> routeList, String color) {
        super(routeList);
        setDesign(color);
    }

    private void setDesign(String color) {
        setRadius(TILE_SIZE / 2.5);
        setFill(Color.valueOf(color));
        relocateCircle(getRadius());
    }

    private void relocateCircle(double radius) {
        double relocateValue = (TILE_SIZE / 2) - radius;
        relocate(relocateValue, relocateValue);
    }

}
