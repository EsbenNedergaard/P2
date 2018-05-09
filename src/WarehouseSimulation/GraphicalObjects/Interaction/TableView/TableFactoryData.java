package WarehouseSimulation.GraphicalObjects.Interaction.TableView;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

import java.util.List;

public class TableFactoryData {

    private final SimpleStringProperty productIDSet;
    private final SimpleStringProperty number;
    private Button highlightButton;
    private List<Node> routeList;
    private String routeColor;
    private List<Point2D> productPositions;

    public TableFactoryData(String productIDSet, int number, List<Node> list, List<Point2D> productPositionsList, String routeColor) {
        this.productIDSet = new SimpleStringProperty(productIDSet);
        this.number = new SimpleStringProperty("" + number);
        this.highlightButton = new Button("View");
        this.routeList = list;
        this.routeColor = routeColor;
        this.productPositions = productPositionsList;
        setButtonStyles();
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

    public String getRouteColor() {
        return routeColor;
    }

    public List<Point2D> getProductPositions() {
        return productPositions;
    }

    private void setButtonStyles() {
        this.highlightButton.setStyle(
                "-fx-background-color: " + this.routeColor + "; " +
                "-fx-text-fill: white;");
    }

}
