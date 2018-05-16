package WarehouseSimulation.GraphicalObjects.Interaction;

import static Warehouse.GUIWarehouse.*;
import static WarehouseSimulation.GraphicalObjects.Interaction.ButtonType.*;
import WarehouseSimulation.GraphicalObjects.Interaction.TableView.Table;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.*;

public class InteractionGraphics {

    private TextField inputField;
    private Map<ButtonType, Button> buttonsMap;
    private Table tableView;

    public InteractionGraphics() {
        this.buttonsMap = new HashMap<>();
        this.inputField = new TextField();
        this.tableView = new Table();
        createButtons();
    }

    private void createButtons() {
        for(ButtonType buttonType : ButtonType.values())
            buttonsMap.put(buttonType, new Button(buttonType.getText()));
    }

    public BorderPane getInteractionPane() {
        BorderPane borderPane = new BorderPane();
        borderPane.getStyleClass().add("interaction-border-pane");
        borderPane.setRight(getInteractionGrid());
        borderPane.setLeft(tableView.getTable());
        return borderPane;
    }

    private GridPane getInteractionGrid() {
        GridPane gridpane = new GridPane();
        gridpane.add(new VBox(getHeading()), 1, 1, 5, 1);
        gridpane.add(new HBox(inputField, getButton(ADD)), 1, 2, 5, 2);
        gridpane.add(new HBox(
                getButton(LAUNCH),
                getButton(RELAUNCH),
                getButton(RESET)
        ), 1, 4, 5, 4);
        gridpane.add(new Label("Change speed"), 1, 5, 5, 5);
        gridpane.add(new HBox(
                getButton(SPEEDX1),
                getButton(SPEEDX2),
                getButton(SPEEDX5)
        ), 1, 8, 5, 8);
        gridpane.add(getButton(RANDOMIZE), 1, 10, 5, 10);
        setGridPaneStyles(gridpane);
        return gridpane;
    }

    private Label getHeading() {
        Label heading = new Label("Add product list to queue. Product id separated by comma");
        heading.getStyleClass().add("heading-label");
        return heading;
    }

    public Button getButton(ButtonType buttonType) {
        return buttonsMap.get(buttonType);
    }

    private void setGridPaneStyles(GridPane gridpane) {
        gridpane.setMinWidth(TILE_SIZE * SCALE); // TODO: Fix this :))
        gridpane.setHgap(10);
        gridpane.setVgap(15);
    }

    public Map<ButtonType, Button> getButtonsMap() {
        return buttonsMap;
    }

    public TextField getInputField() {
        return inputField;
    }

    public Table getTableView() {
        return tableView;
    }

}
