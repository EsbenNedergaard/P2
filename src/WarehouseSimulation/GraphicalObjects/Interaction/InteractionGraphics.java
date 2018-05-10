package WarehouseSimulation.GraphicalObjects.Interaction;

import WarehouseSimulation.GraphicalObjects.Interaction.TableView.Table;
import javafx.scene.control.*;

public class InteractionGraphics {

    private TextField inputField;
    private Button addDataButton;
    private Button launchButton;
    private Button resetAllButton;
    private Button startOverButton;
    private Table tableView;

    public InteractionGraphics() {
        this.inputField = new TextField();
        this.addDataButton = new Button("Add");
        this.launchButton = new Button("Launch");
        this.resetAllButton = new Button("Reset");
        this.startOverButton = new Button("Start Over");
        this.tableView = new Table();
        // Add style class to launch button
        launchButton.getStyleClass().add("launch-button");

        //setEventHandlers();
    }

    public TextField getInputField() {
        return inputField;
    }

    public Button getAddDataButton() {
        return addDataButton;
    }

    public Button getLaunchButton() {
        return launchButton;
    }

    public Button getResetAllButton() {
        return resetAllButton;
    }

    public Button getStartOverButton() {
        return startOverButton;
    }

    public Table getTableView() {
        return tableView;
    }

}
