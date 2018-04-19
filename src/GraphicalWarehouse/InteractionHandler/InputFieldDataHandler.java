package GraphicalWarehouse.InteractionHandler;

import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class InputFieldDataHandler {

    private List<Integer> productIDListBuffer;

    public InputFieldDataHandler() {
        this.productIDListBuffer = new ArrayList<>();
    }

    // This method takes a string of product id's, clears product buffer,
    // adds new result to the productIDListBuffer and returns it
    public List<Integer> generateProductIDList(String inputString) {
        productIDListBuffer.clear();

        if(inputString.isEmpty())
            showAlert("The text field was empty", Alert.AlertType.WARNING);

        String tempStringBuffer = "";
        int inputStringLength = inputString.length() - 1;

        for(int charIndex = 0; charIndex <= inputStringLength; charIndex++) {

            char currentChar = inputString.charAt(charIndex);

            if(isNumeric(currentChar)) {
                tempStringBuffer += currentChar;
            }

            // Buffer closing conditions
            if((currentChar == ',' || charIndex == inputStringLength) && !tempStringBuffer.isEmpty()) {
                int foundNumber;
                // Try to parse the current tempStringBuffer to integer
                try {
                    foundNumber = Integer.parseInt(tempStringBuffer);
                    productIDListBuffer.add(foundNumber);
                    tempStringBuffer = "";

                } catch(NumberFormatException e) {
                    showAlert(e.getMessage(), Alert.AlertType.ERROR);
                }
            }

        }

        return productIDListBuffer;

    }

    public String generateProductIDString() {

        String productIDString = "";

        for(Integer number : productIDListBuffer) {
            String currentNumber = number.toString();

            if(productIDString.equals(""))
                productIDString = currentNumber;
            else
                productIDString = productIDString + ", " + currentNumber;
        }

        return productIDString;

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
