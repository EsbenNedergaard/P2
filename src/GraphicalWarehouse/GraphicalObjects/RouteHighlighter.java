package GraphicalWarehouse.GraphicalObjects;


/* THIS CLASS TAKES A LIST OF NODES AND HIGHLIGHTS THEM
 * GRAPHICALLY IN THE WAREHOUSE. ONLY ONE ROUTE CAN BE
  * HIGHLIGHTED AT A TIME */

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class RouteHighlighter {

    private List<Node> routeList;
    private Group highlightGroup = new Group();

    public RouteHighlighter(List<Node> routeList) {
        this.routeList = routeList;
        createHighlight();
    }

    public RouteHighlighter() {
        this.routeList = new ArrayList<>();
    }

    // Creates a new Tile with a new color which highlights the route
    private void createHighlight() {
        // When a new route should be highlighted, delete the previous
        highlightGroup.getChildren().clear();

        // Create the new highlighted route
        for(Node node : routeList) {
            Point2D tileLocation = new Point2D(node.getX(), node.getY());
            highlightGroup.getChildren().add(new CircleTile(tileLocation));
        }
    }

    public Group getHighlightGroup() {
        return highlightGroup;
    }

    public void setRouteList(List<Node> routeList) {
        this.routeList = routeList;
        // When a route is set, create it
        createHighlight();
    }

    public void reset() {
        highlightGroup.getChildren().clear();
    }
}
