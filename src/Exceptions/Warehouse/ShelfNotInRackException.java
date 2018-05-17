package Exceptions.Warehouse;

public class ShelfNotInRackException extends RuntimeException {
    private String errorMsg;

    public  ShelfNotInRackException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
