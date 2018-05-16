package Exceptions.Warehouse;

public class IllegalRackDimensionException extends RuntimeException {
    private String errorMsg;

    public IllegalRackDimensionException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public IllegalRackDimensionException() {
        this.errorMsg = "Not a legal rack dimension";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
