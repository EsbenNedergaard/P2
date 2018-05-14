package Warehouse.Exceptions;

public class FullAisleException extends RuntimeException {
    private String errorMsg;

    public FullAisleException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
