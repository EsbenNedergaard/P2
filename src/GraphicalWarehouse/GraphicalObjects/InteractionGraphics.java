package GraphicalWarehouse.GraphicalObjects;

import GraphicalWarehouse.InteractionHandler.InputFieldDataHandler;
import javafx.scene.control.*;
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

        InputFieldDataHandler handler = new InputFieldDataHandler();

        // This is when the ADD BUTTON is clicked
        addDataButton.setOnMouseClicked(e -> {
            List<Integer> tempList = handler.generateProductIDList(inputField.getText());
            String generatedProductIDs = handler.generateProductIDString();

            if(!generatedProductIDs.equals(""))
                table.add(generatedProductIDs);

            inputField.clear();
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

}
