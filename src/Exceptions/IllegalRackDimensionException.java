package Exceptions;

public class IllegalRackDimensionException extends RuntimeException {
    private String errorMsg;

    public IllegalRackDimensionException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public IllegalRackDimensionException() {
        this.errorMsg = "This is not a legal rack dimension";
    }

    @Override
    public String toString() {
        return errorMsg;
    }
}
