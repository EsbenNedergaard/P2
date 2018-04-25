package GraphicalWarehouse.GraphicalObjects;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class InteractionGraphics {

    private TextField inputField;
    private Button addDataButton;
    private Button launchButton;
    private Button resetAllButton;
    private Table tableView;

    public InteractionGraphics() {
        this.inputField = new TextField();
        this.addDataButton = new Button("Add");
        this.launchButton = new Button("Launch");
        this.resetAllButton = new Button("Reset");
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

    public Table getTableView() {
        return tableView;
    }

}
