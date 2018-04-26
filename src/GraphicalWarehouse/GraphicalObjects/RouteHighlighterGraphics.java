package GraphicalWarehouse.GraphicalObjects;


/* THIS CLASS TAKES A LIST OF NODES AND HIGHLIGHTS THEM
 * GRAPHICALLY IN THE WAREHOUSE. ONLY ONE ROUTE CAN BE
  * HIGHLIGHTED AT A TIME */

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class RouteHighlighterGraphics {

    private List<Node> routeList;
    private Group highlightGroup = new Group();
    private boolean highlightOn = false;

    public RouteHighlighterGraphics(List<Node> routeList) {
        this.routeList = routeList;
        createHighlight();
    }

    public RouteHighlighterGraphics() {
        this.routeList = new ArrayList<>();
    }

    // Creates a new Tile with a new color which highlights the route
    private void createHighlight() {

        // Create the new highlighted route
        for(Node node : routeList) {
            Point2D tileLocation = new Point2D(node.getX(), node.getY());
            highlightGroup.getChildren().add(new CircleTile(tileLocation));
        }
    }

    public Group getHighlightGroup() {
        return highlightGroup;
    }

    public void setHighlightRouteList(List<Node> routeList) {
        this.routeList = routeList;

        // When the exact same route is highlighted again, delete the highlight
        if(highlightOn && checkIfHighlighted()) {
            reset();
            highlightOn = false;
        }
        else {
            // When a new route should be highlighted, delete the previous
            reset();
            createHighlight();
            highlightOn = true;
        }
    }

    public void reset() {
        highlightGroup.getChildren().clear();
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
}
