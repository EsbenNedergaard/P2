package Exceptions;

public class PickerIsTrappedException extends RuntimeException {
    private String errorMsg;

    public PickerIsTrappedException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public PickerIsTrappedException() {
        this.errorMsg = "The route was not possible to create";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
