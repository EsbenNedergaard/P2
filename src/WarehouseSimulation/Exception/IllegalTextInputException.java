package WarehouseSimulation.Exception;

public class IllegalTextInputException extends RuntimeException {
    private String errorMsg;

    public IllegalTextInputException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public IllegalTextInputException() {
        this.errorMsg = "Illegal text input";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
