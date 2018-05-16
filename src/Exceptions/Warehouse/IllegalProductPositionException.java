package Exceptions.Warehouse;

public class IllegalProductPositionException extends RuntimeException {
    private String errorMsg;

    public IllegalProductPositionException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public IllegalProductPositionException() {
        this.errorMsg = "Illegal product position";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
