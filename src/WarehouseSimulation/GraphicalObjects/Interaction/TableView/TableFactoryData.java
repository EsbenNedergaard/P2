package WarehouseSimulation.GraphicalObjects.Interaction.TableView;

import BackEnd.Geometry.Node;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

import java.util.List;

/* THIS CLASS IS A DATA MODEL FOR THE GRAPHICAL OBJECT TABLE VIEW.
 * THIS CLASS ONLY FORMS THE DATA STRUCTURE FOR PUTTING DATA INTO
 * A TABLE VIEW*/

public class TableFactoryData {

    private final SimpleStringProperty productIDSet;
    private final SimpleStringProperty number;
    // Button for highlighting route graphically
    private Button highlightButton;
    // Holds a copy of the node list too
    private List<Node> routeList;

    public TableFactoryData(String productIDSet, int number, List<Node> list) {
        this.productIDSet = new SimpleStringProperty(productIDSet);
        this.number = new SimpleStringProperty("" + number);
        this.highlightButton = new Button("View");
        this.routeList = list;
    }

    public String getProductIDSet() {
        return productIDSet.get();
    }

    public SimpleStringProperty productIDSetProperty() {
        return productIDSet;
    }

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public Button getHighlightButton() {
        return this.highlightButton;
    }

    public List<Node> getRouteList() {
        return routeList;
    }

}
