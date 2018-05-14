package Warehouse.Exceptions;

public class FullRackRowException extends RuntimeException {
    private String errorMsg;

    public FullRackRowException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
