package Warehouse.Exceptions;

public class FullRackException extends RuntimeException {
    private String errorMsg;

    public FullRackException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}