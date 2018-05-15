package WarehouseSimulation.GraphicalObjects.Interaction;

import WarehouseSimulation.GraphicalObjects.Interaction.TableView.Table;
import javafx.scene.control.*;

public class InteractionGraphics {

    private TextField inputField;
    private Button addDataButton;
    private Button launchButton;
    private Button resetAllButton;
    private Button reLaunchButton;
    private Button speedUpByFiveButton;
    private Button doubleSpeedButton;
    private Button normalSpeedButton;
    private Table tableView;

    public InteractionGraphics() {
        this.inputField = new TextField();
        this.addDataButton = new Button("Add");
        this.launchButton = new Button("Launch");
        this.resetAllButton = new Button("Reset");
        this.reLaunchButton = new Button("Re-Launch");
        this.normalSpeedButton = new Button("x1");
        this.doubleSpeedButton = new Button("x2");
        this.speedUpByFiveButton = new Button("x5");
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

    public Button getReLaunchButton() {
        return reLaunchButton;
    }

    public Button getSpeedUpByFiveButton() {
        return speedUpByFiveButton;
    }

    public Button getNormalSpeedButton() {
        return normalSpeedButton;
    }

    public Button getDoubleSpeedButton() {
        return doubleSpeedButton;
    }

    public Table getTableView() {
        return tableView;
    }

}
