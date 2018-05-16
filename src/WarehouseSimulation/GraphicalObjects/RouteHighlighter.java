package WarehouseSimulation.GraphicalObjects;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class RouteHighlighter {

    private List<Node> routeList;
    private Group highlightGroup = new Group();
    private boolean highlightOn = false;
    private String color;
    private List<Point2D> productPositions;

    public RouteHighlighter() {
        this.routeList = new ArrayList<>();
    }

    // Creates a new Tile with a new color which highlights the route
    private void createHighlight() {

        // Create the new highlighted route
        for (Node node : routeList) {
            Point2D tileLocation = new Point2D(node.getX(), node.getY());
            highlightGroup.getChildren().add(new CircleTile(tileLocation, color, 2));
        }

        // Create product highlights
        int i = 1;
        for (Point2D point : productPositions) {
            CircleTile circle = new CircleTile(point, color, 8);
            Text text = new Text("" + i);
            PaneCircleText pane = new PaneCircleText(circle, text);
            highlightGroup.getChildren().add(pane);
            i++;
        }

    }

    public Group getHighlightGroup() {
        return highlightGroup;
    }

    public void setHighlightRouteList(List<Node> routeList, String color, List<Point2D> productPositions) {
        this.routeList = routeList;
        this.color = color;
        this.productPositions = productPositions;

        // When the exact same route is highlighted again, delete the highlight
        if (isHighlightOn() && checkIfHighlighted()) {
            reset();
            setHighlightOn(false);
        } else {
            // When a new route should be highlighted, delete the previous
            reset();
            createHighlight();
            setHighlightOn(true);
        }
    }

    public void reset() {
        highlightGroup.getChildren().clear();
        setHighlightOn(false);
    }

    // Checking if the route compared to is already highlighted
    private boolean checkIfHighlighted() {
        int i = 0;
        for (Node node : routeList) {
            if (node.getXPixels() != getHighlightGroup().getChildren().get(i).getTranslateX() ||
                    node.getYPixels() != getHighlightGroup().getChildren().get(i).getTranslateY()) {
                return false;
            }
            i++;
        }
        return true;
    }

    public boolean isHighlightOn() {
        return highlightOn;
    }

    public void setHighlightOn(boolean highlightOn) {
        this.highlightOn = highlightOn;
    }
}
