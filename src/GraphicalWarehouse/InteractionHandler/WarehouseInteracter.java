package GraphicalWarehouse.InteractionHandler;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class WarehouseInteracter {

    private TextField inputField;
    private Button addDataButton;
    private Button launchButton;
    private List<Integer> productIDListBuffer = new ArrayList<>();

    public WarehouseInteracter() {
        this.inputField = new TextField();
        this.addDataButton = new Button("Add");
        this.launchButton = new Button("Launch");
        // Add style class to launch button
        launchButton.getStyleClass().add("launch-button");

        setEventHandlers();
    }

    private void setEventHandlers() {
        addDataButton.setOnMouseClicked(e -> {
            String inputString = inputField.getText();
            handleInputData(inputString);
        });
    }

    private void handleInputData(String inputString) {

        if(inputString.isEmpty())
            showAlert("The text field was empty", Alert.AlertType.WARNING);

        String tempStringBuffer = "";
        int inputStringLength = inputString.length() - 1;

        for(int charIndex = 0; charIndex <= inputStringLength; charIndex++) {

            char currentChar = inputString.charAt(charIndex);

            if(isNumeric(currentChar)) {
                tempStringBuffer += currentChar;
            }

            if((currentChar == ',' || charIndex == inputStringLength) && !tempStringBuffer.isEmpty()) {
                int foundNumber;
                // Try to parse the current buffer to integer
                try {
                    foundNumber = Integer.parseInt(tempStringBuffer);
                    productIDListBuffer.add(foundNumber);
                    tempStringBuffer = "";

                } catch(NumberFormatException e) {
                    showAlert(e.getMessage(), Alert.AlertType.ERROR);
                }
            }

        }

        // TODO: Send data from productIDListBuffer here

        // Clear data in productIDListBuffer
        productIDListBuffer.clear();


    }

    private void clearInputField() {
        this.inputField.clear();
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

    private void showAlert(String contentText, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(contentText);
        alert.show();
    }

    private boolean isNumeric(char c) {
        return c >= '0' && c <= '9';
    }

}
