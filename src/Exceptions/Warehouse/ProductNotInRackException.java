package Exceptions.Warehouse;

public class ProductNotInRackException extends RuntimeException {
    private String errorMsg;

    public ProductNotInRackException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ProductNotInRackException() {
        this.errorMsg = "Product not in rack";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
