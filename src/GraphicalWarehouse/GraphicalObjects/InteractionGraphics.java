package GraphicalWarehouse.GraphicalObjects;

import GraphicalWarehouse.InteractionHandler.InputFieldDataHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.util.List;

public class InteractionGraphics {

    private TextField inputField;
    private Button addDataButton;
    private Button launchButton;
    private TableGraphics table;

    public InteractionGraphics() {
        this.inputField = new TextField();
        this.addDataButton = new Button("Add");
        this.launchButton = new Button("Launch");
        this.table = new TableGraphics();
        // Add style class to launch button
        launchButton.getStyleClass().add("launch-button");

        setEventHandlers();
    }

    private void setEventHandlers() {
        // This is when the ADD BUTTON is clicked
        addDataButton.setOnMouseClicked(e -> this.actionsForAddProductIDs());
        // This is when ENTER key is pressed inside input field
        inputField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                this.actionsForAddProductIDs();
            }
        });
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

    public GridPane getTable() {
        return table;
    }

    // This is the actions which should be called when add button is pressed
    private void actionsForAddProductIDs() {

        InputFieldDataHandler textHandler = new InputFieldDataHandler();

        List<Integer> tempList = textHandler.generateProductIDList(inputField.getText());
        String generatedProductIDs = textHandler.generateProductIDString();

        if(!generatedProductIDs.equals(""))
            table.add(generatedProductIDs);

        inputField.clear();
    }

}
