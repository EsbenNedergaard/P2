package GraphicalWarehouse.GraphicalObjects;


/* THIS CLASS TAKES A LIST OF NODES AND HIGHLIGHTS THEM
 * GRAPHICALLY IN THE WAREHOUSE. ONLY ONE ROUTE CAN BE
  * HIGHLIGHTED AT A TIME */

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RouteHighlighterGraphics {

    private List<Node> routeList;
    private Group highlightGroup = new Group();
    private boolean startHighlight = false;

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

    public void setRouteList(List<Node> routeList) {
        this.routeList = routeList;

        //When the exact same route is highlighted again, delete the highlight
        if(startHighlight && compareRoute()) {
            reset();
        }
        else {
            // When a new route should be highlighted, delete the previous
            reset();

            createHighlight();

            startHighlight = true;
        }
    }

    public void reset() {
        highlightGroup.getChildren().clear();
    }

    public boolean compareRoute() {
        if (getHighlightGroup().getChildren() == null){ return false;}
        int i = 0;
        for (Node node : routeList) {
            //System.out.println("routelistX: " + node.getXPixels() + " Y: " + node.getYPixels());
            //System.out.println("GroupX: " + getHighlightGroup().getChildren().get(i).getTranslateX() + " Y: "
            //        + getHighlightGroup().getChildren().get(i).getTranslateY());
            if (node.getXPixels() != getHighlightGroup().getChildren().get(i).getTranslateX() ||
                node.getYPixels() != getHighlightGroup().getChildren().get(i).getTranslateY()) {
                return false;
            }

            i++;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeList, getHighlightGroup());
    }
}
