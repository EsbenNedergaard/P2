package Exceptions.Warehouse;

public class RackRowDoesNotContainProductException extends Exception {
    private String errorMsg;

    public RackRowDoesNotContainProductException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
